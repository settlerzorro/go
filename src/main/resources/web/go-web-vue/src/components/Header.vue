<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <div class="collapse-btn" @click="collapseChage">
      <i v-if="!collapse" class="el-icon-s-fold"></i>
      <i v-else class="el-icon-s-unfold"></i>
    </div>
    <div class="logo">大连一站式交通工具推荐系统</div>
    <div class="header-right">
      <div class="header-user-con">
        <!-- 用户名下拉菜单 -->
        <template v-if="username">
          <el-dropdown
            class="user-name"
            trigger="click"
            @command="handleCommand"
          >
            <span class="el-dropdown-link">
              {{ username }}
              <i class="el-icon-caret-bottom"></i>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
        <template v-if="!username">
          <div @click="toLogin" class="loginText">您好，请登录</div>
        </template>
      </div>
    </div>
  </div>
</template>
<script>
import { computed, onMounted, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { getUserInfo, logoutApi } from "../api/index";

export default {
  setup() {
    const username = ref(localStorage.getItem("ms_username"));
    const message = 2;

    const store = useStore();
    const collapse = computed(() => store.state.collapse);
    // 侧边栏折叠
    const collapseChage = () => {
      store.commit("handleCollapse", !collapse.value);
    };

    onMounted(() => {
      if (document.body.clientWidth < 1500) {
        collapseChage();
      }
      getUserInfo().then((res) => {
        if (res.code == 0) {
          let userData = res.data;
          localStorage.setItem("ms_username", userData.username);
          localStorage.setItem("ms_userid", userData.id);
          localStorage.setItem(
            "ms_userRole",
            JSON.stringify(userData.authorities)
          );
        } else {
          localStorage.removeItem("ms_username");
          localStorage.removeItem("ms_userid");
          localStorage.removeItem("ms_userRole");
        }
      });
    });

    // 用户名下拉菜单选择事件
    const router = useRouter();
    const handleCommand = (command) => {
      if (command == "loginout") {
        router.push("/dashboard");
        // router.push("/login");
        // 后台退出
        logoutApi().then((res) => {
          if (res.code == 0) {
            username.value = null;
            localStorage.removeItem("ms_username");
            localStorage.removeItem("ms_userid");
            localStorage.removeItem("ms_userRole");
            window.location.reload();
          }
        });
      }
    };

    const toLogin = () => {
      router.push("/login");
    };

    return {
      username,
      message,
      collapse,
      collapseChage,
      handleCommand,
      toLogin,
    };
  },
};
</script>
<style scoped>
.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}
.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}
.header-right {
  float: right;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}
.loginText {
  font-size: 14px;
  cursor: pointer;
}
</style>
