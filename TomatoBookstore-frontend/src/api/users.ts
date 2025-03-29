import { reactive } from "vue";
import { USER_MODULE } from "./_prefix";
import { axios } from "./request";
import type { ResultVO } from "./vo";
import type { FormRules } from "element-plus";

export interface LoginForm {
  username: string;
  password: string;
}

export const userLogin: (loginForm: LoginForm) => Promise<string> = async (
  loginForm: LoginForm
) => {
  try {
    const resp = await axios.post(`${USER_MODULE}/login`, loginForm);
    const result: ResultVO<string> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Login Error: ${error}`);
  }
};

export interface RegisterForm {
  username: string;
  password: string;
  name: string;
  avatar: string | null;
  role: string;
  telephone: string | null;
  email: string | null;
  location: string | null;
}

export const userRegister: (registerForm: RegisterForm) => Promise<string> = async (
  registerForm: RegisterForm
) => {
  try {
    const resp = await axios.post(`${USER_MODULE}`, registerForm);
    const result: ResultVO<string> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Register Error: ${error}`);
  }
};

const checkEmail = (_: unknown, value: string, callback: (error?: Error) => void) => {
  if (!value) {
    callback();
  }
  setTimeout(() => {
    const emailRegex = /^(?!.*\.\.)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (emailRegex.test(value)) {
      callback();
    }
    callback(new Error("Invalid email address"));
  }, 500);
};

const checkTelephone = (_: unknown, value: string, callback: (error?: Error) => void) => {
  if (!value) {
    callback();
  }
  setTimeout(() => {
    if (value.length != 11 || value[0] !== "1") {
      callback(new Error("Invalid telephone number"));
    }
    callback();
  }, 500);
};

export const registerRules = reactive<FormRules<RegisterForm>>({
  username: [{ required: true, message: "Please input your username", trigger: "blur" }],
  password: [
    { required: true, message: "Please input your password", trigger: "blur" },
    { min: 6, max: 18, message: "Password length is between 6 and 18", trigger: "blur" },
  ],
  name: [{ required: true, message: "Please input your name", trigger: "blur" }],
  role: [{ required: true, message: "Please select your role", trigger: "blur" }],
  telephone: [{ validator: checkTelephone, trigger: "blur" }],
  email: [{ validator: checkEmail, trigger: "blur" }],
});

export interface UserInfo {
  username: string;
  password?: string;
  name: string;
  role: string;
  avatar?: string;
  telephone?: string;
  email?: string;
  location?: string;
}

export const getUserByUsername: (username: string) => Promise<UserInfo> = async (
  username: string
) => {
  try {
    const resp = await axios.get(`${USER_MODULE}/${username}`);
    const result: ResultVO<UserInfo> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Get UserInfo with username ${username} Error: ${error}`);
  }
};

export const putUserRules = reactive<FormRules<UserInfo>>({
  username: [{ required: true, message: "Please input your username", trigger: "blur" }],
  password: [
    { required: true, message: "Please input your password", trigger: "blur" },
    { min: 6, max: 18, message: "Password length is between 6 and 18", trigger: "blur" },
  ],
  name: [{ required: true, message: "Please input your name", trigger: "blur" }],
  role: [{ required: true, message: "Please select your role", trigger: "blur" }],
  telephone: [{ validator: checkTelephone, trigger: "blur" }],
  email: [{ validator: checkEmail, trigger: "blur" }],
});

export const putUser: (user: UserInfo) => Promise<string> = async (user: UserInfo) => {
  try {
    const resp = await axios.put(`${USER_MODULE}`, user);
    const result: ResultVO<string> = resp.data;
    if (result.code === 200) {
      return result.data;
    }
    throw Error(result.msg);
  } catch (error) {
    throw Error(`Update UserInfo Error: ${error}`);
  }
};
