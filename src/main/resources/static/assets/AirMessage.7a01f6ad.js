import{g as e,b as a,d as l,r as t,h as o,o as n,i as r,j as i,k as d,w as s,n as u,c,m,t as p,F as h,l as b,H as g}from"./vendor.ce047f19.js";import{_ as f,o as x,l as y,j as k,c as v}from"./index.3af46441.js";const w={name:"airMessage",setup(){const t=e(),o=a(localStorage.getItem("ms_userid")),n=l({oapName:"大连",aapName:null,fromTime:null,userId:o,pageIndex:1,pageSize:5}),r=a(""),i=a(!1),d=a([]),s=a([]),u=a(0),c=()=>{n.aapName&&n.fromTime&&(i.value=!0,x(n).then((e=>{if(0==e.code){if(d.value=e.data,u.value=e.data.length,1==m.value)for(let a=0;a<e.data.length;a++)if(e.data[a].id==h){b(a,e.data[a]);break}}else g.error(e.message);i.value=!1})))};c();const m=a(!1),p=(e,a,l)=>{l.map((e=>{k(e.id).then((e=>{0==e.code?(g.success("取消收藏成功"),c()):g.error("取消收藏失败")})).catch((()=>{g.error("取消收藏失败")}))}))};let h="";const b=(e,a)=>{m.value=!0,h=a.id,a.commentAairs&&(s.value=a.commentAairs)};return{userId:o,loading:i,query:n,tableData:d,commentTableData:s,comment:r,pageTotal:u,commentVisible:m,handleSearch:()=>{n.pageIndex=1,c()},handlePageChange:e=>{n.pageIndex=e},viewComment:b,closeComment:()=>{m.value=!1,c()},handleLike:(e,a)=>{if(!o.value)return void t.push("/login");let l={userId:o.value,airId:a.id};a.likeAirs.length>0?p(0,{},a.likeAirs):y(l).then((e=>{0==e.code?(g.success("收藏成功"),c()):g.error("收藏失败")})).catch((()=>{g.error("收藏失败")}))},handleDisLike:p,handleComment:(e,a)=>{if(!r.value)return void g.warning("请输入评论内容，不要无故刷评论");let l={userId:o.value,airId:h,content:r.value};v(l).then((e=>{0==e.code?(g.success("评论成功"),c()):g.error("评论失败")})).catch((()=>{g.error("评论失败")}))},handleBuy:(e,a)=>{window.open(a.buyUrl,"_blank")},handleGetLike:(e,a)=>!!(a.likeAirs&&a.likeAirs.length>0)}}},C={class:"crumbs"},_=i("i",{class:"el-icon-lx-cascades"},null,-1),I=m(" 航班信息 "),V={class:"container"},T={class:"handle-box"},q=m("搜索"),D=m("评论 "),S=m("购买地址"),Y={class:"pagination"},N={style:{"text-align":"right"}},$=m("评论");var z=f(w,[["render",function(e,a,l,g,f,x){const y=t("el-breadcrumb-item"),k=t("el-breadcrumb"),v=t("el-input"),w=t("el-date-picker"),z=t("el-button"),A=t("el-table-column"),L=t("el-table"),B=t("el-pagination"),M=t("el-dialog"),U=o("loading");return n(),r("div",null,[i("div",C,[d(k,{separator:"/"},{default:s((()=>[d(y,null,{default:s((()=>[_,I])),_:1})])),_:1})]),i("div",V,[i("div",T,[d(v,{modelValue:g.query.aapName,"onUpdate:modelValue":a[0]||(a[0]=e=>g.query.aapName=e),placeholder:"到达站点",class:"handle-input mr10"},null,8,["modelValue"]),d(w,{modelValue:g.query.fromTime,"onUpdate:modelValue":a[1]||(a[1]=e=>g.query.fromTime=e),placeholder:"出发日",type:"date",format:"YYYY-MM-DD","value-format":"YYYY-MM-DD",class:"mr10"},null,8,["modelValue"]),d(z,{type:"primary",icon:"el-icon-search",onClick:g.handleSearch},{default:s((()=>[q])),_:1},8,["onClick"])]),u((n(),c(L,{data:g.tableData.slice((g.query.pageIndex-1)*g.query.pageSize,g.query.pageIndex*g.query.pageSize),border:"",class:"table",ref:"multipleTable","header-cell-class-name":"table-header"},{default:s((()=>[d(A,{prop:"index",label:"序号",width:"50"},{default:s((e=>[m(p(e.$index+1),1)])),_:1}),d(A,{prop:"oapName",label:"出发站点"}),d(A,{prop:"aapName",label:"到达站点"}),d(A,{prop:"si",label:"经停",width:"80"},{default:s((e=>[m(p(e.row.si||"无"),1)])),_:1}),d(A,{prop:"flyOffOnlyTime",label:"出发时刻",width:"100"}),d(A,{prop:"arrivalOnlyTime",label:"到达时刻",width:"100"}),d(A,{prop:"useTime",label:"总用时",sortable:"",width:"100"}),d(A,{prop:"aep",label:"价格",sortable:"",width:"100"}),d(A,{prop:"weather",label:"天气",width:"100"}),d(A,{prop:"scenicSpots",label:"景点",width:"100"}),d(A,{label:"操作",width:"250",align:"center"},{default:s((e=>[d(z,{type:"text",icon:"el-icon-lx-comment",class:"opButton",onClick:a=>g.viewComment(e.$index,e.row)},{default:s((()=>[D])),_:2},1032,["onClick"]),m(" "+p()+" ",1),d(z,{type:"text",icon:g.handleGetLike(e.$index,e.row)?"el-icon-lx-likefill":"el-icon-lx-like",class:"opButton red",onClick:a=>g.handleLike(e.$index,e.row)},{default:s((()=>[m(p(g.handleGetLike(e.$index,e.row)?"已收藏":"收藏"),1)])),_:2},1032,["icon","onClick"]),d(z,{type:"text",icon:"el-icon-lx-share",class:"opButton",onClick:a=>g.handleBuy(e.$index,e.row)},{default:s((()=>[S])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[U,g.loading]]),i("div",Y,[d(B,{background:"",layout:"total, prev, pager, next","current-page":g.query.pageIndex,"page-size":g.query.pageSize,total:g.pageTotal,onCurrentChange:g.handlePageChange},null,8,["current-page","page-size","total","onCurrentChange"])])]),d(M,{title:"评论",modelValue:g.commentVisible,"onUpdate:modelValue":a[3]||(a[3]=e=>g.commentVisible=e),width:"60%",onClose:g.closeComment},{default:s((()=>[d(L,{data:g.commentTableData,"max-height":"300",border:"",class:"table",ref:"commentTable","header-cell-class-name":"table-header"},{default:s((()=>[d(A,{prop:"index",label:"序号",width:"50"},{default:s((e=>[m(p(e.$index+1),1)])),_:1}),d(A,{prop:"content",label:"评论内容"}),d(A,{prop:"userId",label:"评论用户"},{default:s((e=>[m(" 用户"+p(e.row.userId),1)])),_:1}),d(A,{prop:"time",label:"评论时间"})])),_:1},8,["data"]),g.userId?(n(),r(h,{key:0},[d(v,{modelValue:g.comment,"onUpdate:modelValue":a[2]||(a[2]=e=>g.comment=e),type:"textarea",placeholder:"请输入评论",style:{margin:"16px 0"}},null,8,["modelValue"]),i("div",N,[d(z,{type:"primary",onClick:g.handleComment,style:{}},{default:s((()=>[$])),_:1},8,["onClick"])])],64)):b("",!0)])),_:1},8,["modelValue","onClose"])])}]]);export{z as default};
