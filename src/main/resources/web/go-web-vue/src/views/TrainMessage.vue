<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 火车信息
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="query.toStation"
          placeholder="到达站点"
          class="handle-input mr10"
        ></el-input>
        <el-date-picker
          v-model="query.fromDate"
          placeholder="发车日"
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
        <el-table-column type="expand">
          <template #default="scope">
            <div>经停站点：</div>
            <div>{{ scope.row.trainLines }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="trainCode" label="车次"></el-table-column>
        <el-table-column prop="fromStation" label="出发站点"></el-table-column>
        <el-table-column prop="toStation" label="到达站点"></el-table-column>
        <el-table-column
          prop="fromTime"
          label="出发时间"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="toTime"
          label="到达时间"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="runTime"
          label="运行时间"
          sortable
          width="110"
        ></el-table-column>
        <el-table-column
          prop="swzNum"
          label="商务座/特等座"
          align="center"
          width="120px"
        >
          <template #default="scope">
            {{ scope.row.swzNum ? scope.row.swzNum : "--" }}
            <template
              v-if="
                scope.row.swzNum &&
                (scope.row.swzNum == '有' || scope.row.swzNum > 0) &&
                scope.row.swzPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.swzPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column
          prop="ydzNum"
          label="一等座"
          align="center"
          width="120px"
        >
          <template #default="scope">
            {{ scope.row.ydzNum ? scope.row.ydzNum : "--" }}
            <template
              v-if="
                scope.row.ydzNum &&
                (scope.row.ydzNum == '有' || scope.row.ydzNum > 0) &&
                scope.row.ydzPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.ydzPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column
          prop="edzNum"
          label="二等座"
          align="center"
          width="120px"
        >
          <template #default="scope">
            {{ scope.row.edzNum ? scope.row.edzNum : "--" }}
            <template
              v-if="
                scope.row.edzNum &&
                (scope.row.edzNum == '有' || scope.row.edzNum > 0) &&
                scope.row.edzPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.edzPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column
          prop="gjrwNum"
          label="高级软卧"
          align="center"
          width="120px"
        >
          <template #default="scope">
            {{ scope.row.gjrwNum ? scope.row.gjrwNum : "--" }}
            <template
              v-if="
                scope.row.gjrwNum &&
                (scope.row.gjrwNum == '有' || scope.row.gjrwNum > 0) &&
                scope.row.gjrwPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.gjrwPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column
          prop="rwNum"
          label="软卧/一等卧"
          align="center"
          width="120px"
        >
          <template #default="scope">
            {{ scope.row.rwNum ? scope.row.rwNum : "--" }}
            <template
              v-if="
                scope.row.rwNum &&
                (scope.row.rwNum == '有' || scope.row.rwNum > 0) &&
                scope.row.rwPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.rwPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column prop="dwNum" label="动卧" align="center" width="120px">
          <template #default="scope">
            {{ scope.row.dwNum ? scope.row.dwNum : "--" }}
            <template
              v-if="
                scope.row.dwNum &&
                (scope.row.dwNum == '有' || scope.row.dwNum > 0) &&
                scope.row.dwPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.dwPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column prop="ywNum" label="硬卧" align="center" width="120px">
          <template #default="scope">
            {{ scope.row.ywNum ? scope.row.ywNum : "--" }}
            <template
              v-if="
                scope.row.ywNum &&
                (scope.row.ywNum == '有' || scope.row.ywNum > 0) &&
                scope.row.ywPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.ywPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column prop="rzNum" label="软座" align="center" width="120px">
          <template #default="scope">
            {{ scope.row.rzNum ? scope.row.rzNum : "--" }}
            <template
              v-if="
                scope.row.rzNum &&
                (scope.row.rzNum == '有' || scope.row.rzNum > 0) &&
                scope.row.rzPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.rzPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column prop="yzNum" label="硬座" align="center" width="120px">
          <template #default="scope">
            {{ scope.row.yzNum ? scope.row.yzNum : "--" }}
            <template
              v-if="
                scope.row.yzNum &&
                (scope.row.yzNum == '有' || scope.row.yzNum > 0) &&
                scope.row.yzPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.yzPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column prop="wzNum" label="无座" align="center" width="120px">
          <template #default="scope">
            {{ scope.row.wzNum ? scope.row.wzNum : "--" }}
            <template
              v-if="
                scope.row.wzNum &&
                (scope.row.wzNum == '有' || scope.row.wzNum > 0) &&
                scope.row.wzPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.wzPrice }}</div>
            </template>
          </template>
        </el-table-column>
        <el-table-column prop="qtNum" label="其他" align="center" width="120px">
          <template #default="scope">
            {{ scope.row.qtNum ? scope.row.qtNum : "--" }}
            <template
              v-if="
                scope.row.qtNum &&
                (scope.row.qtNum == '有' || scope.row.qtNum > 0) &&
                scope.row.qtPrice
              "
            >
              <div class="price">票价：￥{{ scope.row.qtPrice }}</div>
            </template>
          </template>
        </el-table-column>
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
        <el-table-column label="操作" width="250" align="center" fixed="right">
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
import {
  getTrainList,
  commentTrain,
  likeTrain,
  dislikeTrain,
} from "../api/index";

export default {
  name: "trainMessage",
  setup() {
    const router = useRouter();
    const userId = ref(localStorage.getItem("ms_userid"));
    // 查询数据
    const query = reactive({
      fromStation: "大连",
      toStation: null,
      fromDate: null,
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
      if (query.toStation && query.fromDate) {
        loading.value = true;
        getTrainList(query).then((res) => {
          if (res.code == 0) {
            tableData.value = res.data;
            pageTotal.value = res.data.length;
            // 刷新评论信息
            if (commentVisible.value == true) {
              for (let i = 0; i < res.data.length; i++) {
                if (res.data[i].id == trainId) {
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
      let param = { userId: userId.value, trainId: row.id };
      // 取消收藏
      if (row.likes.length > 0) {
        handleDisLike(0, {}, row.likes);
        return;
      }
      likeTrain(param)
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
        dislikeTrain(like.id)
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
      });
    };

    let trainId = "";
    // 查看评论
    const viewComment = (index, row) => {
      commentVisible.value = true;
      trainId = row.id;
      if (row.commentTrains) {
        commentTableData.value = row.commentTrains;
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
        trainId: trainId,
        content: comment.value,
      };
      commentTrain(data)
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
      if (row.likes && row.likes.length > 0) {
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
.price {
  font-size: 12px;
  color: #fc8302;
}
</style>
