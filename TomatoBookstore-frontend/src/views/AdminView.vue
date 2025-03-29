<script setup lang="ts">
import { onMounted, ref, watch } from "vue";
import { ElMessage, type TabsPaneContext } from "element-plus";
import {
  deleteProduct,
  getProductById,
  getProducts,
  putProduct,
  type ProductInfo,
} from "@/api/products";
import { getUserByUsername, putUser, type UserInfo, putUserRules } from "@/api/users";
import ProductForm from "@/components/ProductForm.vue";
import CreateProduct from "@/components/CreateProduct.vue";

// LifeCycle Functions
onMounted(async () => {
  (await getProducts()).forEach((product) => {
    products.value.set(product.id, product);
  });
});

// Tabs Control
const activeTabName = ref("user");

const handleClick = (tabs: TabsPaneContext) => {
  if (tabs.paneName === "user") {
  } else if (tabs.paneName === "admin") {
  }
};

// User Part
const roleOptions = [
  { label: "管理员", key: 0, value: "admin" },
  { label: "用户", key: 1, value: "user" },
];

const usersMap = ref<Map<string, UserInfo>>(new Map());

const userInputState = ref("");

const userActivateName = ref("");

const userActivateClone = ref<UserInfo | null>(null);

watch(userActivateName, (newUsername, oldUsername) => {
  if (userActivateClone.value !== null) {
    usersMap.value.set(oldUsername, userActivateClone.value);
  }

  userActivateClone.value = usersMap.value.get(newUsername)
    ? JSON.parse(JSON.stringify(usersMap.value.get(newUsername)))
    : null;
  isEditUser.value = false;
});

const addUser = async () => {
  try {
    const user: UserInfo = await getUserByUsername(userInputState.value);
    if (usersMap.value.get(user.username)) {
      ElMessage({
        message: "用户已存在",
        type: "error",
      });
      return;
    }
    ElMessage({
      message: "获取用户信息成功",
      type: "success",
    });
    usersMap.value.set(user.username, user);
    userInputState.value = "";
  } catch (error) {
    ElMessage({
      message: String(error),
      type: "error",
    });
  }
};

const getUser = (username: string): UserInfo | undefined => {
  return usersMap.value.get(username);
};

const updateUser = async () => {
  try {
    if (usersMap.value.get(userActivateName.value) === null) {
      ElMessage({
        message: "系统错误",
        type: "error",
      });
      return;
    }
    const user = usersMap.value.get(userActivateName.value);
    if (user) {
      user.avatar = user.avatar === "" ? undefined : user.avatar;
      user.telephone = user.telephone === "" ? undefined : user.telephone;
      user.email = user.email === "" ? undefined : user.email;
      user.location = user.location === "" ? undefined : user.location;
    }
    const msg = await putUser(usersMap.value.get(userActivateName.value)!);
    ElMessage({
      message: msg,
      type: "success",
    });
    userActivateClone.value = JSON.parse(
      JSON.stringify(usersMap.value.get(userActivateName.value))
    );
    isEditUser.value = false;
  } catch (error) {
    ElMessage({
      message: String(error),
      type: "error",
    });
  }
};

const isEditUser = ref(false);

// Product Part
const isEditProduct = ref<boolean>(false);

const products = ref<Map<string, ProductInfo>>(new Map());

const productInputState = ref("");
const searchedProduct = ref<ProductInfo | null>(null);
const searchProduct = async () => {
  try {
    const product = await getProductById(productInputState.value);
    searchedProduct.value = product;
    ElMessage({
      message: "查询成功",
      type: "success",
    });
    productInputState.value = "";
  } catch (error) {
    ElMessage({
      message: String(error),
      type: "error",
    });
  }
};
const clearSearch = () => {
  searchedProduct.value = null;
  productInputState.value = "";
};

const productActivateId = ref("");
const productIdClone = ref<ProductInfo | null>(null);
watch(productActivateId, (newId, oldId) => {
  if (productIdClone.value !== null) {
    products.value.set(oldId, productIdClone.value);
  }
  productIdClone.value = products.value.get(newId)
    ? JSON.parse(JSON.stringify(products.value.get(newId)))
    : null;
  isEditProduct.value = false;
});

const handleProductChange = (updatedProduct: ProductInfo) => {
  products.value.set(updatedProduct.id, updatedProduct);
};

const handleUpdateProduct = async (product: ProductInfo) => {
  try {
    const msg = await putProduct(product);
    ElMessage({
      message: msg,
      type: "success",
    });
    isEditProduct.value = false;
  } catch (error) {
    ElMessage({
      message: String(error),
      type: "error",
    });
  }
};

