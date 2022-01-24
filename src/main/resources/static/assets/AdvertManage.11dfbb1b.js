import{D as e,b as a,r as l,E as t,o as n,g as o,h as d,i as r,w as i,G as s,c as u,j as c,t as p,H as h,L as m}from"./vendor.c7f77ec0.js";import{_ as g,g as f,I as v,J as b,K as x,L as w}from"./index.2b53616f.js";const C={name:"adManage",setup(){const l=e({pageIndex:1,pageSize:10}),t=a([]),n=a(!1),o=a([]),d=a(0),r=()=>{n.value=!0,f().then((e=>{0==e.code?(o.value=e.data,d.value=e.data.length):h.error(e.message),n.value=!1}))};r();const i=a(!1);let s="add";const u=a("新增"),c=a(null);let p=e({id:null,name:null,localUrl:null,showAd:null,file:null});const g=e({name:[{required:!0,message:"请输入名称",trigger:"blur"}],file:[{required:!0,message:"请选择图片",trigger:"blur"}]});return{loading:n,fileList:t,query:l,tableData:o,pageTotal:d,editVisible:i,formRef:c,form:p,rules:g,dialogTitle:u,handlePageChange:e=>{l.pageIndex=e},handleDelete:(e,a)=>{m.confirm("确定要删除吗？","提示",{type:"warning"}).then((()=>{b(a.id).then((e=>{0==e.code?(h.success("删除成功"),r()):h.error("删除失败")})).catch((()=>{h.error("删除失败")}))})).catch((()=>{}))},handleEdit:(e,a)=>{Object.keys(p).forEach((e=>{p[e]=a[e]})),i.value=!0,s="update",u.value="修改"},saveEdit:()=>{c.value.validate((e=>{if(!e)return!1;if("add"==s){let e=new FormData;t.value.length>0&&(console.log(t.value[0].raw),e.append("file",t.value[0].raw)),p.advertName=p.name,v(p,e).then((e=>{0==e.code?(h.success("新增成功"),c.value.resetFields(),i.value=!1,r()):h.error("新增失败")})).catch((()=>{h.error("新增失败")}))}}))},handleAdd:()=>{i.value=!0,s="add",u.value="新增"},cancelEdit:()=>{c.value.resetFields(),i.value=!1},handleGetShow:(e,a)=>!(!a||!a.showAd),handleRemove:(e,a)=>{t.value=a},handleExceed:(e,a)=>{h.info("只能上传一个文件，请先移除上一个文件后再上传")},handleChange:(e,a)=>{t.value=a},handleShow:(e,a)=>{a&&a.showAd?x(a.id).then((e=>{0==e.code?(h.success("隐藏成功"),r()):h.error("隐藏失败")})).catch((()=>{h.error("隐藏失败")})):w(a.id).then((e=>{0==e.code?(h.success("激活成功"),r()):h.error("激活失败")})).catch((()=>{h.error("激活失败")}))}}}},y={class:"crumbs"},_=d("i",{class:"el-icon-lx-cascades"},null,-1),k=c(" 广告信息管理 "),E={class:"container"},q={class:"handle-box"},S=c("新增"),V=c("删除"),z={class:"pagination"},D=c("点击上传"),I={class:"dialog-footer"},A=c("取 消"),T=c("确 定");var U=g(C,[["render",function(e,a,h,m,g,f){const v=l("el-breadcrumb-item"),b=l("el-breadcrumb"),x=l("el-button"),w=l("el-table-column"),C=l("el-table"),U=l("el-pagination"),$=l("el-input"),j=l("el-form-item"),G=l("el-upload"),L=l("el-form"),R=l("el-dialog"),F=t("loading");return n(),o("div",null,[d("div",y,[r(b,{separator:"/"},{default:i((()=>[r(v,null,{default:i((()=>[_,k])),_:1})])),_:1})]),d("div",E,[d("div",q,[r(x,{type:"primary",icon:"el-icon-lx-add",onClick:m.handleAdd},{default:i((()=>[S])),_:1},8,["onClick"])]),s((n(),u(C,{data:m.tableData.slice((m.query.pageIndex-1)*m.query.pageSize,m.query.pageIndex*m.query.pageSize),border:"",class:"table",ref:"multipleTable","header-cell-class-name":"table-header"},{default:i((()=>[r(w,{prop:"index",label:"序号",width:"50"},{default:i((e=>[c(p(e.$index+1),1)])),_:1}),r(w,{prop:"name",label:"名称"}),r(w,{prop:"localUrl",label:"路径"}),r(w,{label:"操作",width:"250",align:"center"},{default:i((e=>[r(x,{type:"text",icon:m.handleGetShow(e.$index,e.row)?"el-icon-lx-attention":"el-icon-lx-attentionforbid",class:"opButton",onClick:a=>m.handleShow(e.$index,e.row)},{default:i((()=>[c(p(m.handleGetShow(e.$index,e.row)?"激活":"隐藏"),1)])),_:2},1032,["icon","onClick"]),r(x,{type:"text",icon:"el-icon-delete",class:"opButton red",onClick:a=>m.handleDelete(e.$index,e.row)},{default:i((()=>[V])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[F,m.loading]]),d("div",z,[r(U,{background:"",layout:"total, prev, pager, next","current-page":m.query.pageIndex,"page-size":m.query.pageSize,total:m.pageTotal,onCurrentChange:m.handlePageChange},null,8,["current-page","page-size","total","onCurrentChange"])])]),r(R,{title:m.dialogTitle,modelValue:m.editVisible,"onUpdate:modelValue":a[1]||(a[1]=e=>m.editVisible=e),width:"30%",onClose:m.cancelEdit},{footer:i((()=>[d("span",I,[r(x,{onClick:m.cancelEdit},{default:i((()=>[A])),_:1},8,["onClick"]),r(x,{type:"primary",onClick:m.saveEdit},{default:i((()=>[T])),_:1},8,["onClick"])])])),default:i((()=>[r(L,{"label-width":"100px",rules:m.rules,model:m.form,ref:"formRef"},{default:i((()=>[r(j,{label:"名称",prop:"name"},{default:i((()=>[r($,{modelValue:m.form.name,"onUpdate:modelValue":a[0]||(a[0]=e=>m.form.name=e)},null,8,["modelValue"])])),_:1}),r(j,{label:"上传图片",prop:"localUrl"},{default:i((()=>[r(G,{class:"upload-demo",action:"#","auto-upload":!1,"on-remove":m.handleRemove,"on-change":m.handleChange,multiple:!1,limit:1,"on-exceed":m.handleExceed,"file-list":m.fileList},{default:i((()=>[r(x,{type:"primary"},{default:i((()=>[D])),_:1})])),_:1},8,["on-remove","on-change","on-exceed","file-list"])])),_:1})])),_:1},8,["rules","model"])])),_:1},8,["title","modelValue","onClose"])])}]]);export{U as default};
