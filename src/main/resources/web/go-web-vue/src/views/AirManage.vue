<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 航班信息管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="query.aapName"
          placeholder="到达站点"
          class="handle-input mr10"
        ></el-input>
        <el-date-picker
          v-model="query.fromTime"
          placeholder="出发日"
          type="date"
          format="YYYY-MM-DD"
          value-format="YYYY-MM-DD"
          class="mr10"
        ></el-date-picker>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-lx-add"
          style="float: right"
          @click="handleAdd"
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
        <el-table-column prop="oapName" label="出发站点"></el-table-column>
        <el-table-column prop="aapName" label="到达站点"></el-table-column>
        <el-table-column prop="fromTime" label="出发日" width="120"></el-table-column>
        <el-table-column
          prop="flyOffOnlyTime"
          label="出发时刻"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="arrivalOnlyTime"
          label="到达时刻"
          width="100"
        ></el-table-column>
        <el-table-column prop="useTime" label="总用时" width="100"></el-table-column>
        <el-table-column prop="aep" label="价格" width="100"></el-table-column>
        <el-table-column label="操作" width="250" align="center">
          <template #default="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              class="opButton"
              @click="viewComment(scope.$index, scope.row)"
              >查看评论
            </el-button>
            <el-button
              type="text"
              icon="el-icon-edit"
              class="opButton"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑
            </el-button>
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
    <el-dialog :title="dialogTitle" v-model="editVisible" width="30%" @close="cancelEdit">
      <el-form label-width="100px" :rules="rules" :model="form" ref="formRef">
        <el-form-item label="出发站点" prop="oapName">
          <el-input v-model="form.oapName" readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="到达站点" prop="aapName">
          <el-input v-model="form.aapName"></el-input>
        </el-form-item>
        <el-form-item label="经停" prop="si">
          <el-input v-model="form.si"></el-input>
        </el-form-item>
        <el-form-item label="出发日" prop="fromTime">
          <el-date-picker
            v-model="form.fromTime"
            type="date"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="出发时刻" prop="flyOffOnlyTime">
          <el-time-picker
            v-model="form.flyOffOnlyTime"
            format="HH:mm"
            value-format="HH:mm"
          ></el-time-picker>
        </el-form-item>
        <el-form-item label="到达时刻" prop="arrivalOnlyTime">
          <el-time-picker
            v-model="form.arrivalOnlyTime"
            format="HH:mm"
            value-format="HH:mm"
          ></el-time-picker>
        </el-form-item>
        <el-form-item label="总用时(min)" prop="useTime">
          <el-input v-model="form.useTime" type="number"></el-input>
        </el-form-item>
        <el-form-item label="最低价格" prop="aep">
          <el-input v-model="form.aep" type="number"></el-input>
        </el-form-item>
        <el-form-item label="购票地址" prop="buyUrl">
          <el-input v-model="form.buyUrl" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cancelEdit">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 评论弹出框 -->
    <el-dialog title="评论" v-model="commentVisible" width="60%" @close="closeComment">
        <el-table
        :data="
          commentTableData.slice(
            (commentQuery.pageIndex - 1) * commentQuery.pageSize,
            commentQuery.pageIndex * commentQuery.pageSize
          )
        "
        border
        class="table"
        ref="commentTable"
        header-cell-class-name="table-header"
      >
        <el-table-column prop="index" label="序号" width="50">
          <template #default="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="content" label="评论内容"></el-table-column>
        <el-table-column prop="time" label="评论时间"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template #default="scope">
            <el-button
              type="text"
              icon="el-icon-delete"
              class="opButton red"
              @click="handleCommentDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :current-page="commentQuery.pageIndex"
          :page-size="commentQuery.pageSize"
          :total="commentPageTotal"
          @current-change="handleCommentPageChange"
        ></el-pagination>
      </div>
        <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeComment">取 消</el-button>
          <el-button type="primary" @click="closeComment">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { getAirList, insertAir, updateAir, deleteAir, deleteAirComment } from "../api/index";

