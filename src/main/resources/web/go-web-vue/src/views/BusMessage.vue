<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 汽车信息
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
        <el-table-column prop="ticketLeft" label="剩余票数" width="100"></el-table-column>
        <el-table-column prop="ticketPrice" label="票价" sortable width="100"></el-table-column>
        <el-table-column
          prop="weather"
          label="天气"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="scenicSpots"
          label="景点"
          width="100"
        ></el-table-column>
        <el-table-column label="操作" width="250" align="center">
          <template #default="scope">
            <el-button
              type="text"
              icon="el-icon-lx-comment"
              class="opButton"
              @click="viewComment(scope.$index, scope.row)"
              >评论
            </el-button>
            {{}}
            <el-button
              type="text"
              :icon="
                handleGetLike(scope.$index, scope.row)
                  ? 'el-icon-lx-likefill'
                  : 'el-icon-lx-like'
              "
              class="opButton red"
              @click="handleLike(scope.$index, scope.row)"
              >{{
                handleGetLike(scope.$index, scope.row) ? "已收藏" : "收藏"
              }}</el-button
            >
            <el-button
              type="text"
              icon="el-icon-lx-share"
              class="opButton"
              @click="handleBuy(scope.$index, scope.row)"
              >购买地址</el-button
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

    <!-- 评论弹出框 -->
    <el-dialog
      title="评论"
      v-model="commentVisible"
      width="60%"
      @close="closeComment"
    >
      <el-table
        :data="commentTableData"
        max-height="300"
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
        <el-table-column prop="userId" label="评论用户">
          <template #default="scope"> 用户{{ scope.row.userId }} </template>
        </el-table-column>
        <el-table-column prop="time" label="评论时间"></el-table-column>
      </el-table>
      <template v-if="userId">
        <el-input
          v-model="comment"
          type="textarea"
          placeholder="请输入评论"
          style="margin: 16px 0"
        ></el-input>
        <div style="text-align: right">
          <el-button type="primary" @click="handleComment" style=""
            >评论</el-button
          >
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from "element-plus";
import { getBusList, commentBus, likeBus, dislikeBus } from "../api/index";

export default {
  name: "busMessage",
  setup() {
    const router = useRouter();
    const userId = ref(localStorage.getItem("ms_userid"));
    // 查询数据
    const query = reactive({
      oapName: "大连",
      arrStation: null,
      dptDate: null,
      userId: userId,
      pageIndex: 1,
      pageSize: 5,
    });
    const comment = ref("");
    const loading = ref(false);
    const tableData = ref([]);
    const commentTableData = ref([]);
    const pageTotal = ref(0);
    // 获取表格数据
    const getData = () => {
      if (query.arrStation && query.dptDate) {
        loading.value = true;
        getBusList(query).then((res) => {
          if (res.code == 0) {
            tableData.value = res.data;
            pageTotal.value = res.data.length;
            // 刷新评论信息
            if (commentVisible.value == true) {
              for (let i = 0; i < res.data.length; i++) {
                if (res.data[i].id == busId) {
                  viewComment(i, res.data[i]);
                  break;
                }
              }
            }
          } else {
            ElMessage.error(res.message);
          }
          loading.value = false;
        });
      }
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

    const commentVisible = ref(false);

    // 收藏操作
    const handleLike = (index, row) => {
      // 游客模式访问想收藏直接跳转到登录页面
      if (!userId.value) {
        router.push("/login");
        return;
      }
      let param = { userId: userId.value, busId: row.id };
      // 取消收藏
      if (row.likeBuses.length > 0) {
        handleDisLike(0, {}, row.likeBuses)
        return;
      }
        likeBus(param)
          .then((res) => {
            if (res.code == 0) {
              ElMessage.success("收藏成功");
              getData();
            } else {
              ElMessage.error("收藏失败");
            }
          })
          .catch(() => {
            ElMessage.error("收藏失败");
          });
    };

    // 取消收藏操作
    const handleDisLike = (index, row, likes) => {
      likes.map((like) => {
        dislikeBus(like.id)
          .then((res) => {
            if (res.code == 0) {
              ElMessage.success("取消收藏成功");
              getData();
            } else {
              ElMessage.error("取消收藏失败");
            }
          })
          .catch(() => {
            ElMessage.error("取消收藏失败");
          });
      })
    };

    let busId = "";
    // 查看评论
    const viewComment = (index, row) => {
      commentVisible.value = true;
      busId = row.id;
      if (row.commentBuses) {
        commentTableData.value = row.commentBuses;
      }
    };

    // 评论
    const handleComment = (index, row) => {
      if (!comment.value) {
        ElMessage.warning("请输入评论内容，不要无故刷评论");
        return;
      }
      let data = {
        userId: userId.value,
        busId: busId,
        content: comment.value,
      };
      commentBus(data)
        .then((res) => {
          if (res.code == 0) {
            ElMessage.success("评论成功");
            getData();
          } else {
            ElMessage.error("评论失败");
          }
        })
        .catch(() => {
          ElMessage.error("评论失败");
        });
    };

    // 关闭评论
    const closeComment = () => {
      commentVisible.value = false;
      getData();
    };

    // 跳转购买地址
    const handleBuy = (index, row) => {
      window.open(row.buyUrl, "_blank");
    };

    // 判断验证用户是否收藏
    const handleGetLike = (index, row) => {
      if (row.likeBuses && row.likeBuses.length > 0) {
        return true;
      }
      return false;
    };

    return {
      userId,
      loading,
      query,
      tableData,
      commentTableData,
      comment,
      pageTotal,
      commentVisible,
      handleSearch,
      handlePageChange,
      viewComment,
      closeComment,
      handleLike,
      handleDisLike,
      handleComment,
      handleBuy,
      handleGetLike,
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
