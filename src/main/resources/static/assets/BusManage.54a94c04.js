import{d as e,b as l,r as a,h as t,o,i as r,j as n,k as d,w as i,n as u,c,m,t as p,H as s,L as g}from"./vendor.ce047f19.js";import{_ as f,D as h,E as b,F as y,G as V,H as C}from"./index.3af46441.js";const v={name:"airManage",setup(){const a=e({dptStation:"大连",arrStation:null,dptDate:null,pageIndex:1,pageSize:10}),t=e({pageIndex:1,pageSize:5}),o=l(!1),r=l([]),n=l([]),d=l(0),i=l(0),u=()=>{o.value=!0,h(a).then((e=>{0==e.code?(r.value=e.data,d.value=e.data.length):s.error(e.message),o.value=!1}))};u();const c=l(!1),m=l(!1);let p="add";const f=l("新增"),v=l(null);let k=e({id:null,dptStation:"大连站南客运站一处",arrStation:null,dptDate:null,dptTime:null,coachType:null,ticketLeft:null,buyUrl:null,ticketPrice:null,weather:null,scenicSpots:null});const x=e({dptStation:[{required:!0,message:"请选择出发站",trigger:"blur"}],arrStation:[{required:!0,message:"请输入到达站",trigger:"blur"}],dptDate:[{required:!0,message:"请选择出发日",trigger:"blur"}],dptTime:[{required:!0,message:"请选择出发时间",trigger:"blur"}],coachType:[{required:!0,message:"请输入车型",trigger:"blur"}],ticketLeft:[{required:!0,message:"请输入剩余票数",trigger:"blur"}],ticketPrice:[{required:!0,message:"请输入票价",trigger:"blur"}]});return{loading:o,query:a,commentQuery:t,tableData:r,commentTableData:n,pageTotal:d,commentPageTotal:i,editVisible:c,commentVisible:m,formRef:v,form:k,rules:x,dialogTitle:f,handleSearch:()=>{a.pageIndex=1,u()},handlePageChange:e=>{a.pageIndex=e},handleDelete:(e,l)=>{g.confirm("确定要删除吗？","提示",{type:"warning"}).then((()=>{V(l.id).then((e=>{0==e.code?(s.success("删除成功"),u()):s.error("删除失败")})).catch((()=>{s.error("删除失败")}))})).catch((()=>{}))},handleEdit:(e,l)=>{Object.keys(k).forEach((e=>{k[e]=l[e]})),c.value=!0,p="update",f.value="修改"},saveEdit:()=>{v.value.validate((e=>{if(!e)return!1;"add"==p?b(k).then((e=>{0==e.code?(s.success("新增成功"),v.value.resetFields(),c.value=!1,u()):s.error("新增失败")})).catch((()=>{s.error("新增失败")})):y(k).then((e=>{0==e.code?(s.success("修改成功"),v.value.resetFields(),c.value=!1,u()):s.error("修改失败")})).catch((()=>{s.error("修改失败")}))}))},handleAdd:()=>{c.value=!0,p="add",f.value="新增"},cancelEdit:()=>{v.value.resetFields(),c.value=!1},viewComment:(e,l)=>{m.value=!0,l.commentAairs&&(n.value=l.commentAairs,i.value=l.commentAairs.length)},handleCommentPageChange:e=>{t.pageIndex=e},handleCommentDelete:(e,l)=>{g.confirm("确定要删除吗？","提示",{type:"warning"}).then((()=>{C(l.id).then((l=>{if(0==l.code){s.success("删除成功");let l=(t.pageIndex-1)*t.pageSize;n.value.splice(l+e,1),i.value=n.value.length}else s.error("删除失败")})).catch((()=>{s.error("删除失败")}))})).catch((()=>{}))},closeComment:()=>{m.value=!1,u()}}}},k={class:"crumbs"},x=n("i",{class:"el-icon-lx-cascades"},null,-1),_=m(" 汽车信息管理 "),S={class:"container"},D={class:"handle-box"},w=m("搜索"),T=m("新增"),q=m("查看评论 "),U=m("编辑 "),Y=m("删除"),z={class:"pagination"},I={class:"dialog-footer"},P=m("取 消"),E=m("确 定"),M=m("删除"),Q={class:"pagination"},H={class:"dialog-footer"},L=m("取 消"),$=m("确 定");var A=f(v,[["render",function(e,l,s,g,f,h){const b=a("el-breadcrumb-item"),y=a("el-breadcrumb"),V=a("el-input"),C=a("el-date-picker"),v=a("el-button"),A=a("el-table-column"),j=a("el-table"),B=a("el-pagination"),F=a("el-option"),R=a("el-select"),G=a("el-form-item"),O=a("el-time-picker"),J=a("el-form"),K=a("el-dialog"),N=t("loading");return o(),r("div",null,[n("div",k,[d(y,{separator:"/"},{default:i((()=>[d(b,null,{default:i((()=>[x,_])),_:1})])),_:1})]),n("div",S,[n("div",D,[d(V,{modelValue:g.query.arrStation,"onUpdate:modelValue":l[0]||(l[0]=e=>g.query.arrStation=e),placeholder:"到达站",class:"handle-input mr10"},null,8,["modelValue"]),d(C,{modelValue:g.query.dptDate,"onUpdate:modelValue":l[1]||(l[1]=e=>g.query.dptDate=e),placeholder:"出发日",type:"date",format:"YYYY-MM-DD","value-format":"YYYY-MM-DD",class:"mr10"},null,8,["modelValue"]),d(v,{type:"primary",icon:"el-icon-search",onClick:g.handleSearch},{default:i((()=>[w])),_:1},8,["onClick"]),d(v,{type:"primary",icon:"el-icon-lx-add",style:{float:"right"},onClick:g.handleAdd},{default:i((()=>[T])),_:1},8,["onClick"])]),u((o(),c(j,{data:g.tableData.slice((g.query.pageIndex-1)*g.query.pageSize,g.query.pageIndex*g.query.pageSize),border:"",class:"table",ref:"multipleTable","header-cell-class-name":"table-header"},{default:i((()=>[d(A,{prop:"index",label:"序号",width:"50"},{default:i((e=>[m(p(e.$index+1),1)])),_:1}),d(A,{prop:"dptStation",label:"出发站"}),d(A,{prop:"arrStation",label:"到达站"}),d(A,{prop:"dptDate",label:"出发日",width:"120"}),d(A,{prop:"dptTime",label:"出发时间",width:"100"}),d(A,{prop:"coachType",label:"车型",width:"100"}),d(A,{prop:"ticketPrice",label:"票价",width:"100"}),d(A,{label:"操作",width:"250",align:"center"},{default:i((e=>[d(v,{type:"text",icon:"el-icon-edit",class:"opButton",onClick:l=>g.viewComment(e.$index,e.row)},{default:i((()=>[q])),_:2},1032,["onClick"]),d(v,{type:"text",icon:"el-icon-edit",class:"opButton",onClick:l=>g.handleEdit(e.$index,e.row)},{default:i((()=>[U])),_:2},1032,["onClick"]),d(v,{type:"text",icon:"el-icon-delete",class:"opButton red",onClick:l=>g.handleDelete(e.$index,e.row)},{default:i((()=>[Y])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[N,g.loading]]),n("div",z,[d(B,{background:"",layout:"total, prev, pager, next","current-page":g.query.pageIndex,"page-size":g.query.pageSize,total:g.pageTotal,onCurrentChange:g.handlePageChange},null,8,["current-page","page-size","total","onCurrentChange"])])]),d(K,{title:g.dialogTitle,modelValue:g.editVisible,"onUpdate:modelValue":l[12]||(l[12]=e=>g.editVisible=e),width:"30%",onClose:g.cancelEdit},{footer:i((()=>[n("span",I,[d(v,{onClick:g.cancelEdit},{default:i((()=>[P])),_:1},8,["onClick"]),d(v,{type:"primary",onClick:g.saveEdit},{default:i((()=>[E])),_:1},8,["onClick"])])])),default:i((()=>[d(J,{"label-width":"100px",rules:g.rules,model:g.form,ref:"formRef"},{default:i((()=>[d(G,{label:"出发站",prop:"dptStation"},{default:i((()=>[d(R,{modelValue:g.form.dptStation,"onUpdate:modelValue":l[2]||(l[2]=e=>g.form.dptStation=e),class:"m-2",size:"large"},{default:i((()=>[d(F,{key:"11",label:"大连站南客运站一处",value:"大连站南客运站一处"}),d(F,{key:"10",label:"大连开发区客运站",value:"大连开发区客运站"})])),_:1},8,["modelValue"])])),_:1}),d(G,{label:"到达站",prop:"arrStation"},{default:i((()=>[d(V,{modelValue:g.form.arrStation,"onUpdate:modelValue":l[3]||(l[3]=e=>g.form.arrStation=e)},null,8,["modelValue"])])),_:1}),d(G,{label:"出发日",prop:"dptDate"},{default:i((()=>[d(C,{modelValue:g.form.dptDate,"onUpdate:modelValue":l[4]||(l[4]=e=>g.form.dptDate=e),type:"date",format:"YYYY-MM-DD","value-format":"YYYY-MM-DD"},null,8,["modelValue"])])),_:1}),d(G,{label:"出发时间",prop:"dptTime"},{default:i((()=>[d(O,{modelValue:g.form.dptTime,"onUpdate:modelValue":l[5]||(l[5]=e=>g.form.dptTime=e),format:"HH:mm","value-format":"HH:mm"},null,8,["modelValue"])])),_:1}),d(G,{label:"车型",prop:"coachType"},{default:i((()=>[d(V,{modelValue:g.form.coachType,"onUpdate:modelValue":l[6]||(l[6]=e=>g.form.coachType=e)},null,8,["modelValue"])])),_:1}),d(G,{label:"剩余票数",prop:"ticketLeft"},{default:i((()=>[d(V,{modelValue:g.form.ticketLeft,"onUpdate:modelValue":l[7]||(l[7]=e=>g.form.ticketLeft=e)},null,8,["modelValue"])])),_:1}),d(G,{label:"票价",prop:"ticketPrice"},{default:i((()=>[d(V,{modelValue:g.form.ticketPrice,"onUpdate:modelValue":l[8]||(l[8]=e=>g.form.ticketPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(G,{label:"目的地天气",prop:"weather"},{default:i((()=>[d(V,{modelValue:g.form.weather,"onUpdate:modelValue":l[9]||(l[9]=e=>g.form.weather=e)},null,8,["modelValue"])])),_:1}),d(G,{label:"目的地景点",prop:"scenicSpots"},{default:i((()=>[d(V,{modelValue:g.form.scenicSpots,"onUpdate:modelValue":l[10]||(l[10]=e=>g.form.scenicSpots=e)},null,8,["modelValue"])])),_:1}),d(G,{label:"购票地址",prop:"buyUrl"},{default:i((()=>[d(V,{modelValue:g.form.buyUrl,"onUpdate:modelValue":l[11]||(l[11]=e=>g.form.buyUrl=e),type:"textarea"},null,8,["modelValue"])])),_:1})])),_:1},8,["rules","model"])])),_:1},8,["title","modelValue","onClose"]),d(K,{title:"评论",modelValue:g.commentVisible,"onUpdate:modelValue":l[13]||(l[13]=e=>g.commentVisible=e),width:"60%",onClose:g.closeComment},{footer:i((()=>[n("span",H,[d(v,{onClick:g.closeComment},{default:i((()=>[L])),_:1},8,["onClick"]),d(v,{type:"primary",onClick:g.closeComment},{default:i((()=>[$])),_:1},8,["onClick"])])])),default:i((()=>[d(j,{data:g.commentTableData.slice((g.commentQuery.pageIndex-1)*g.commentQuery.pageSize,g.commentQuery.pageIndex*g.commentQuery.pageSize),border:"",class:"table",ref:"commentTable","header-cell-class-name":"table-header"},{default:i((()=>[d(A,{prop:"index",label:"序号",width:"50"},{default:i((e=>[m(p(e.$index+1),1)])),_:1}),d(A,{prop:"content",label:"评论内容"}),d(A,{prop:"time",label:"评论时间"}),d(A,{label:"操作",width:"180",align:"center"},{default:i((e=>[d(v,{type:"text",icon:"el-icon-delete",class:"opButton red",onClick:l=>g.handleCommentDelete(e.$index,e.row)},{default:i((()=>[M])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"]),n("div",Q,[d(B,{background:"",layout:"total, prev, pager, next","current-page":g.commentQuery.pageIndex,"page-size":g.commentQuery.pageSize,total:g.commentPageTotal,onCurrentChange:g.handleCommentPageChange},null,8,["current-page","page-size","total","onCurrentChange"])])])),_:1},8,["modelValue","onClose"])])}]]);export{A as default};
