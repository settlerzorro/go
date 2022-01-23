<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 广告信息管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" icon="el-icon-lx-add" @click="handleAdd"
          >新增</el-button
        >
      </div>
      <el-table
        v-loading="loading"
        :data="
          tableData.slice(
            (query.pageIndex - 1) * query.pageSize,
            query.pageIndex * query.pageSize
          )
        "
        border
        class="table"
        ref="multipleTable"
        header-cell-class-name="table-header"
      >
        <el-table-column prop="index" label="序号" width="50">
          <template #default="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="localUrl" label="路径"></el-table-column>
        <el-table-column label="操作" width="250" align="center">
          <template #default="scope">
            <el-button
              type="text"
              :icon="
                handleGetShow(scope.$index, scope.row)
                  ? 'el-icon-lx-attention'
                  : 'el-icon-lx-attentionforbid'
              "
              class="opButton"
              @click="handleShow(scope.$index, scope.row)"
              >{{
                handleGetShow(scope.$index, scope.row) ? "激活" : "隐藏"
              }}</el-button
            >
            <el-button
              type="text"
              icon="el-icon-delete"
              class="opButton red"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :current-page="query.pageIndex"
          :page-size="query.pageSize"
          :total="pageTotal"
          @current-change="handlePageChange"
        ></el-pagination>
      </div>
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="editVisible"
      width="30%"
      @close="cancelEdit"
    >
      <el-form label-width="100px" :rules="rules" :model="form" ref="formRef">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="上传图片" prop="localUrl">
          <el-upload
            class="upload-demo"
            action="#"
            :auto-upload="false"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :multiple="false"
            :limit="1"
            :on-exceed="handleExceed"
            :file-list="fileList"
          >
            <el-button type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cancelEdit">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import {
  getAdList,
  insertAdvert,
  deleteAdvert,
  showAd,
  hideAd,
} from "../api/index";

export default {
  name: "adManage",
  setup() {
    // 查询数据
    const query = reactive({
      pageIndex: 1,
      pageSize: 10,
    });
    const fileList = ref([]);
    const loading = ref(false);
    const tableData = ref([]);
    const pageTotal = ref(0);
    // 获取表格数据
    const getData = () => {
      loading.value = true;
      getAdList(query).then((res) => {
        if (res.code == 0) {
          tableData.value = res.data;
          pageTotal.value = res.data.length;
        } else {
          ElMessage.error(res.message);
        }
        loading.value = false;
      });
    };
    getData();

    // 分页导航
    const handlePageChange = (val) => {
      query.pageIndex = val;
      // getData();
    };

    // 表格编辑时弹窗和保存
    const editVisible = ref(false);
    // 编辑时的操作类型
    let editMode = "add"; // update
    const dialogTitle = ref("新增"); // 修改
    const formRef = ref(null);
    // 表单数据
    let form = reactive({
      id: null,
      name: null,
      localUrl: null,
      showAd: null,
      file: null,
    });
    // 表单校验规则
    const rules = reactive({
      name: [
        {
          required: true,
          message: "请输入名称",
          trigger: "blur",
        },
      ],
      file: [
        {
          required: true,
          message: "请选择图片",
          trigger: "blur",
        },
      ],
    });
    // 点击新增
    const handleAdd = () => {
      editVisible.value = true;
      editMode = "add";
      dialogTitle.value = "新增";
    };
    // 点击编辑
    const handleEdit = (index, row) => {
      Object.keys(form).forEach((item) => {
        form[item] = row[item];
      });
      editVisible.value = true;
      editMode = "update";
      dialogTitle.value = "修改";
    };
    // 保存
    const saveEdit = () => {
      formRef.value.validate((valid) => {
        if (valid) {
          // 新增操作
          if (editMode == "add") {
            let formData = new FormData();
            if (fileList.value.length > 0) {
              console.log(fileList.value[0].raw);
              formData.append("file", fileList.value[0].raw);
            }
            form.advertName = form.name;
            insertAdvert(form, formData)
              .then((res) => {
                if (res.code == 0) {
                  ElMessage.success("新增成功");
                  formRef.value.resetFields();
                  editVisible.value = false;
                  getData();
                } else {
                  ElMessage.error("新增失败");
                }
              })
              .catch(() => {
                ElMessage.error("新增失败");
              });
          } else {
          }
        } else {
          return false;
        }
      });
    };
    const cancelEdit = () => {
      formRef.value.resetFields();
      editVisible.value = false;
    };
    // 删除操作
    const handleDelete = (index, row) => {
      // 二次确认删除
      ElMessageBox.confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          deleteAdvert(row.id)
            .then((res) => {
              if (res.code == 0) {
                ElMessage.success("删除成功");
                getData();
              } else {
                ElMessage.error("删除失败");
              }
            })
            .catch(() => {
              ElMessage.error("删除失败");
            });
        })
        .catch(() => {});
    };

    const handleShow = (index, row) => {
      if (row && row.showAd) {
        hideAd(row.id)
          .then((res) => {
            if (res.code == 0) {
              ElMessage.success("隐藏成功");
              getData();
            } else {
              ElMessage.error("隐藏失败");
            }
          })
          .catch(() => {
            ElMessage.error("隐藏失败");
          });
      } else {
        showAd(row.id)
          .then((res) => {
            if (res.code == 0) {
              ElMessage.success("激活成功");
              getData();
            } else {
              ElMessage.error("激活失败");
            }
          })
          .catch(() => {
            ElMessage.error("激活失败");
          });
      }
    };

    const handleGetShow = (index, row) => {
      if (row && row.showAd) {
        return true;
      }
      return false;
    };

    const handleRemove = (file, filelist) => {
      fileList.value = filelist;
    };

    const handleExceed = (file, filelist) => {
      ElMessage.info("只能上传一个文件，请先移除上一个文件后再上传");
    };

    const handleChange = (file, filelist) => {
      fileList.value = filelist;
    };

    return {
      loading,
      fileList,
      query,
      tableData,
      pageTotal,
      editVisible,
      formRef,
      form,
      rules,
      dialogTitle,
      handlePageChange,
      handleDelete,
      handleEdit,
      saveEdit,
      handleAdd,
      cancelEdit,
      handleGetShow,
      handleRemove,
      handleExceed,
      handleChange,
      handleShow
    };
  },
};
</script>

<style>
.handle-box {
  margin-bottom: 20px;
  text-align: right;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.opButton {
  height: 16px;
  min-height: 0px;
  padding-top: 0px;
  padding-bottom: 0px;
}
.mr10 {
  margin-right: 10px;
}
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
.el-upload--text {
  height: 42px;
  width: 98px;
  border: none;
}
</style>
