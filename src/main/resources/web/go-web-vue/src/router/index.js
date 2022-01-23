import { createRouter, createWebHashHistory } from "vue-router";
import Home from "../views/Home.vue";

const routes = [
    {
        path: '/',
        redirect: '/dashboard'
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: "/dashboard",
                name: "dashboard",
                meta: {
                    title: '首页'
                },
                component: () => import( /* webpackChunkName: "dashboard" */ "../views/Dashboard.vue")
            }, {
                path: "/airManage",
                name: "airManage",
                meta: {
                    title: '航班信息管理'
                },
                component: () => import( /* webpackChunkName: "airManage" */ "../views/AirManage.vue")
            }, {
                path: "/airMessage",
                name: "airMessage",
                meta: {
                    title: '航班信息'
                },
                component: () => import( /* webpackChunkName: "airMessage" */ "../views/AirMessage.vue")
            }, {
                path: "/shipManage",
                name: "shipManage",
                meta: {
                    title: '游轮信息管理'
                },
                component: () => import( /* webpackChunkName: "shipManage" */ "../views/ShipManage.vue")
            }, {
                path: "/shipMessage",
                name: "shipMessage",
                meta: {
                    title: '游轮信息'
                },
                component: () => import( /* webpackChunkName: "shipMessage" */ "../views/ShipMessage.vue")
            }, {
                path: "/trainManage",
                name: "trainManage",
                meta: {
                    title: '火车信息管理'
                },
                component: () => import( /* webpackChunkName: "trainManage" */ "../views/TrainManage.vue")
            }, {
                path: "/trainMessage",
                name: "trainMessage",
                meta: {
                    title: '火车信息'
                },
                component: () => import( /* webpackChunkName: "trainMessage" */ "../views/TrainMessage.vue")
            }, {
                path: "/busManage",
                name: "busManage",
                meta: {
                    title: '汽车信息管理'
                },
                component: () => import( /* webpackChunkName: "busManage" */ "../views/BusManage.vue")
            }, {
                path: "/busMessage",
                name: "busMessage",
                meta: {
                    title: '汽车信息'
                },
                component: () => import( /* webpackChunkName: "busMessage" */ "../views/BusMessage.vue")
            }, {
                path: "/advertManage",
                name: "advertManage",
                meta: {
                    title: '广告信息管理'
                },
                component: () => import( /* webpackChunkName: "advertManage" */ "../views/AdvertManage.vue")
            },
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录'
        },
        component: () => import( /* webpackChunkName: "login" */ "../views/Login.vue")
    }, {
        path: "/register",
        name: "Register",
        meta: {
            title: '注册'
        },
        component: () => import( /* webpackChunkName: "register" */ "../views/Register.vue")
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    document.title = `大连一站式交通工具推荐系统`;
    const user = localStorage.getItem('ms_userid');
    if (!user && (to.path === '/airManage' || to.path === '/shipManage' || to.path === '/trainManage' || to.path === '/busManage' || to.path === '/advertManage')) {
        next('/login');
    } else {
        next();
    }
});

export default router;