import{f as e,b as a,D as l,r as t,E as n,o,g as r,h as d,i,w as s,G as u,c,j as p,t as m,F as h,k as g,H as b}from"./vendor.c7f77ec0.js";import{_ as f,D as x,i as k,n as y,e as v}from"./index.01f492b5.js";const C={name:"busMessage",setup(){const t=e(),n=a(localStorage.getItem("ms_userid")),o=l({oapName:"大连",arrStation:null,dptDate:null,userId:n,pageIndex:1,pageSize:5}),r=a(""),d=a(!1),i=a([]),s=a([]),u=a(0),c=()=>{o.arrStation&&o.dptDate&&(d.value=!0,x(o).then((e=>{if(0==e.code){if(i.value=e.data,u.value=e.data.length,1==p.value)for(let a=0;a<e.data.length;a++)if(e.data[a].id==h){g(a,e.data[a]);break}}else b.error(e.message);d.value=!1})))};c();const p=a(!1),m=(e,a,l)=>{l.map((e=>{y(e.id).then((e=>{0==e.code?(b.success("取消收藏成功"),c()):b.error("取消收藏失败")})).catch((()=>{b.error("取消收藏失败")}))}))};let h="";const g=(e,a)=>{p.value=!0,h=a.id,a.commentBuses&&(s.value=a.commentBuses)};return{userId:n,loading:d,query:o,tableData:i,commentTableData:s,comment:r,pageTotal:u,commentVisible:p,handleSearch:()=>{o.pageIndex=1,c()},handlePageChange:e=>{o.pageIndex=e},viewComment:g,closeComment:()=>{p.value=!1,c()},handleLike:(e,a)=>{if(!n.value)return void t.push("/login");let l={userId:n.value,busId:a.id};a.likeBuses.length>0?m(0,{},a.likeBuses):k(l).then((e=>{0==e.code?(b.success("收藏成功"),c()):b.error("收藏失败")})).catch((()=>{b.error("收藏失败")}))},handleDisLike:m,handleComment:(e,a)=>{if(!r.value)return void b.warning("请输入评论内容，不要无故刷评论");let l={userId:n.value,busId:h,content:r.value};v(l).then((e=>{0==e.code?(b.success("评论成功"),c()):b.error("评论失败")})).catch((()=>{b.error("评论失败")}))},handleBuy:(e,a)=>{window.open(a.buyUrl,"_blank")},handleGetLike:(e,a)=>!!(a.likeBuses&&a.likeBuses.length>0)}}},w={class:"crumbs"},_=d("i",{class:"el-icon-lx-cascades"},null,-1),I=p(" 汽车信息 "),D={class:"container"},V={class:"handle-box"},S=p("搜索"),q=p("评论 "),B=p("购买地址"),T={class:"pagination"},Y={style:{"text-align":"right"}},L=p("评论");var $=f(C,[["render",function(e,a,l,b,f,x){const k=t("el-breadcrumb-item"),y=t("el-breadcrumb"),v=t("el-input"),C=t("el-date-picker"),$=t("el-button"),z=t("el-table-column"),M=t("el-table"),U=t("el-pagination"),G=t("el-dialog"),j=n("loading");return o(),r("div",null,[d("div",w,[i(y,{separator:"/"},{default:s((()=>[i(k,null,{default:s((()=>[_,I])),_:1})])),_:1})]),d("div",D,[d("div",V,[i(v,{modelValue:b.query.arrStation,"onUpdate:modelValue":a[0]||(a[0]=e=>b.query.arrStation=e),placeholder:"到达站",class:"handle-input mr10"},null,8,["modelValue"]),i(C,{modelValue:b.query.dptDate,"onUpdate:modelValue":a[1]||(a[1]=e=>b.query.dptDate=e),placeholder:"出发日",type:"date",format:"YYYY-MM-DD","value-format":"YYYY-MM-DD",class:"mr10"},null,8,["modelValue"]),i($,{type:"primary",icon:"el-icon-search",onClick:b.handleSearch},{default:s((()=>[S])),_:1},8,["onClick"])]),u((o(),c(M,{data:b.tableData.slice((b.query.pageIndex-1)*b.query.pageSize,b.query.pageIndex*b.query.pageSize),border:"",class:"table",ref:"multipleTable","header-cell-class-name":"table-header"},{default:s((()=>[i(z,{prop:"index",label:"序号",width:"50"},{default:s((e=>[p(m(e.$index+1),1)])),_:1}),i(z,{prop:"dptStation",label:"出发站"}),i(z,{prop:"arrStation",label:"到达站"}),i(z,{prop:"dptTime",label:"出发时间",width:"100"}),i(z,{prop:"coachType",label:"车型",width:"100"}),i(z,{prop:"ticketLeft",label:"剩余票数",width:"100"}),i(z,{prop:"ticketPrice",label:"票价",width:"100"}),i(z,{label:"操作",width:"250",align:"center"},{default:s((e=>[i($,{type:"text",icon:"el-icon-lx-comment",class:"opButton",onClick:a=>b.viewComment(e.$index,e.row)},{default:s((()=>[q])),_:2},1032,["onClick"]),p(" "+m()+" ",1),i($,{type:"text",icon:b.handleGetLike(e.$index,e.row)?"el-icon-lx-likefill":"el-icon-lx-like",class:"opButton red",onClick:a=>b.handleLike(e.$index,e.row)},{default:s((()=>[p(m(b.handleGetLike(e.$index,e.row)?"已收藏":"收藏"),1)])),_:2},1032,["icon","onClick"]),i($,{type:"text",icon:"el-icon-lx-share",class:"opButton",onClick:a=>b.handleBuy(e.$index,e.row)},{default:s((()=>[B])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[j,b.loading]]),d("div",T,[i(U,{background:"",layout:"total, prev, pager, next","current-page":b.query.pageIndex,"page-size":b.query.pageSize,total:b.pageTotal,onCurrentChange:b.handlePageChange},null,8,["current-page","page-size","total","onCurrentChange"])])]),i(G,{title:"评论",modelValue:b.commentVisible,"onUpdate:modelValue":a[3]||(a[3]=e=>b.commentVisible=e),width:"60%",onClose:b.closeComment},{default:s((()=>[i(M,{data:b.commentTableData,"max-height":"300",border:"",class:"table",ref:"commentTable","header-cell-class-name":"table-header"},{default:s((()=>[i(z,{prop:"index",label:"序号",width:"50"},{default:s((e=>[p(m(e.$index+1),1)])),_:1}),i(z,{prop:"content",label:"评论内容"}),i(z,{prop:"userId",label:"评论用户"},{default:s((e=>[p(" 用户"+m(e.row.userId),1)])),_:1}),i(z,{prop:"time",label:"评论时间"})])),_:1},8,["data"]),b.userId?(o(),r(h,{key:0},[i(v,{modelValue:b.comment,"onUpdate:modelValue":a[2]||(a[2]=e=>b.comment=e),type:"textarea",placeholder:"请输入评论",style:{margin:"16px 0"}},null,8,["modelValue"]),d("div",Y,[i($,{type:"primary",onClick:b.handleComment,style:{}},{default:s((()=>[L])),_:1},8,["onClick"])])],64)):g("",!0)])),_:1},8,["modelValue","onClose"])])}]]);export{$ as default};