export default {
  name: "airManage",
  setup() {
    // 查询数据
    const query = reactive({
      oapName: "大连",
      aapName: null,
      fromTime: null,
      flyOffOnlyTime: null,
      arrivalOnlyTime: null,
      pageIndex: 1,
      pageSize: 10,
    });
    // 查询评论数据
    const commentQuery = reactive({
      pageIndex: 1,
      pageSize: 5,
    });
    const loading = ref(false);
    const tableData = ref([]);
    const commentTableData = ref([]);
    const pageTotal = ref(0);
    const commentPageTotal = ref(0);
    // 获取表格数据
    const getData = () => {
      loading.value = true;
      getAirList(query).then((res) => {
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

    // 查询操作
    const handleSearch = () => {
      query.pageIndex = 1;
      getData();
    };
    // 分页导航
    const handlePageChange = (val) => {
      query.pageIndex = val;
      // getData();
    };
    const handleCommentPageChange = (val) => {
        commentQuery.pageIndex = val;
    }

    // 表格编辑时弹窗和保存
    const editVisible = ref(false);
    const commentVisible = ref(false);
    // 编辑时的操作类型
    let editMode = "add"; // update
    const dialogTitle = ref("新增"); // 修改
    const formRef = ref(null);
    // 表单数据
    let form = reactive({
      id: null,
      oapName: "大连周水子国际机场",
      aapName: null,
      flyOffOnlyTime: null,
      arrivalOnlyTime: null,
      aep: null,
      fromTime: null,
      buyUrl: null,
      si: null,
      useTime: null,
    });
    // 表单校验规则
    const rules = reactive({
      oapName: [
        {
          required: true,
          message: "请输入出发站点",
          trigger: "blur",
        },
      ],
      aapName: [
        {
          required: true,
          message: "请输入到达站点",
          trigger: "blur",
        },
      ],
      fromTime: [
        {
          required: true,
          message: "请选择出发日",
          trigger: "blur",
        },
      ],
      flyOffOnlyTime: [
        {
          required: true,
          message: "请选择出发时刻",
          trigger: "blur",
        },
      ],
      arrivalOnlyTime: [
        {
          required: true,
          message: "请选择到达时刻",
          trigger: "blur",
        },
      ],
      useTime: [
        {
          required: true,
          message: "请输入总用时",
          trigger: "blur",
        },
      ],
      aep: [
        {
          required: true,
          message: "请输入最低价格",
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
            insertAir(form)
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
            // 修改
            updateAir(form)
              .then((res) => {
                if (res.code == 0) {
                  ElMessage.success("修改成功");
                  formRef.value.resetFields();
                  editVisible.value = false;
                  getData();
                } else {
                  ElMessage.error("修改失败");
                }
              })
              .catch(() => {
                ElMessage.error("修改失败");
              });
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
          deleteAir(row.id)
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

    const viewComment = (index, row) => {
        commentVisible.value = true;
        if (row.commentAairs) {
            commentTableData.value = row.commentAairs;
            commentPageTotal.value = row.commentAairs.length;
        }
    }

    const handleCommentDelete = (index, row) => {
        // 二次确认删除
      ElMessageBox.confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          deleteAirComment(row.id)
            .then((res) => {
              if (res.code == 0) {
                ElMessage.success("删除成功");
                let startIndex = (commentQuery.pageIndex - 1) * commentQuery.pageSize;
                commentTableData.value.splice(startIndex + index, 1);
                commentPageTotal.value = commentTableData.value.length;
              } else {
                ElMessage.error("删除失败");
              }
            })
            .catch(() => {
              ElMessage.error("删除失败");
            });
        })
        .catch(() => {});
    }

    const closeComment = () => {
        commentVisible.value = false;
        getData();
    }

    return {
      loading,
      query,
      commentQuery,
      tableData,
      commentTableData,
      pageTotal,
      commentPageTotal,
      editVisible,
      commentVisible,
      formRef,
      form,
      rules,
      dialogTitle,
      handleSearch,
      handlePageChange,
      handleDelete,
      handleEdit,
      saveEdit,
      handleAdd,
      cancelEdit,
      viewComment,
      handleCommentPageChange,
      handleCommentDelete,
      closeComment
    };
  },
};
</script>

<style>
.handle-box {
  margin-bottom: 20px;
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
</style>
