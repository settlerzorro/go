import{r as e,o as t,c as a,a as s,b as o,u as l,d as n,e as i,f as r,g as d,h as c,w as u,i as m,j as p,t as h,k as g,p as _,l as f,m as v,F as b,n as x,q as L,s as k,T as M,v as T,K as y,x as I,y as C,z as E,A as P,_ as S,B as A,R as w,C as j}from"./vendor.be3cdc88.js";var R=(e,t)=>{const a=e.__vccOpts||e;for(const[s,o]of t)a[s]=o;return a};var O=R({},[["render",function(s,o,l,n,i,r){const d=e("router-view");return t(),a(d)}]]);let D;const V={},B=function(e,t){if(!t)return e();if(void 0===D){const e=document.createElement("link").relList;D=e&&e.supports&&e.supports("modulepreload")?"modulepreload":"preload"}return Promise.all(t.map((e=>{if(e in V)return;V[e]=!0;const t=e.endsWith(".css"),a=t?'[rel="stylesheet"]':"";if(document.querySelector(`link[href="${e}"]${a}`))return;const s=document.createElement("link");return s.rel=t?"stylesheet":D,t||(s.as="script",s.crossOrigin=""),s.href=e,document.head.appendChild(s),t?new Promise(((e,t)=>{s.addEventListener("load",e),s.addEventListener("error",t)})):void 0}))).then((()=>e()))},$=s.create({timeout:0});$.interceptors.request.use((e=>e),(e=>(console.log(e),Promise.reject()))),$.interceptors.response.use((e=>{if(200===e.status)return e.data;Promise.reject()}),(e=>(console.log(e),Promise.reject())));const q=e=>$({url:"/login",method:"post",params:e}),z=()=>$({url:"/loginSuccess",method:"get",params:{}}),H=e=>$({url:"/air/getAirList",method:"post",params:e,data:e}),F=e=>$({url:"/air/insertAir",method:"post",data:e}),N=e=>$({url:"/air/updateAir",method:"post",data:e}),W=e=>$({url:"/air/deleteAir?id="+e,method:"delete",data:{}}),G=e=>$({url:"/air/insertComment",method:"post",data:e}),J=e=>$({url:"/air/deleteComment?id="+e,method:"delete",data:{}}),K=e=>$({url:"/air/like",method:"get",params:e,data:{}}),Q=e=>$({url:"/air/dislike?id="+e,method:"get",data:{}}),U=e=>$({url:"/ship/getShipList",method:"post",params:e,data:e}),X=e=>$({url:"/ship/insertShip",method:"post",data:e}),Y=e=>$({url:"/ship/updateShip",method:"post",data:e}),Z=e=>$({url:"/ship/deleteShip?id="+e,method:"delete",data:{}}),ee=e=>$({url:"/ship/insertComment",method:"post",data:e}),te=e=>$({url:"/ship/deleteComment?id="+e,method:"delete",data:{}}),ae=e=>$({url:"/ship/like",method:"get",params:e,data:{}}),se=e=>$({url:"/ship/dislike?id="+e,method:"get",data:{}}),oe=e=>$({url:"/train/getTicketList",method:"post",params:e,data:e}),le=e=>$({url:"/train/insertTrain",method:"post",data:e}),ne=e=>$({url:"/train/updateTrain",method:"post",data:e}),ie=e=>$({url:"/train/deleteTrain?id="+e,method:"delete",data:{}}),re=e=>$({url:"/train/insertComment",method:"post",data:e}),de=e=>$({url:"/train/deleteComment?id="+e,method:"delete",data:{}}),ce=e=>$({url:"/train/like",method:"get",params:e,data:{}}),ue=e=>$({url:"/train/dislike?id="+e,method:"get",data:{}}),me=e=>$({url:"/bus/getBusList",method:"post",params:e,data:e}),pe=e=>$({url:"/bus/insertBus",method:"post",data:e}),he=e=>$({url:"/bus/updateBus",method:"post",data:e}),ge=e=>$({url:"/bus/deleteBus?id="+e,method:"delete",data:{}}),_e=e=>$({url:"/bus/insertComment",method:"post",data:e}),fe=e=>$({url:"/bus/deleteComment?id="+e,method:"delete",data:{}}),ve=e=>$({url:"/bus/like",method:"get",params:e,data:{}}),be=e=>$({url:"/bus/dislike?id="+e,method:"get",data:{}});const xe={setup(){const e=o(localStorage.getItem("ms_username")),t=l(),a=n((()=>t.state.collapse)),s=()=>{t.commit("handleCollapse",!a.value)};i((()=>{document.body.clientWidth<1500&&s(),z().then((e=>{if(0==e.code){let t=e.data;localStorage.setItem("ms_username",t.username),localStorage.setItem("ms_userid",t.id),localStorage.setItem("ms_userRole",JSON.stringify(t.authorities))}else localStorage.removeItem("ms_username"),localStorage.removeItem("ms_userid"),localStorage.removeItem("ms_userRole")}))}));const d=r();return{username:e,message:2,collapse:a,collapseChage:s,handleCommand:t=>{"loginout"==t&&(localStorage.removeItem("ms_username"),localStorage.removeItem("ms_userid"),localStorage.removeItem("ms_userRole"),e.value=null,d.push("/dashboard"),window.location.reload(),$({url:"/logout",method:"get",params:{}}).then((()=>{})))},toLogin:()=>{d.push("/login")}}}},Le=e=>(_("data-v-89672a92"),e=e(),f(),e),ke={class:"header"},Me={key:0,class:"el-icon-s-fold"},Te={key:1,class:"el-icon-s-unfold"},ye=Le((()=>c("div",{class:"logo"},"大连一站式交通工具推荐系统",-1))),Ie={class:"header-right"},Ce={class:"header-user-con"},Ee={class:"el-dropdown-link"},Pe=Le((()=>c("i",{class:"el-icon-caret-bottom"},null,-1))),Se=p("退出登录");var Ae=R(xe,[["render",function(s,o,l,n,i,r){const _=e("el-dropdown-item"),f=e("el-dropdown-menu"),v=e("el-dropdown");return t(),d("div",ke,[c("div",{class:"collapse-btn",onClick:o[0]||(o[0]=(...e)=>n.collapseChage&&n.collapseChage(...e))},[n.collapse?(t(),d("i",Te)):(t(),d("i",Me))]),ye,c("div",Ie,[c("div",Ce,[n.username?(t(),a(v,{key:0,class:"user-name",trigger:"click",onCommand:n.handleCommand},{dropdown:u((()=>[m(f,null,{default:u((()=>[m(_,{command:"loginout"},{default:u((()=>[Se])),_:1})])),_:1})])),default:u((()=>[c("span",Ee,[p(h(n.username)+" ",1),Pe])])),_:1},8,["onCommand"])):g("",!0),n.username?g("",!0):(t(),d("div",{key:1,onClick:o[1]||(o[1]=(...e)=>n.toLogin&&n.toLogin(...e)),class:"loginText"},"您好，请登录"))])])])}],["__scopeId","data-v-89672a92"]]);const we={class:"sidebar"};var je=R({setup(){const e=v(),t=n((()=>e.path)),a=l();return{items:[{icon:"el-icon-lx-cascades",index:"/dashboard",title:"首页"},{icon:"el-icon-lx-cascades",index:"/airManage",title:"航班信息管理"},{icon:"el-icon-lx-cascades",index:"/airMessage",title:"航班信息"},{icon:"el-icon-lx-copy",index:"/shipManage",title:"游轮信息管理"},{icon:"el-icon-lx-copy",index:"/shipMessage",title:"游轮信息"},{icon:"el-icon-lx-copy",index:"/trainManage",title:"火车信息管理"},{icon:"el-icon-lx-copy",index:"/trainMessage",title:"火车信息"},{icon:"el-icon-lx-copy",index:"/busManage",title:"汽车信息管理"},{icon:"el-icon-lx-copy",index:"/busMessage",title:"汽车信息"}],onRoutes:t,collapse:n((()=>a.state.collapse))}}},[["render",function(s,o,l,n,i,r){const g=e("el-menu-item"),_=e("el-submenu"),f=e("el-menu");return t(),d("div",we,[m(f,{class:"sidebar-el-menu","default-active":n.onRoutes,collapse:n.collapse,"background-color":"#324157","text-color":"#bfcbd9","active-text-color":"#20a0ff","unique-opened":"",router:""},{default:u((()=>[(t(!0),d(b,null,x(n.items,(e=>(t(),d(b,null,[e.subs?(t(),a(_,{index:e.index,key:e.index},{title:u((()=>[c("i",{class:L(e.icon)},null,2),c("span",null,h(e.title),1)])),default:u((()=>[(t(!0),d(b,null,x(e.subs,(e=>(t(),d(b,null,[e.subs?(t(),a(_,{index:e.index,key:e.index},{title:u((()=>[p(h(e.title),1)])),default:u((()=>[(t(!0),d(b,null,x(e.subs,((e,s)=>(t(),a(g,{key:s,index:e.index},{default:u((()=>[p(h(e.title),1)])),_:2},1032,["index"])))),128))])),_:2},1032,["index"])):(t(),a(g,{index:e.index,key:e.index},{default:u((()=>[p(h(e.title),1)])),_:2},1032,["index"]))],64)))),256))])),_:2},1032,["index"])):(t(),a(g,{index:e.index,key:e.index},{title:u((()=>[p(h(e.title),1)])),default:u((()=>[c("i",{class:L(e.icon)},null,2)])),_:2},1032,["index"]))],64)))),256))])),_:1},8,["default-active","collapse"])])}],["__scopeId","data-v-bca51e2c"]]);const Re={setup(){const e=v(),t=r(),a=l(),s=n((()=>a.state.tagsList)),o=n((()=>s.value.length>0));k((e=>{var t;t=e,s.value.some((e=>e.path===t.fullPath))||(s.value.length>=8&&a.commit("delTagsItem",{index:0}),a.commit("setTagsItem",{name:t.name,title:t.meta.title,path:t.fullPath}))}));return{isActive:t=>t===e.fullPath,tagsList:s,showTags:o,closeTags:o=>{const l=s.value[o];a.commit("delTagsItem",{index:o});const n=s.value[o]?s.value[o]:s.value[o-1];n?l.path===e.fullPath&&t.push(n.path):t.push("/")},handleTags:o=>{"other"===o?(()=>{const t=s.value.filter((t=>t.path===e.fullPath));a.commit("closeTagsOther",t)})():(a.commit("clearTags"),t.push("/"))}}}},Oe={key:0,class:"tags"},De=["onClick"],Ve=[c("i",{class:"el-icon-close"},null,-1)];const Be={class:"about"},$e={class:"content"};const qe=[{path:"/",redirect:"/dashboard"},{path:"/",name:"Home",component:R({components:{vHeader:Ae,vSidebar:je,vTags:R(Re,[["render",function(a,s,o,l,n,i){const r=e("router-link");return l.showTags?(t(),d("div",Oe,[c("ul",null,[(t(!0),d(b,null,x(l.tagsList,((e,a)=>(t(),d("li",{class:L(["tags-li",{active:l.isActive(e.path)}]),key:a},[m(r,{to:e.path,class:"tags-li-title"},{default:u((()=>[p(h(e.title),1)])),_:2},1032,["to"]),c("span",{class:"tags-li-icon",onClick:e=>l.closeTags(a)},Ve,8,De)],2)))),128))])])):g("",!0)}]])},setup(){const e=l();return{tagsList:n((()=>e.state.tagsList.map((e=>e.name)))),collapse:n((()=>e.state.collapse))}}},[["render",function(s,o,l,n,i,r){const p=e("v-header"),h=e("v-sidebar"),g=e("v-tags"),_=e("router-view");return t(),d("div",Be,[m(p),m(h),c("div",{class:L(["content-box",{"content-collapse":n.collapse}])},[m(g),c("div",$e,[m(_,null,{default:u((({Component:e})=>[m(M,{name:"move",mode:"out-in"},{default:u((()=>[(t(),a(y,{include:n.tagsList},[(t(),a(T(e)))],1032,["include"]))])),_:2},1024)])),_:1})])],2)])}]]),children:[{path:"/dashboard",name:"dashboard",meta:{title:"系统首页"},component:()=>B((()=>import("./Dashboard.3b2497bc.js")),["./assets/Dashboard.3b2497bc.js","./assets/Dashboard.dd1f6edb.css","./assets/vendor.be3cdc88.js"])},{path:"/airManage",name:"airManage",meta:{title:"航班信息管理"},component:()=>B((()=>import("./AirManage.b274a170.js")),["./assets/AirManage.b274a170.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.be3cdc88.js"])},{path:"/airMessage",name:"airMessage",meta:{title:"航班信息"},component:()=>B((()=>import("./AirMessage.fe72430e.js")),["./assets/AirMessage.fe72430e.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.be3cdc88.js"])},{path:"/shipManage",name:"shipManage",meta:{title:"游轮信息管理"},component:()=>B((()=>import("./ShipManage.92c53afe.js")),["./assets/ShipManage.92c53afe.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.be3cdc88.js"])},{path:"/shipMessage",name:"shipMessage",meta:{title:"游轮信息"},component:()=>B((()=>import("./ShipMessage.b7f1f34c.js")),["./assets/ShipMessage.b7f1f34c.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.be3cdc88.js"])},{path:"/trainManage",name:"trainManage",meta:{title:"火车信息管理"},component:()=>B((()=>import("./TrainManage.c8ff887d.js")),["./assets/TrainManage.c8ff887d.js","./assets/TrainManage.5e6d09df.css","./assets/vendor.be3cdc88.js"])},{path:"/trainMessage",name:"trainMessage",meta:{title:"火车信息"},component:()=>B((()=>import("./TrainMessage.a3d28e7a.js")),["./assets/TrainMessage.a3d28e7a.js","./assets/TrainMessage.cc66b20f.css","./assets/vendor.be3cdc88.js"])},{path:"/busManage",name:"busManage",meta:{title:"汽车信息管理"},component:()=>B((()=>import("./BusManage.a445ad56.js")),["./assets/BusManage.a445ad56.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.be3cdc88.js"])},{path:"/busMessage",name:"busMessage",meta:{title:"汽车信息"},component:()=>B((()=>import("./BusMessage.52e2bd0d.js")),["./assets/BusMessage.52e2bd0d.js","./assets/AirManage.bf7e29a9.css","./assets/vendor.be3cdc88.js"])}]},{path:"/login",name:"Login",meta:{title:"登录"},component:()=>B((()=>import("./Login.8e1c7c1c.js")),["./assets/Login.8e1c7c1c.js","./assets/Login.eae492b6.css","./assets/vendor.be3cdc88.js"])}],ze=I({history:C(),routes:qe});ze.beforeEach(((e,t,a)=>{document.title="大连一站式交通工具推荐系统";localStorage.getItem("ms_userid")||"/airManage"!==e.path&&"/shipManage"!==e.path&&"/trainManage"!==e.path&&"/busManage"!==e.path?a():a("/login")}));var He=E({state:{tagsList:[],collapse:!1},mutations:{delTagsItem(e,t){e.tagsList.splice(t.index,1)},setTagsItem(e,t){e.tagsList.push(t)},clearTags(e){e.tagsList=[]},closeTagsOther(e,t){e.tagsList=t},closeCurrentTag(e,t){for(let a=0,s=e.tagsList.length;a<s;a++){if(e.tagsList[a].path===t.$route.fullPath){a<s-1?t.$router.push(e.tagsList[a+1].path):a>0?t.$router.push(e.tagsList[a-1].path):t.$router.push("/"),e.tagsList.splice(a,1);break}}},handleCollapse(e,t){e.collapse=t}},actions:{},modules:{}});const Fe=P({locale:S.name,fallbackLocale:A.name,messages:{"zh-cn":{i18n:{breadcrumb:"国际化产品",tips:"通过切换语言按钮，来改变当前内容的语言。",btn:"切换英文",title1:"常用用法",p1:"要是你把你的秘密告诉了风，那就别怪风把它带给树。",p2:"没有什么比信念更能支撑我们度过艰难的时光了。",p3:"只要能把自己的事做好，并让自己快乐，你就领先于大多数人了。"}},en:{i18n:{breadcrumb:"International Products",tips:"Click on the button to change the current language. ",btn:"Switch Chinese",title1:"Common usage",p1:"If you reveal your secrets to the wind you should not blame the wind for  revealing them to the trees.",p2:"Nothing can help us endure dark times better than our faith. ",p3:"If you can do what you do best and be happy, you're further along in life  than most people."}}}});const Ne=j(O);var We;(We=Ne).use(w,{locale:S}),We.use(Fe),Ne.use(He).use(ze).mount("#app");export{he as A,ge as B,fe as C,ve as D,be as E,_e as F,q as G,z as H,R as _,J as a,Q as b,G as c,W as d,U as e,X as f,H as g,Y as h,F as i,Z as j,te as k,K as l,ae as m,se as n,ee as o,oe as p,le as q,ne as r,ie as s,de as t,N as u,ce as v,ue as w,re as x,me as y,pe as z};
