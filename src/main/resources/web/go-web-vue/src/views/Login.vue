<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">大连一站式交通工具推荐系统</div>
      <el-form
        :model="param"
        :rules="rules"
        ref="login"
        label-width="0px"
        class="ms-content"
      >
        <el-form-item prop="username">
          <el-input v-model="param.username" placeholder="用户名">
            <template #prepend>
              <el-button icon="el-icon-user"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            placeholder="密码"
            v-model="param.password"
            @keyup.enter="submitForm()"
          >
            <template #prepend>
              <el-button icon="el-icon-lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <div style="text-align: right; margin-bottom: 8px">
          <p style="font-size: 12px; cursor: pointer;color: #3882E5;" @click="toRegister">注册</p>
        </div>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm()">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { loginApi, getUserInfo } from "../api/index";

export default {
  setup() {
    localStorage.removeItem("ms_username");
    localStorage.removeItem("ms_userid");
    localStorage.removeItem("ms_userRole");
    
    const router = useRouter();
    const param = reactive({
      username: "",
      password: "",
    });

    const rules = {
      username: [
        {
          required: true,
          message: "请输入用户名",
          trigger: "blur",
        },
      ],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
    };
    const login = ref(null);
    const submitForm = () => {
      login.value.validate((valid) => {
        if (valid) {
          loginApi(param).then((res) => {
            if (res.code == 0) {
              getUserInfo().then((res) => {
                if (res.code == 0) {
                  let userData = res.data;
                  ElMessage.success("登录成功");
                  localStorage.setItem("ms_username", userData.username);
                  localStorage.setItem("ms_userid", userData.id);
                  localStorage.setItem(
                    "ms_userRole",
                    JSON.stringify(userData.authorities)
                  );
                  router.push("/");
                } else {
                  ElMessage.error(res.message);
                }
              });
            } else {
              ElMessage.error(res.message);
            }
          });
        } else {
          ElMessage.error("请输入用户名或密码");
          return false;
        }
      });
    };

    const store = useStore();
    store.commit("clearTags");

    const toRegister = () => {
      router.push("/register");
    }

    return {
      param,
      rules,
      login,
      submitForm,
      toRegister
    };
  },
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(../assets/img/login-bg.jpeg);
  background-repeat: no-repeat;
  /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
    background-attachment: fixed;
    /* 让背景图基于容器大小伸缩 */
    background-size: cover;
  background-position: center center;
}
.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #303133;
  border-bottom: 1px solid #ddd;
}
.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.3);
  overflow: hidden;
}
.ms-content {
  padding: 30px 30px;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}
</style>