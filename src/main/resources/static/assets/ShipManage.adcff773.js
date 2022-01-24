import{D as e,b as l,r as a,E as t,o,g as r,h as n,i as d,w as i,G as u,c as m,j as c,t as s,H as p,L as f}from"./vendor.c7f77ec0.js";import{_ as g,s as b,t as h,v as V,w as y,x as C}from"./index.01f492b5.js";const v={name:"shipManage",setup(){const a=e({fromStation:"大连",toStation:null,pageIndex:1,pageSize:10}),t=e({pageIndex:1,pageSize:5}),o=l(!1),r=l([]),n=l([]),d=l(0),i=l(0),u=()=>{o.value=!0,b(a).then((e=>{0==e.code?(r.value=e.data,d.value=e.data.length,o.value=!1):p.error(e.message),o.value=!1}))};u();const m=l(!1),c=l(!1);let s="add";const g=l("新增"),v=l(null);let x=e({id:null,shipName:null,startTime:null,fromStation:"大连湾港",endTime:null,toStation:null,tdPrice:null,ydPrice:null,edAprice:null,edBprice:null,sdAPrice:null,sdBPrice:null,sdPrice:null,sxPrice:null,buyUrl:null});const _=e({shipName:[{required:!0,message:"请输入游轮名称",trigger:"blur"}],startTime:[{required:!0,message:"请选择出发时间",trigger:"blur"}],fromStation:[{required:!0,message:"请输入登船港",trigger:"blur"}],endTime:[{required:!0,message:"请选择到达时间",trigger:"blur"}],toStation:[{required:!0,message:"请输入抵达港",trigger:"blur"}]});return{loading:o,query:a,commentQuery:t,tableData:r,commentTableData:n,pageTotal:d,commentPageTotal:i,editVisible:m,commentVisible:c,formRef:v,form:x,rules:_,dialogTitle:g,handleSearch:()=>{a.pageIndex=1,u()},handlePageChange:e=>{a.pageIndex=e},handleDelete:(e,l)=>{f.confirm("确定要删除吗？","提示",{type:"warning"}).then((()=>{y(l.id).then((e=>{0==e.code?(p.success("删除成功"),u()):p.error("删除失败")})).catch((()=>{p.error("删除失败")}))})).catch((()=>{}))},handleEdit:(e,l)=>{Object.keys(x).forEach((e=>{x[e]=l[e]})),m.value=!0,s="update",g.value="修改"},saveEdit:()=>{v.value.validate((e=>{if(!e)return!1;"add"==s?h(x).then((e=>{0==e.code?(p.success("新增成功"),v.value.resetFields(),m.value=!1,u()):p.error("新增失败")})).catch((()=>{p.error("新增失败")})):V(x).then((e=>{0==e.code?(p.success("修改成功"),v.value.resetFields(),m.value=!1,u()):p.error("修改失败")})).catch((()=>{p.error("修改失败")}))}))},handleAdd:()=>{m.value=!0,s="add",g.value="新增"},cancelEdit:()=>{v.value.resetFields(),m.value=!1},viewComment:(e,l)=>{c.value=!0,l.commentShips&&(n.value=l.commentShips,i.value=l.commentShips.length)},handleCommentPageChange:e=>{t.pageIndex=e},handleCommentDelete:(e,l)=>{f.confirm("确定要删除吗？","提示",{type:"warning"}).then((()=>{C(l.id).then((l=>{if(0==l.code){p.success("删除成功");let l=(t.pageIndex-1)*t.pageSize;n.value.splice(l+e,1),i.value=n.value.length}else p.error("删除失败")})).catch((()=>{p.error("删除失败")}))})).catch((()=>{}))},closeComment:()=>{c.value=!1,u()}}}},x={class:"crumbs"},_=n("i",{class:"el-icon-lx-cascades"},null,-1),P=c(" 游轮信息管理 "),S={class:"container"},k={class:"handle-box"},T=c("搜索"),U=c("新增"),w=c("查看评论 "),q=c("编辑 "),B=c("删除"),z={class:"pagination"},A={class:"dialog-footer"},I=c("取 消"),D=c("确 定"),E=c("删除"),H={class:"pagination"},Q={class:"dialog-footer"},N=c("取 消"),$=c("确 定");var j=g(v,[["render",function(e,l,p,f,g,b){const h=a("el-breadcrumb-item"),V=a("el-breadcrumb"),y=a("el-input"),C=a("el-button"),v=a("el-table-column"),j=a("el-table"),F=a("el-pagination"),R=a("el-form-item"),G=a("el-time-picker"),L=a("el-form"),M=a("el-dialog"),O=t("loading");return o(),r("div",null,[n("div",x,[d(V,{separator:"/"},{default:i((()=>[d(h,null,{default:i((()=>[_,P])),_:1})])),_:1})]),n("div",S,[n("div",k,[d(y,{modelValue:f.query.toStation,"onUpdate:modelValue":l[0]||(l[0]=e=>f.query.toStation=e),placeholder:"抵达港",class:"handle-input mr10"},null,8,["modelValue"]),d(C,{type:"primary",icon:"el-icon-search",onClick:f.handleSearch},{default:i((()=>[T])),_:1},8,["onClick"]),d(C,{type:"primary",icon:"el-icon-lx-add",style:{float:"right"},onClick:f.handleAdd},{default:i((()=>[U])),_:1},8,["onClick"])]),u((o(),m(j,{data:f.tableData.slice((f.query.pageIndex-1)*f.query.pageSize,f.query.pageIndex*f.query.pageSize),border:"",class:"table",ref:"multipleTable","header-cell-class-name":"table-header"},{default:i((()=>[d(v,{prop:"index",label:"序号",width:"50"},{default:i((e=>[c(s(e.$index+1),1)])),_:1}),d(v,{prop:"shipName",label:"船名"}),d(v,{prop:"fromStation",label:"登船港"}),d(v,{prop:"toStation",label:"抵达港"}),d(v,{prop:"startTime",label:"出发时间",width:"100"}),d(v,{prop:"endTime",label:"到达时间",width:"100"}),d(v,{label:"操作",width:"250",align:"center"},{default:i((e=>[d(C,{type:"text",icon:"el-icon-edit",class:"opButton",onClick:l=>f.viewComment(e.$index,e.row)},{default:i((()=>[w])),_:2},1032,["onClick"]),d(C,{type:"text",icon:"el-icon-edit",class:"opButton",onClick:l=>f.handleEdit(e.$index,e.row)},{default:i((()=>[q])),_:2},1032,["onClick"]),d(C,{type:"text",icon:"el-icon-delete",class:"opButton red",onClick:l=>f.handleDelete(e.$index,e.row)},{default:i((()=>[B])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[O,f.loading]]),n("div",z,[d(F,{background:"",layout:"total, prev, pager, next","current-page":f.query.pageIndex,"page-size":f.query.pageSize,total:f.pageTotal,onCurrentChange:f.handlePageChange},null,8,["current-page","page-size","total","onCurrentChange"])])]),d(M,{title:f.dialogTitle,modelValue:f.editVisible,"onUpdate:modelValue":l[15]||(l[15]=e=>f.editVisible=e),width:"30%",onClose:f.cancelEdit},{footer:i((()=>[n("span",A,[d(C,{onClick:f.cancelEdit},{default:i((()=>[I])),_:1},8,["onClick"]),d(C,{type:"primary",onClick:f.saveEdit},{default:i((()=>[D])),_:1},8,["onClick"])])])),default:i((()=>[d(L,{"label-width":"100px",rules:f.rules,model:f.form,ref:"formRef"},{default:i((()=>[d(R,{label:"船名",prop:"shipName"},{default:i((()=>[d(y,{modelValue:f.form.shipName,"onUpdate:modelValue":l[1]||(l[1]=e=>f.form.shipName=e)},null,8,["modelValue"])])),_:1}),d(R,{label:"登船港",prop:"fromStation"},{default:i((()=>[d(y,{modelValue:f.form.fromStation,"onUpdate:modelValue":l[2]||(l[2]=e=>f.form.fromStation=e),readonly:"true"},null,8,["modelValue"])])),_:1}),d(R,{label:"抵达港",prop:"toStation"},{default:i((()=>[d(y,{modelValue:f.form.toStation,"onUpdate:modelValue":l[3]||(l[3]=e=>f.form.toStation=e)},null,8,["modelValue"])])),_:1}),d(R,{label:"出发时间",prop:"startTime"},{default:i((()=>[d(G,{modelValue:f.form.startTime,"onUpdate:modelValue":l[4]||(l[4]=e=>f.form.startTime=e),format:"HH:mm","value-format":"HH:mm"},null,8,["modelValue"])])),_:1}),d(R,{label:"到达时间",prop:"endTime"},{default:i((()=>[d(G,{modelValue:f.form.endTime,"onUpdate:modelValue":l[5]||(l[5]=e=>f.form.endTime=e),format:"HH:mm","value-format":"HH:mm"},null,8,["modelValue"])])),_:1}),d(R,{label:"特等价格",prop:"tdPrice"},{default:i((()=>[d(y,{modelValue:f.form.tdPrice,"onUpdate:modelValue":l[6]||(l[6]=e=>f.form.tdPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"一等价格",prop:"ydPrice"},{default:i((()=>[d(y,{modelValue:f.form.ydPrice,"onUpdate:modelValue":l[7]||(l[7]=e=>f.form.ydPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"二等A价格",prop:"edAprice"},{default:i((()=>[d(y,{modelValue:f.form.edAprice,"onUpdate:modelValue":l[8]||(l[8]=e=>f.form.edAprice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"二等B价格",prop:"edBprice"},{default:i((()=>[d(y,{modelValue:f.form.edBprice,"onUpdate:modelValue":l[9]||(l[9]=e=>f.form.edBprice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"三等A价格",prop:"sdAPrice"},{default:i((()=>[d(y,{modelValue:f.form.sdAPrice,"onUpdate:modelValue":l[10]||(l[10]=e=>f.form.sdAPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"三等B价格",prop:"sdBPrice"},{default:i((()=>[d(y,{modelValue:f.form.sdBPrice,"onUpdate:modelValue":l[11]||(l[11]=e=>f.form.sdBPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"四等价格",prop:"sdPrice"},{default:i((()=>[d(y,{modelValue:f.form.sdPrice,"onUpdate:modelValue":l[12]||(l[12]=e=>f.form.sdPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"散席价格",prop:"sxPrice"},{default:i((()=>[d(y,{modelValue:f.form.sxPrice,"onUpdate:modelValue":l[13]||(l[13]=e=>f.form.sxPrice=e),type:"number"},null,8,["modelValue"])])),_:1}),d(R,{label:"购票地址",prop:"buyUrl"},{default:i((()=>[d(y,{modelValue:f.form.buyUrl,"onUpdate:modelValue":l[14]||(l[14]=e=>f.form.buyUrl=e),type:"textarea"},null,8,["modelValue"])])),_:1})])),_:1},8,["rules","model"])])),_:1},8,["title","modelValue","onClose"]),d(M,{title:"评论",modelValue:f.commentVisible,"onUpdate:modelValue":l[16]||(l[16]=e=>f.commentVisible=e),width:"60%",onClose:f.closeComment},{footer:i((()=>[n("span",Q,[d(C,{onClick:f.closeComment},{default:i((()=>[N])),_:1},8,["onClick"]),d(C,{type:"primary",onClick:f.closeComment},{default:i((()=>[$])),_:1},8,["onClick"])])])),default:i((()=>[d(j,{data:f.commentTableData.slice((f.commentQuery.pageIndex-1)*f.commentQuery.pageSize,f.commentQuery.pageIndex*f.commentQuery.pageSize),border:"",class:"table",ref:"commentTable","header-cell-class-name":"table-header"},{default:i((()=>[d(v,{prop:"index",label:"序号",width:"50"},{default:i((e=>[c(s(e.$index+1),1)])),_:1}),d(v,{prop:"content",label:"评论内容"}),d(v,{prop:"time",label:"评论时间"}),d(v,{label:"操作",width:"180",align:"center"},{default:i((e=>[d(C,{type:"text",icon:"el-icon-delete",class:"opButton red",onClick:l=>f.handleCommentDelete(e.$index,e.row)},{default:i((()=>[E])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"]),n("div",H,[d(F,{background:"",layout:"total, prev, pager, next","current-page":f.commentQuery.pageIndex,"page-size":f.commentQuery.pageSize,total:f.commentPageTotal,onCurrentChange:f.handleCommentPageChange},null,8,["current-page","page-size","total","onCurrentChange"])])])),_:1},8,["modelValue","onClose"])])}]]);export{j as default};