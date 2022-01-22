<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 汽车信息管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="query.arrStation"
          placeholder="到达站"
          class="handle-input mr10"
        ></el-input>
        <el-date-picker
          v-model="query.dptDate"
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
        <el-table-column prop="dptStation" label="出发站"></el-table-column>
        <el-table-column prop="arrStation" label="到达站"></el-table-column>
        <el-table-column prop="dptDate" label="出发日" width="120"></el-table-column>
        <el-table-column
          prop="dptTime"
          label="出发时间"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="coachType"
          label="车型"
          width="100"
        ></el-table-column>
        <el-table-column prop="ticketPrice" label="票价" width="100"></el-table-column>
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
        <el-form-item label="出发站" prop="dptStation">
          <el-select
            v-model="form.dptStation"
            class="m-2"
            size="large"
          >
            <el-option key="11" label="大连站南客运站一处" value="大连站南客运站一处"></el-option>
            <el-option key="10" label="大连开发区客运站" value="大连开发区客运站"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="到达站" prop="arrStation">
          <el-input v-model="form.arrStation"></el-input>
        </el-form-item>
        <el-form-item label="出发日" prop="dptDate">
          <el-date-picker
            v-model="form.dptDate"
            type="date"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="出发时间" prop="dptTime">
          <el-time-picker
            v-model="form.dptTime"
            format="HH:mm"
            value-format="HH:mm"
          ></el-time-picker>
        </el-form-item>
        <el-form-item label="车型" prop="coachType">
          <el-input v-model="form.coachType"></el-input>
        </el-form-item>
        <el-form-item label="剩余票数" prop="ticketLeft">
          <el-input v-model="form.ticketLeft"></el-input>
        </el-form-item>
        <el-form-item label="票价" prop="ticketPrice">
          <el-input v-model="form.ticketPrice" type="number"></el-input>
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
import { getBusList, insertBus, updateBus, deleteBus, deleteBusComment } from "../api/index";

export default {
  name: "airManage",
  setup() {
    // 查询数据
    const query = reactive({
      dptStation: "大连",
      arrStation: null,
      dptDate: null,
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
      getBusList(query).then((res) => {
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
      dptStation: "大连站南客运站一处",
      arrStation: null,
      dptDate: null,
      dptTime: null,
      coachType: null,
      ticketLeft: null,
      buyUrl: null,
      ticketPrice: null,
    });
    // 表单校验规则
    const rules = reactive({
      dptStation: [
        {
          required: true,
          message: "请选择出发站",
          trigger: "blur",
        },
      ],
      arrStation: [
        {
          required: true,
          message: "请输入到达站",
          trigger: "blur",
        },
      ],
      dptDate: [
        {
          required: true,
          message: "请选择出发日",
          trigger: "blur",
        },
      ],
      dptTime: [
        {
          required: true,
          message: "请选择出发时间",
          trigger: "blur",
        },
      ],
      coachType: [
        {
          required: true,
          message: "请输入车型",
          trigger: "blur",
        },
      ],
      ticketLeft: [
        {
          required: true,
          message: "请输入剩余票数",
          trigger: "blur",
        },
      ],
      ticketPrice: [
        {
          required: true,
          message: "请输入票价",
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
            insertBus(form)
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
            updateBus(form)
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
          deleteBus(row.id)
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
          deleteBusComment(row.id)
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
