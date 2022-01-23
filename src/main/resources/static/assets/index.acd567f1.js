import{r as e,o as t,c as a,a as s,b as o,u as l,d as n,e as i,f as r,g as d,h as u,w as m,i as c,j as p,t as h,k as g,p as _,l as v,m as b,F as f,n as x,q as L,s as M,T as I,v as T,K as k,x as E,y as A,z as y,A as C,_ as R,B as S,R as P,C as O}from"./vendor.c7f77ec0.js";var w=(e,t)=>{const a=e.__vccOpts||e;for(const[s,o]of t)a[s]=o;return a};var j=w({},[["render",function(s,o,l,n,i,r){const d=e("router-view");return t(),a(d)}]]);let D;const V={},B=function(e,t){if(!t)return e();if(void 0===D){const e=document.createElement("link").relList;D=e&&e.supports&&e.supports("modulepreload")?"modulepreload":"preload"}return Promise.all(t.map((e=>{if(e in V)return;V[e]=!0;const t=e.endsWith(".css"),a=t?'[rel="stylesheet"]':"";if(document.querySelector(`link[href="${e}"]${a}`))return;const s=document.createElement("link");return s.rel=t?"stylesheet":D,t||(s.as="script",s.crossOrigin=""),s.href=e,document.head.appendChild(s),t?new Promise(((e,t)=>{s.addEventListener("load",e),s.addEventListener("error",t)})):void 0}))).then((()=>e()))},N=s.create({timeout:0});N.interceptors.request.use((e=>e),(e=>(console.log(e),Promise.reject()))),N.interceptors.response.use((e=>{if(200===e.status)return e.data;Promise.reject()}),(e=>(console.log(e),Promise.reject())));const $=e=>N({url:"/login",method:"post",params:e}),q=e=>N({url:"/register",method:"post",data:e}),z=()=>N({url:"/loginSuccess",method:"get",params:{}}),F=e=>N({url:"/air/getAirList",method:"post",params:e,data:e}),H=e=>N({url:"/air/insertAir",method:"post",data:e}),J=e=>N({url:"/air/updateAir",method:"post",data:e}),U=e=>N({url:"/air/deleteAir?id="+e,method:"delete",data:{}}),G=e=>N({url:"/air/insertComment",method:"post",data:e}),K=e=>N({url:"/air/deleteComment?id="+e,method:"delete",data:{}}),W=e=>N({url:"/air/like",method:"get",params:e,data:{}}),Q=e=>N({url:"/air/dislike?id="+e,method:"get",data:{}}),X=e=>N({url:"/ship/getShipList",method:"post",params:e,data:e}),Y=e=>N({url:"/ship/insertShip",method:"post",data:e}),Z=e=>N({url:"/ship/updateShip",method:"post",data:e}),ee=e=>N({url:"/ship/deleteShip?id="+e,method:"delete",data:{}}),te=e=>N({url:"/ship/insertComment",method:"post",data:e}),ae=e=>N({url:"/ship/deleteComment?id="+e,method:"delete",data:{}}),se=e=>N({url:"/ship/like",method:"get",params:e,data:{}}),oe=e=>N({url:"/ship/dislike?id="+e,method:"get",data:{}}),le=e=>N({url:"/train/getTicketList",method:"post",params:e,data:e}),ne=e=>N({url:"/train/insertTrain",method:"post",data:e}),ie=e=>N({url:"/train/updateTrain",method:"post",data:e}),re=e=>N({url:"/train/deleteTrain?id="+e,method:"delete",data:{}}),de=e=>N({url:"/train/insertComment",method:"post",data:e}),ue=e=>N({url:"/train/deleteComment?id="+e,method:"delete",data:{}}),me=e=>N({url:"/train/like",method:"get",params:e,data:{}}),ce=e=>N({url:"/train/dislike?id="+e,method:"get",data:{}}),pe=e=>N({url:"/bus/getBusList",method:"post",params:e,data:e}),he=e=>N({url:"/bus/insertBus",method:"post",data:e}),ge=e=>N({url:"/bus/updateBus",method:"post",data:e}),_e=e=>N({url:"/bus/deleteBus?id="+e,method:"delete",data:{}}),ve=e=>N({url:"/bus/insertComment",method:"post",data:e}),be=e=>N({url:"/bus/deleteComment?id="+e,method:"delete",data:{}}),fe=e=>N({url:"/bus/like",method:"get",params:e,data:{}}),xe=e=>N({url:"/bus/dislike?id="+e,method:"get",data:{}}),Le=()=>N({url:"/advert/getAdList",method:"post",data:{}}),Me=(e,t)=>N({url:"/advert/insertAdvert",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded; charset=UTF-8"},data:t,params:e}),Ie=e=>N({url:"/advert/deleteAdvert?id="+e,method:"delete",data:{}}),Te=e=>N({url:"/advert/showAd?id="+e,method:"post",data:{}}),ke=e=>N({url:"/advert/hideAd?id="+e,method:"post",data:{}}),Ee=e=>N({url:"/transportApi/getTransportList",method:"get",params:e,data:{}});const Ae={setup(){const e=o(localStorage.getItem("ms_username")),t=l(),a=n((()=>t.state.collapse)),s=()=>{t.commit("handleCollapse",!a.value)};i((()=>{document.body.clientWidth<1500&&s(),z().then((e=>{if(0==e.code){let t=e.data;localStorage.setItem("ms_username",t.username),localStorage.setItem("ms_userid",t.id),localStorage.setItem("ms_userRole",JSON.stringify(t.authorities))}else localStorage.removeItem("ms_username"),localStorage.removeItem("ms_userid"),localStorage.removeItem("ms_userRole")}))}));const d=r();return{username:e,message:2,collapse:a,collapseChage:s,handleCommand:t=>{"loginout"==t&&(d.push("/dashboard"),N({url:"/logout",method:"get",params:{}}).then((t=>{0==t.code&&(e.value=null,localStorage.removeItem("ms_username"),localStorage.removeItem("ms_userid"),localStorage.removeItem("ms_userRole"),window.location.reload())})))},toLogin:()=>{d.push("/login")}}}},ye=e=>(_("data-v-458017a4"),e=e(),v(),e),Ce={class:"header"},Re={key:0,class:"el-icon-s-fold"},Se={key:1,class:"el-icon-s-unfold"},Pe=ye((()=>u("div",{class:"logo"},"大连一站式交通工具推荐系统",-1))),Oe={class:"header-right"},we={class:"header-user-con"},je={class:"el-dropdown-link"},De=ye((()=>u("i",{class:"el-icon-caret-bottom"},null,-1))),Ve=p("退出登录");var Be=w(Ae,[["render",function(s,o,l,n,i,r){const _=e("el-dropdown-item"),v=e("el-dropdown-menu"),b=e("el-dropdown");return t(),d("div",Ce,[u("div",{class:"collapse-btn",onClick:o[0]||(o[0]=(...e)=>n.collapseChage&&n.collapseChage(...e))},[n.collapse?(t(),d("i",Se)):(t(),d("i",Re))]),Pe,u("div",Oe,[u("div",we,[n.username?(t(),a(b,{key:0,class:"user-name",trigger:"click",onCommand:n.handleCommand},{dropdown:m((()=>[c(v,null,{default:m((()=>[c(_,{command:"loginout"},{default:m((()=>[Ve])),_:1})])),_:1})])),default:m((()=>[u("span",je,[p(h(n.username)+" ",1),De])])),_:1},8,["onCommand"])):g("",!0),n.username?g("",!0):(t(),d("div",{key:1,onClick:o[1]||(o[1]=(...e)=>n.toLogin&&n.toLogin(...e)),class:"loginText"},"您好，请登录"))])])])}],["__scopeId","data-v-458017a4"]]);const Ne={class:"sidebar"};var $e=w({setup(){const e={icon:"el-icon-lx-apps",index:"/airManage",title:"航班信息管理"},t={icon:"el-icon-lx-apps",index:"/shipManage",title:"游轮信息管理"},a={icon:"el-icon-lx-apps",index:"/trainManage",title:"火车信息管理"},s={icon:"el-icon-lx-apps",index:"/busManage",title:"汽车信息管理"},o={icon:"el-icon-lx-apps",index:"/advertManage",title:"广告信息管理"};let i=[{icon:"el-icon-lx-home",index:"/dashboard",title:"首页"},{icon:"el-icon-lx-apps",index:"/airMessage",title:"航班信息"},{icon:"el-icon-lx-apps",index:"/trainMessage",title:"火车信息"},{icon:"el-icon-lx-apps",index:"/shipMessage",title:"游轮信息"},{icon:"el-icon-lx-apps",index:"/busMessage",title:"汽车信息"}],r=localStorage.getItem("ms_userRole");r&&(r=JSON.parse(r),r.map((l=>{"ROLE_SUPER"==l.authority&&i.push(o),"ROLE_AIR_ADMIN"==l.authority&&i.push(e),"ROLE_SEA_ADMIN"==l.authority&&i.push(t),"ROLE_GROUND_ADMIN"==l.authority&&(i.push(a),i.push(s))})));const d=b(),u=n((()=>d.path)),m=l(),c=n((()=>m.state.collapse));return{items:i,onRoutes:u,collapse:c}}},[["render",function(s,o,l,n,i,r){const g=e("el-menu-item"),_=e("el-submenu"),v=e("el-menu");return t(),d("div",Ne,[c(v,{class:"sidebar-el-menu","default-active":n.onRoutes,collapse:n.collapse,"background-color":"#324157","text-color":"#bfcbd9","active-text-color":"#20a0ff","unique-opened":"",router:""},{default:m((()=>[(t(!0),d(f,null,x(n.items,(e=>(t(),d(f,null,[e.subs?(t(),a(_,{index:e.index,key:e.index},{title:m((()=>[u("i",{class:L(e.icon)},null,2),u("span",null,h(e.title),1)])),default:m((()=>[(t(!0),d(f,null,x(e.subs,(e=>(t(),d(f,null,[e.subs?(t(),a(_,{index:e.index,key:e.index},{title:m((()=>[p(h(e.title),1)])),default:m((()=>[(t(!0),d(f,null,x(e.subs,((e,s)=>(t(),a(g,{key:s,index:e.index},{default:m((()=>[p(h(e.title),1)])),_:2},1032,["index"])))),128))])),_:2},1032,["index"])):(t(),a(g,{index:e.index,key:e.index},{default:m((()=>[p(h(e.title),1)])),_:2},1032,["index"]))],64)))),256))])),_:2},1032,["index"])):(t(),a(g,{index:e.index,key:e.index},{title:m((()=>[p(h(e.title),1)])),default:m((()=>[u("i",{class:L(e.icon)},null,2)])),_:2},1032,["index"]))],64)))),256))])),_:1},8,["default-active","collapse"])])}],["__scopeId","data-v-067d6ad0"]]);const qe={setup(){const e=b(),t=r(),a=l(),s=n((()=>a.state.tagsList)),o=n((()=>s.value.length>0));M((e=>{var t;t=e,s.value.some((e=>e.path===t.fullPath))||(s.value.length>=8&&a.commit("delTagsItem",{index:0}),a.commit("setTagsItem",{name:t.name,title:t.meta.title,path:t.fullPath}))}));return{isActive:t=>t===e.fullPath,tagsList:s,showTags:o,closeTags:o=>{const l=s.value[o];a.commit("delTagsItem",{index:o});const n=s.value[o]?s.value[o]:s.value[o-1];n?l.path===e.fullPath&&t.push(n.path):t.push("/")},handleTags:o=>{"other"===o?(()=>{const t=s.value.filter((t=>t.path===e.fullPath));a.commit("closeTagsOther",t)})():(a.commit("clearTags"),t.push("/"))}}}},ze={key:0,class:"tags"},Fe=["onClick"],He=[u("i",{class:"el-icon-close"},null,-1)];const Je={class:"about"},Ue={class:"content"};const Ge=[{path:"/",redirect:"/dashboard"},{path:"/",name:"Home",component:w({components:{vHeader:Be,vSidebar:$e,vTags:w(qe,[["render",function(a,s,o,l,n,i){const r=e("router-link");return l.showTags?(t(),d("div",ze,[u("ul",null,[(t(!0),d(f,null,x(l.tagsList,((e,a)=>(t(),d("li",{class:L(["tags-li",{active:l.isActive(e.path)}]),key:a},[c(r,{to:e.path,class:"tags-li-title"},{default:m((()=>[p(h(e.title),1)])),_:2},1032,["to"]),u("span",{class:"tags-li-icon",onClick:e=>l.closeTags(a)},He,8,Fe)],2)))),128))])])):g("",!0)}]])},setup(){const e=l();return{tagsList:n((()=>e.state.tagsList.map((e=>e.name)))),collapse:n((()=>e.state.collapse))}}},[["render",function(s,o,l,n,i,r){const p=e("v-header"),h=e("v-sidebar"),g=e("v-tags"),_=e("router-view");return t(),d("div",Je,[c(p),c(h),u("div",{class:L(["content-box",{"content-collapse":n.collapse}])},[c(g),u("div",Ue,[c(_,null,{default:m((({Component:e})=>[c(I,{name:"move",mode:"out-in"},{default:m((()=>[(t(),a(k,{include:n.tagsList},[(t(),a(T(e)))],1032,["include"]))])),_:2},1024)])),_:1})])],2)])}]]),children:[{path:"/dashboard",name:"dashboard",meta:{title:"首页"},component:()=>B((()=>import("./Dashboard.8db6881c.js")),["./assets/Dashboard.8db6881c.js","./assets/Dashboard.e4be7157.css","./assets/vendor.c7f77ec0.js"])},{path:"/airManage",name:"airManage",meta:{title:"航班信息管理"},component:()=>B((()=>import("./AirManage.e0a6541c.js")),["./assets/AirManage.e0a6541c.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.c7f77ec0.js"])},{path:"/airMessage",name:"airMessage",meta:{title:"航班信息"},component:()=>B((()=>import("./AirMessage.86e9bd4a.js")),["./assets/AirMessage.86e9bd4a.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.c7f77ec0.js"])},{path:"/shipManage",name:"shipManage",meta:{title:"游轮信息管理"},component:()=>B((()=>import("./ShipManage.c966ad94.js")),["./assets/ShipManage.c966ad94.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.c7f77ec0.js"])},{path:"/shipMessage",name:"shipMessage",meta:{title:"游轮信息"},component:()=>B((()=>import("./ShipMessage.aa7bd588.js")),["./assets/ShipMessage.aa7bd588.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.c7f77ec0.js"])},{path:"/trainManage",name:"trainManage",meta:{title:"火车信息管理"},component:()=>B((()=>import("./TrainManage.79c95aad.js")),["./assets/TrainManage.79c95aad.js","./assets/TrainManage.5e6d09df.css","./assets/vendor.c7f77ec0.js"])},{path:"/trainMessage",name:"trainMessage",meta:{title:"火车信息"},component:()=>B((()=>import("./TrainMessage.6a1d245e.js")),["./assets/TrainMessage.6a1d245e.js","./assets/TrainMessage.cc66b20f.css","./assets/vendor.c7f77ec0.js"])},{path:"/busManage",name:"busManage",meta:{title:"汽车信息管理"},component:()=>B((()=>import("./BusManage.42fc9c04.js")),["./assets/BusManage.42fc9c04.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.c7f77ec0.js"])},{path:"/busMessage",name:"busMessage",meta:{title:"汽车信息"},component:()=>B((()=>import("./BusMessage.20d41d57.js")),["./assets/BusMessage.20d41d57.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.c7f77ec0.js"])},{path:"/advertManage",name:"advertManage",meta:{title:"广告信息管理"},component:()=>B((()=>import("./AdvertManage.6e9bec86.js")),["./assets/AdvertManage.6e9bec86.js","./assets/AdvertManage.f2c394b6.css","./assets/vendor.c7f77ec0.js"])}]},{path:"/login",name:"Login",meta:{title:"登录"},component:()=>B((()=>import("./Login.a2decb8a.js")),["./assets/Login.a2decb8a.js","./assets/Login.7e5f3e86.css","./assets/vendor.c7f77ec0.js"])},{path:"/register",name:"Register",meta:{title:"注册"},component:()=>B((()=>import("./Register.b41b3147.js")),["./assets/Register.b41b3147.js","./assets/Register.1e13febc.css","./assets/vendor.c7f77ec0.js"])}],Ke=E({history:A(),routes:Ge});Ke.beforeEach(((e,t,a)=>{document.title="大连一站式交通工具推荐系统";localStorage.getItem("ms_userid")||"/airManage"!==e.path&&"/shipManage"!==e.path&&"/trainManage"!==e.path&&"/busManage"!==e.path&&"/advertManage"!==e.path?a():a("/login")}));var We=y({state:{tagsList:[],collapse:!1},mutations:{delTagsItem(e,t){e.tagsList.splice(t.index,1)},setTagsItem(e,t){e.tagsList.push(t)},clearTags(e){e.tagsList=[]},closeTagsOther(e,t){e.tagsList=t},closeCurrentTag(e,t){for(let a=0,s=e.tagsList.length;a<s;a++){if(e.tagsList[a].path===t.$route.fullPath){a<s-1?t.$router.push(e.tagsList[a+1].path):a>0?t.$router.push(e.tagsList[a-1].path):t.$router.push("/"),e.tagsList.splice(a,1);break}}},handleCollapse(e,t){e.collapse=t}},actions:{},modules:{}});const Qe=C({locale:R.name,fallbackLocale:S.name,messages:{"zh-cn":{i18n:{breadcrumb:"国际化产品",tips:"通过切换语言按钮，来改变当前内容的语言。",btn:"切换英文",title1:"常用用法",p1:"要是你把你的秘密告诉了风，那就别怪风把它带给树。",p2:"没有什么比信念更能支撑我们度过艰难的时光了。",p3:"只要能把自己的事做好，并让自己快乐，你就领先于大多数人了。"}},en:{i18n:{breadcrumb:"International Products",tips:"Click on the button to change the current language. ",btn:"Switch Chinese",title1:"Common usage",p1:"If you reveal your secrets to the wind you should not blame the wind for  revealing them to the trees.",p2:"Nothing can help us endure dark times better than our faith. ",p3:"If you can do what you do best and be happy, you're further along in life  than most people."}}}});const Xe=O(j);var Ye;(Ye=Xe).use(P,{locale:R}),Ye.use(Qe),Xe.use(We).use(Ke).mount("#app");export{ie as A,re as B,ue as C,pe as D,he as E,ge as F,_e as G,be as H,Me as I,Ie as J,ke as K,Te as L,$ as M,z as N,q as O,w as _,Ee as a,de as b,G as c,te as d,ve as e,me as f,Le as g,se as h,fe as i,Q as j,ce as k,W as l,oe as m,xe as n,F as o,H as p,U as q,K as r,X as s,Y as t,J as u,Z as v,ee as w,ae as x,le as y,ne as z};
