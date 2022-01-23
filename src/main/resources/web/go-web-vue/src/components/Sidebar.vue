<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#324157"
      text-color="#bfcbd9"
      active-text-color="#20a0ff"
      unique-opened
      router
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template #title>
              <i :class="item.icon"></i>
              <span>{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu
                v-if="subItem.subs"
                :index="subItem.index"
                :key="subItem.index"
              >
                <template #title>{{ subItem.title }}</template>
                <el-menu-item
                  v-for="(threeItem, i) in subItem.subs"
                  :key="i"
                  :index="threeItem.index"
                >
                  {{ threeItem.title }}</el-menu-item
                >
              </el-submenu>
              <el-menu-item v-else :index="subItem.index" :key="subItem.index"
                >{{ subItem.title }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <template #title>{{ item.title }}</template>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { computed, watch } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";
export default {
  setup() {
    const dashboard = {
      icon: "el-icon-lx-home",
      index: "/dashboard",
      title: "首页",
    };
    const airManage = {
      icon: "el-icon-lx-apps",
      index: "/airManage",
      title: "航班信息管理",
    };
    const airMessage = {
      icon: "el-icon-lx-apps",
      index: "/airMessage",
      title: "航班信息",
    };
    const shipManage = {
      icon: "el-icon-lx-apps",
      index: "/shipManage",
      title: "游轮信息管理",
    };
    const shipMessage = {
      icon: "el-icon-lx-apps",
      index: "/shipMessage",
      title: "游轮信息",
    };
    const trainManage = {
      icon: "el-icon-lx-apps",
      index: "/trainManage",
      title: "火车信息管理",
    };
    const trainMessage = {
      icon: "el-icon-lx-apps",
      index: "/trainMessage",
      title: "火车信息",
    };
    const busManage = {
      icon: "el-icon-lx-apps",
      index: "/busManage",
      title: "汽车信息管理",
    };
    const busMessage = {
      icon: "el-icon-lx-apps",
      index: "/busMessage",
      title: "汽车信息",
    };
    const advertManage = {
      icon: "el-icon-lx-apps",
      index: "/advertManage",
      title: "广告信息管理",
    };
    let items = [dashboard, airMessage, trainMessage, shipMessage, busMessage];
    let userRole = localStorage.getItem("ms_userRole");
    if (userRole) {
        userRole = JSON.parse(userRole);
        userRole.map((role) => {
            if (role.authority == "ROLE_SUPER") {
                items.push(advertManage);
            }
            if (role.authority == "ROLE_AIR_ADMIN") {
                items.push(airManage);
            }
            if (role.authority == "ROLE_SEA_ADMIN") {
                items.push(shipManage);
            }
            if (role.authority == "ROLE_GROUND_ADMIN") {
                items.push(trainManage);
                items.push(busManage);
            }
        })
    }

    const route = useRoute();

    const onRoutes = computed(() => {
      return route.path;
    });

    const store = useStore();
    const collapse = computed(() => store.state.collapse);

    return {
      items,
      onRoutes,
      collapse,
    };
  },
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
