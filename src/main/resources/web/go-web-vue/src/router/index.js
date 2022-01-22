import {createRouter, createWebHashHistory} from "vue-router";
import Home from "../views/Home.vue";

const routes = [{
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: "/airManage",
                name: "airManage",
                meta: {
                    title: '航班信息管理'
                },
                component: () => import ( /* webpackChunkName: "airManage" */ "../views/AirManage.vue")
            }, {
                path: "/shipManage",
                name: "shipManage",
                meta: {
                    title: '游轮信息管理'
                },
                component: () => import ( /* webpackChunkName: "shipManage" */ "../views/ShipManage.vue")
            }, {
                path: "/trainManage",
                name: "trainManage",
                meta: {
                    title: '火车信息管理'
                },
                component: () => import ( /* webpackChunkName: "trainManage" */ "../views/TrainManage.vue")
            }, {
                path: "/busManage",
                name: "busManage",
                meta: {
                    title: '汽车信息管理'
                },
                component: () => import ( /* webpackChunkName: "busManage" */ "../views/BusManage.vue")
            },
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录'
        },
        component: () => import ( /* webpackChunkName: "login" */ "../views/Login.vue")
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | vue-manage-system`;
    const role = localStorage.getItem('ms_username');
    if (!role && to.path !== '/login') {
        next('/login');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin'
            ? next()
            : next('/403');
    } else {
        next();
    }
});

export default router;