const handleDeleteProduct = async (id: string) => {
  try {
    const msg = await deleteProduct(id);
    ElMessage({
      message: msg,
      type: "success",
    });
    isEditProduct.value = false;
  } catch (error) {
    ElMessage({
      message: String(error),
      type: "error",
    });
  }
};
</script>

<template>
  <div class="h-screen w-screen flex items-center justify-center p-2">
    <ElTabs
      @tab-click="handleClick"
      v-model="activeTabName"
      type="border-card"
      class="w-full h-full"
    >
      <ElTabPane
        label="用户管理"
        name="user"
        class="scrollable-tab"
        :style="{ height: '100vh', overflowY: 'auto' }"
      >
        <ElRow>
          <ElCol :span="12">
            <ElInput v-model="userInputState" clearable placeholder="输入用户名" />
          </ElCol>
          <ElCol :span="2">
            <ElButton @click="addUser">添加用户</ElButton>
          </ElCol>
        </ElRow>
        <ElCollapse v-model="userActivateName" accordion>
          <ElCollapseItem
            v-for="[username, user] in usersMap"
            :key="username"
            :title="username"
            :name="username"
            v-show="username.toLowerCase().startsWith(userInputState.toLowerCase())"
          >
            <ElForm label-width="auto" :rules="putUserRules" :model="getUser(username)">
              <ElFormItem label="用户名" prop="username">
                <ElInput v-model="user.username" disabled />
              </ElFormItem>
              <ElFormItem label="新密码" prop="password">
                <ElInput
                  v-model="user.password"
                  :disabled="!isEditUser"
                  type="password"
                  show-password
                />
              </ElFormItem>
              <ElFormItem label="真实姓名" prop="name">
                <ElInput v-model="user.name" :disabled="!isEditUser" />
              </ElFormItem>
              <ElFormItem label="头像" prop="avatar">
                <ElInput v-model="user.avatar" :disabled="!isEditUser" />
              </ElFormItem>
              <ElFormItem label="身份" prop="role">
                <ElSelect placeholder="选择你的身份" v-model="user.role" :disabled="!isEditUser">
                  <el-option
                    v-for="item in roleOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </ElSelect>
              </ElFormItem>
              <ElFormItem label="电话" prop="telephone">
                <ElInput v-model="user.telephone" :disabled="!isEditUser" />
              </ElFormItem>
              <ElFormItem label="邮箱" prop="email">
                <ElInput v-model="user.email" :disabled="!isEditUser" />
              </ElFormItem>
              <ElFormItem label="住址" prop="location">
                <ElInput v-model="user.location" :disabled="!isEditUser" />
              </ElFormItem>
              <ElFormItem>
                <ElButton v-if="!isEditUser" @click="isEditUser = !isEditUser">修改</ElButton>
                <ElButton v-else @click="isEditUser = !isEditUser">放弃修改</ElButton>
                <ElButton type="primary" v-show="isEditUser" @click="updateUser">提交</ElButton>
              </ElFormItem>
            </ElForm>
          </ElCollapseItem>
        </ElCollapse>
      </ElTabPane>
      <ElTabPane label="商品管理" name="product" :style="{ height: '100vh', overflowY: 'auto' }">
        <ElRow>
          <ElCol :span="12">
            <ElInput v-model="productInputState" clearable placeholder="请输入商品ID" />
          </ElCol>
          <ElButton @click="searchProduct">查询</ElButton>
          <ElButton @click="clearSearch">清空</ElButton>
          <ElDivider v-show="searchedProduct" />
          <ProductForm
            v-model="isEditProduct"
            v-if="searchedProduct"
            :product="searchedProduct"
            @update-product="(product) => (searchedProduct = product)"
            :handleSubmit="handleUpdateProduct"
            :handle-delete="handleDeleteProduct"
          />
        </ElRow>
        <ElFormItem v-show="searchedProduct !== null" :title="searchedProduct?.title"> </ElFormItem>
        <ElDivider />
        <ElCollapse v-model="productActivateId" accordion>
          <ElCollapseItem
            v-for="[, product] in products"
            v-show="product.title.startsWith(productInputState)"
            :name="product.id"
            :key="product.id"
            :title="product.title"
          >
            <ProductForm
              :handleSubmit="handleUpdateProduct"
              :product="product"
              v-model="isEditProduct"
              @update-product="handleProductChange"
              :handle-delete="handleDeleteProduct"
            />
          </ElCollapseItem>
        </ElCollapse>
      </ElTabPane>
      <ElTabPane
        label="商品新建"
        name="createProduct"
        :style="{ height: '100vh', overflowY: 'auto' }"
      >
        <CreateProduct />
      </ElTabPane>
    </ElTabs>
  </div>
</template>

<style lang="css" scoped></style>
