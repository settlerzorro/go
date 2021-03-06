import{g as e,b as r,d as a,r as l,h as o,o as t,i,j as n,k as d,w as u,n as w,c,m,t as s,l as p,F as h,H as g}from"./vendor.ce047f19.js";import{_ as N,y,f as b,k as z,b as f}from"./index.3af46441.js";const x={name:"trainMessage",setup(){const l=e(),o=r(localStorage.getItem("ms_userid")),t=a({fromStation:"大连",toStation:null,fromDate:null,userId:o,pageIndex:1,pageSize:5}),i=r(""),n=r(!1),d=r([]),u=r([]),w=r(0),c=()=>{t.toStation&&t.fromDate&&(n.value=!0,y(t).then((e=>{if(0==e.code){if(d.value=e.data,w.value=e.data.length,1==m.value)for(let r=0;r<e.data.length;r++)if(e.data[r].id==p){h(r,e.data[r]);break}}else g.error(e.message);n.value=!1})))};c();const m=r(!1),s=(e,r,a)=>{a.map((e=>{z(e.id).then((e=>{0==e.code?(g.success("取消收藏成功"),c()):g.error("取消收藏失败")})).catch((()=>{g.error("取消收藏失败")}))}))};let p="";const h=(e,r)=>{m.value=!0,p=r.id,r.commentTrains&&(u.value=r.commentTrains)};return{userId:o,loading:n,query:t,tableData:d,commentTableData:u,comment:i,pageTotal:w,commentVisible:m,handleSearch:()=>{t.pageIndex=1,c()},handlePageChange:e=>{t.pageIndex=e},viewComment:h,closeComment:()=>{m.value=!1,c()},handleLike:(e,r)=>{if(!o.value)return void l.push("/login");let a={userId:o.value,trainId:r.id};r.likes.length>0?s(0,{},r.likes):b(a).then((e=>{0==e.code?(g.success("收藏成功"),c()):g.error("收藏失败")})).catch((()=>{g.error("收藏失败")}))},handleDisLike:s,handleComment:(e,r)=>{if(!i.value)return void g.warning("请输入评论内容，不要无故刷评论");let a={userId:o.value,trainId:p,content:i.value};f(a).then((e=>{0==e.code?(g.success("评论成功"),c()):g.error("评论失败")})).catch((()=>{g.error("评论失败")}))},handleBuy:(e,r)=>{window.open(r.buyUrl,"_blank")},handleGetLike:(e,r)=>!!(r.likes&&r.likes.length>0)}}},k={class:"crumbs"},v=n("i",{class:"el-icon-lx-cascades"},null,-1),_=m(" 火车信息 "),C={class:"container"},P={class:"handle-box"},q=m("搜索"),I=n("div",null,"经停站点：",-1),S={key:0,class:"price"},V={key:0,class:"price"},D={key:0,class:"price"},j={key:0,class:"price"},T={key:0,class:"price"},Y={key:0,class:"price"},L={key:0,class:"price"},$={key:0,class:"price"},B={key:0,class:"price"},M={key:0,class:"price"},U={key:0,class:"price"},G=m("评论 "),F=m("购买地址"),H={class:"pagination"},A={style:{"text-align":"right"}},E=m("评论");var J=N(x,[["render",function(e,r,a,g,N,y){const b=l("el-breadcrumb-item"),z=l("el-breadcrumb"),f=l("el-input"),x=l("el-date-picker"),J=l("el-button"),K=l("el-table-column"),O=l("el-table"),Q=l("el-pagination"),R=l("el-dialog"),W=o("loading");return t(),i("div",null,[n("div",k,[d(z,{separator:"/"},{default:u((()=>[d(b,null,{default:u((()=>[v,_])),_:1})])),_:1})]),n("div",C,[n("div",P,[d(f,{modelValue:g.query.toStation,"onUpdate:modelValue":r[0]||(r[0]=e=>g.query.toStation=e),placeholder:"到达站点",class:"handle-input mr10"},null,8,["modelValue"]),d(x,{modelValue:g.query.fromDate,"onUpdate:modelValue":r[1]||(r[1]=e=>g.query.fromDate=e),placeholder:"发车日",type:"date",format:"YYYY-MM-DD","value-format":"YYYY-MM-DD",class:"mr10"},null,8,["modelValue"]),d(J,{type:"primary",icon:"el-icon-search",onClick:g.handleSearch},{default:u((()=>[q])),_:1},8,["onClick"])]),w((t(),c(O,{data:g.tableData.slice((g.query.pageIndex-1)*g.query.pageSize,g.query.pageIndex*g.query.pageSize),border:"",class:"table",ref:"multipleTable","header-cell-class-name":"table-header"},{default:u((()=>[d(K,{prop:"index",label:"序号",width:"50"},{default:u((e=>[m(s(e.$index+1),1)])),_:1}),d(K,{type:"expand"},{default:u((e=>[I,n("div",null,s(e.row.trainLines),1)])),_:1}),d(K,{prop:"trainCode",label:"车次"}),d(K,{prop:"fromStation",label:"出发站点"}),d(K,{prop:"toStation",label:"到达站点"}),d(K,{prop:"fromTime",label:"出发时间",width:"100"}),d(K,{prop:"toTime",label:"到达时间",width:"100"}),d(K,{prop:"runTime",label:"运行时间",sortable:"",width:"110"}),d(K,{prop:"swzNum",label:"商务座/特等座",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.swzNum?e.row.swzNum:"--")+" ",1),e.row.swzNum&&("有"==e.row.swzNum||e.row.swzNum>0)&&e.row.swzPrice?(t(),i("div",S,"票价：￥"+s(e.row.swzPrice),1)):p("",!0)])),_:1}),d(K,{prop:"ydzNum",label:"一等座",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.ydzNum?e.row.ydzNum:"--")+" ",1),e.row.ydzNum&&("有"==e.row.ydzNum||e.row.ydzNum>0)&&e.row.ydzPrice?(t(),i("div",V,"票价：￥"+s(e.row.ydzPrice),1)):p("",!0)])),_:1}),d(K,{prop:"edzNum",label:"二等座",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.edzNum?e.row.edzNum:"--")+" ",1),e.row.edzNum&&("有"==e.row.edzNum||e.row.edzNum>0)&&e.row.edzPrice?(t(),i("div",D,"票价：￥"+s(e.row.edzPrice),1)):p("",!0)])),_:1}),d(K,{prop:"gjrwNum",label:"高级软卧",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.gjrwNum?e.row.gjrwNum:"--")+" ",1),e.row.gjrwNum&&("有"==e.row.gjrwNum||e.row.gjrwNum>0)&&e.row.gjrwPrice?(t(),i("div",j,"票价：￥"+s(e.row.gjrwPrice),1)):p("",!0)])),_:1}),d(K,{prop:"rwNum",label:"软卧/一等卧",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.rwNum?e.row.rwNum:"--")+" ",1),e.row.rwNum&&("有"==e.row.rwNum||e.row.rwNum>0)&&e.row.rwPrice?(t(),i("div",T,"票价：￥"+s(e.row.rwPrice),1)):p("",!0)])),_:1}),d(K,{prop:"dwNum",label:"动卧",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.dwNum?e.row.dwNum:"--")+" ",1),e.row.dwNum&&("有"==e.row.dwNum||e.row.dwNum>0)&&e.row.dwPrice?(t(),i("div",Y,"票价：￥"+s(e.row.dwPrice),1)):p("",!0)])),_:1}),d(K,{prop:"ywNum",label:"硬卧",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.ywNum?e.row.ywNum:"--")+" ",1),e.row.ywNum&&("有"==e.row.ywNum||e.row.ywNum>0)&&e.row.ywPrice?(t(),i("div",L,"票价：￥"+s(e.row.ywPrice),1)):p("",!0)])),_:1}),d(K,{prop:"rzNum",label:"软座",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.rzNum?e.row.rzNum:"--")+" ",1),e.row.rzNum&&("有"==e.row.rzNum||e.row.rzNum>0)&&e.row.rzPrice?(t(),i("div",$,"票价：￥"+s(e.row.rzPrice),1)):p("",!0)])),_:1}),d(K,{prop:"yzNum",label:"硬座",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.yzNum?e.row.yzNum:"--")+" ",1),e.row.yzNum&&("有"==e.row.yzNum||e.row.yzNum>0)&&e.row.yzPrice?(t(),i("div",B,"票价：￥"+s(e.row.yzPrice),1)):p("",!0)])),_:1}),d(K,{prop:"wzNum",label:"无座",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.wzNum?e.row.wzNum:"--")+" ",1),e.row.wzNum&&("有"==e.row.wzNum||e.row.wzNum>0)&&e.row.wzPrice?(t(),i("div",M,"票价：￥"+s(e.row.wzPrice),1)):p("",!0)])),_:1}),d(K,{prop:"qtNum",label:"其他",align:"center",width:"120px"},{default:u((e=>[m(s(e.row.qtNum?e.row.qtNum:"--")+" ",1),e.row.qtNum&&("有"==e.row.qtNum||e.row.qtNum>0)&&e.row.qtPrice?(t(),i("div",U,"票价：￥"+s(e.row.qtPrice),1)):p("",!0)])),_:1}),d(K,{prop:"weather",label:"天气",width:"100"}),d(K,{prop:"scenicSpots",label:"景点",width:"100"}),d(K,{label:"操作",width:"250",align:"center",fixed:"right"},{default:u((e=>[d(J,{type:"text",icon:"el-icon-lx-comment",class:"opButton",onClick:r=>g.viewComment(e.$index,e.row)},{default:u((()=>[G])),_:2},1032,["onClick"]),m(" "+s()+" ",1),d(J,{type:"text",icon:g.handleGetLike(e.$index,e.row)?"el-icon-lx-likefill":"el-icon-lx-like",class:"opButton red",onClick:r=>g.handleLike(e.$index,e.row)},{default:u((()=>[m(s(g.handleGetLike(e.$index,e.row)?"已收藏":"收藏"),1)])),_:2},1032,["icon","onClick"]),d(J,{type:"text",icon:"el-icon-lx-share",class:"opButton",onClick:r=>g.handleBuy(e.$index,e.row)},{default:u((()=>[F])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[W,g.loading]]),n("div",H,[d(Q,{background:"",layout:"total, prev, pager, next","current-page":g.query.pageIndex,"page-size":g.query.pageSize,total:g.pageTotal,onCurrentChange:g.handlePageChange},null,8,["current-page","page-size","total","onCurrentChange"])])]),d(R,{title:"评论",modelValue:g.commentVisible,"onUpdate:modelValue":r[3]||(r[3]=e=>g.commentVisible=e),width:"60%",onClose:g.closeComment},{default:u((()=>[d(O,{data:g.commentTableData,"max-height":"300",border:"",class:"table",ref:"commentTable","header-cell-class-name":"table-header"},{default:u((()=>[d(K,{prop:"index",label:"序号",width:"50"},{default:u((e=>[m(s(e.$index+1),1)])),_:1}),d(K,{prop:"content",label:"评论内容"}),d(K,{prop:"userId",label:"评论用户"},{default:u((e=>[m(" 用户"+s(e.row.userId),1)])),_:1}),d(K,{prop:"time",label:"评论时间"})])),_:1},8,["data"]),g.userId?(t(),i(h,{key:0},[d(f,{modelValue:g.comment,"onUpdate:modelValue":r[2]||(r[2]=e=>g.comment=e),type:"textarea",placeholder:"请输入评论",style:{margin:"16px 0"}},null,8,["modelValue"]),n("div",A,[d(J,{type:"primary",onClick:g.handleComment,style:{}},{default:u((()=>[E])),_:1},8,["onClick"])])],64)):p("",!0)])),_:1},8,["modelValue","onClose"])])}]]);export{J as default};
