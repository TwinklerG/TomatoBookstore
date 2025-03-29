<script setup lang="ts">
import {
  userLogin,
  userRegister,
  type LoginForm,
  type RegisterForm,
  registerRules,
} from "@/api/users";
import router from "@/router";
import { ElMessage, type FormInstance, type FormRules } from "element-plus";
import { reactive, ref } from "vue";

// Login or Register Status Control
const status = ref<"login" | "register">("login");
const toggleStatus = () => {
  status.value = status.value === "login" ? "register" : "login";
};

// Login Part
const loginForm = reactive<LoginForm>({
  username: "",
  password: "",
});

const loginRules = reactive<FormRules<LoginForm>>({
  username: [{ required: true, message: "Please input your username", trigger: "blur" }],
  password: [
    { required: true, message: "Please input your password", trigger: "blur" },
    { min: 6, max: 18, message: "Password length is between 6 and 18", trigger: "blur" },
  ],
});

const loginRuleFormRef = ref<FormInstance | null>(null);

const submitLoginForm = (formEl: FormInstance | null) => {
  if (!formEl) {
    return;
  }
  formEl.validate(async (valid) => {
    if (valid) {
      try {
        const token = await userLogin(loginForm);
        localStorage.setItem("token", token);
        ElMessage({
          message: "登录成功",
          type: "success",
        });
        router.push("/admin");
      } catch (error) {
        ElMessage({
          message: String(error),
          type: "error",
        });
      }
      formEl.resetFields();
    }
  });
};

// Register Part
const registerRuleFormRef = ref<FormInstance | null>(null);

const roleOptions = [
  { label: "管理员", key: 0, value: "admin" },
  { label: "用户", key: 1, value: "user" },
];

const registerForm = reactive<RegisterForm>({
  username: "",
  password: "",
  name: "",
  avatar: null,
  role: "user",
  telephone: null,
  email: null,
  location: null,
});

const submitRegisterForm = (formEl: FormInstance | null) => {
  if (!formEl) {
    return;
  }
  formEl.validate(async (valid) => {
    if (valid) {
      try {
        registerForm.avatar = registerForm.avatar || null;
        registerForm.telephone = registerForm.telephone || null;
        registerForm.email = registerForm.email || null;
        registerForm.location = registerForm.location || null;
        await userRegister(registerForm);
        ElMessage({
          message: "注册成功",
          type: "success",
        });
        window.location.reload(); // Better Solution?
      } catch (error) {
        ElMessage({
          message: String(error),
          type: "error",
        });
      }
      formEl.resetFields();
    }
  });
};
</script>

<template>
  <div
    class="min-h-screen min-w-screen flex md:flex-row flex-col items-center justify-center bg-gradient-to-r from-red-400 to-yellow-400 dark:from-red-800 dark:to-yellow-600"
  >
    <div class="text-5xl p-10 flex-1/2 items-center justify-center flex">番茄书店</div>
    <div class="flex-1/2 flex items-center justify-center w-full md:w-auto">
      <ElCard class="p-5 md:w-2/3">
        <div class="text-2xl p-2 mb-2">欢迎回来</div>
        <ElForm
          v-if="status === 'login'"
          ref="loginRuleFormRef"
          :model="loginForm"
          :rules="loginRules"
          label-width="auto"
        >
          <ElFormItem label="用户名" prop="username">
            <ElInput v-model="loginForm.username" />
          </ElFormItem>
          <ElFormItem label="密码" prop="password">
            <ElInput v-model="loginForm.password" type="password" show-password />
          </ElFormItem>
          <ElFormItem>
            <ElButton type="primary" @click="submitLoginForm(loginRuleFormRef)">登录</ElButton>
            <ElButton @click="toggleStatus">去注册</ElButton>
          </ElFormItem>
        </ElForm>
        <ElForm
          v-else
          ref="registerRuleFormRef"
          :model="registerForm"
          :rules="registerRules"
          label-width="auto"
        >
          <ElFormItem label="用户名" prop="username">
            <ElInput v-model="registerForm.username" />
          </ElFormItem>
          <ElFormItem label="密码" prop="password">
            <ElInput v-model="registerForm.password" type="password" show-password />
          </ElFormItem>
          <ElFormItem label="真实姓名" prop="name">
            <ElInput v-model="registerForm.name" />
          </ElFormItem>
          <ElFormItem label="头像" prop="avatar">
            <ElInput v-model="registerForm.avatar" />
          </ElFormItem>
          <ElFormItem label="身份" prop="role">
            <ElSelect placeholder="选择你的身份" v-model="registerForm.role">
              <el-option
                v-for="item in roleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </ElSelect>
          </ElFormItem>
          <ElFormItem label="电话" prop="telephone">
            <ElInput v-model="registerForm.telephone" />
          </ElFormItem>
          <ElFormItem label="邮箱" prop="email">
            <ElInput v-model="registerForm.email" />
          </ElFormItem>
          <ElFormItem label="住址" prop="location">
            <ElInput v-model="registerForm.location" />
          </ElFormItem>
          <ElFormItem>
            <ElButton type="primary" @click="submitRegisterForm(registerRuleFormRef)"
              >注册</ElButton
            >
            <ElButton @click="toggleStatus">去登录</ElButton>
          </ElFormItem>
        </ElForm>
      </ElCard>
    </div>
  </div>
</template>

<style lang="css" scoped></style>
