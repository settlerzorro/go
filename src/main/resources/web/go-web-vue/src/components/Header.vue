<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <div class="collapse-btn" @click="collapseChage">
      <i v-if="!collapse" class="el-icon-s-fold"></i>
      <i v-else class="el-icon-s-unfold"></i>
    </div>
    <div class="logo">大连一站式交通工具推荐系统</div>
    <div class="header-right">
      <div class="header-user-con">
        <!-- 用户名下拉菜单 -->
        <template v-if="username">
          <el-dropdown
            class="user-name"
            trigger="click"
            @command="handleCommand"
          >
            <span class="el-dropdown-link">
              {{ username }}
              <i class="el-icon-caret-bottom"></i>
            </span>
            <template #dropdown>
              <template v-if="username">
                <el-dropdown-menu>
                  <el-dropdown-item command="myComment"
                    >个人评论</el-dropdown-item
                  >
                </el-dropdown-menu>
                <el-dropdown-menu>
                  <el-dropdown-item command="myLike">个人收藏</el-dropdown-item>
                </el-dropdown-menu>
              </template>
              <el-dropdown-menu>
                <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
        <template v-if="!username">
          <div @click="toLogin" class="loginText">您好，请登录</div>
        </template>
      </div>
    </div>
  </div>

  <!-- 评论弹出框 -->
  <el-dialog
    title="评论"
    v-model="commentVisible"
    width="60%"
    @close="closeComment"
  >
    <div>
      <el-radio-group
        v-model="commentType"
        @change="changeCommentType"
        style="margin-bottom: 16px"
      >
        <el-radio-button label="航班"></el-radio-button>
        <el-radio-button label="火车"></el-radio-button>
        <el-radio-button label="游轮"></el-radio-button>
        <el-radio-button label="汽车"></el-radio-button>
      </el-radio-group>
    </div>
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

  <el-dialog title="收藏" v-model="likeVisible" width="60%" @close="closeLike">
    <el-tabs v-model="activeName" @tab-click="tabChange">
      <el-tab-pane label="航班" name="air">
        <el-table
          :data="
            airTableData.slice(
              (query.airPageIndex - 1) * query.airPageSize,
              query.airPageIndex * query.airPageSize
            )
          "
          border
          class="table"
          ref="airTable"
          header-cell-class-name="table-header"
        >
          <el-table-column prop="index" label="序号" width="50">
            <template #default="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="oapName" label="出发站点" width="150"></el-table-column>
          <el-table-column prop="aapName" label="到达站点" width="150"></el-table-column>
          <el-table-column prop="si" label="经停" width="80">
            <template #default="scope">
              {{ scope.row.si || "无" }}
            </template>
          </el-table-column>
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
          <el-table-column
            prop="useTime"
            label="总用时"
            sortable
            width="100"
          ></el-table-column>
          <el-table-column
            prop="aep"
            label="价格"
            sortable
            width="100"
          ></el-table-column>
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
          <el-table-column
            label="操作"
            width="250"
            align="center"
            fixed="right"
          >
            <template #default="scope">
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
            :current-page="query.airPageIndex"
            :page-size="query.airPageSize"
            :total="airPageTotal"
            @current-change="handleAirPageChange"
          ></el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="火车" name="train">
        <el-table
          :data="
            trainTableData.slice(
              (query.trainPageIndex - 1) * query.trainPageSize,
              query.trainPageIndex * query.trainPageSize
            )
          "
          border
          class="table"
          ref="trainTable"
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
          <el-table-column
            prop="fromStation"
            label="出发站点"
          ></el-table-column>
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
            width="120"
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
          <el-table-column
            prop="dwNum"
            label="动卧"
            align="center"
            width="120px"
          >
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
          <el-table-column
            prop="ywNum"
            label="硬卧"
            align="center"
            width="120px"
          >
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
          <el-table-column
            prop="rzNum"
            label="软座"
            align="center"
            width="120px"
          >
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
          <el-table-column
            prop="yzNum"
            label="硬座"
            align="center"
            width="120px"
          >
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
          <el-table-column
            prop="wzNum"
            label="无座"
            align="center"
            width="120px"
          >
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
          <el-table-column
            prop="qtNum"
            label="其他"
            align="center"
            width="120px"
          >
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
          <el-table-column
            label="操作"
            width="250"
            align="center"
            fixed="right"
          >
            <template #default="scope">
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
            :current-page="query.trainPageIndex"
            :page-size="query.trainPageSize"
            :total="trainPageTotal"
            @current-change="handleTrainPageChange"
          ></el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="游轮" name="ship">
        <el-table
          :data="
            shipTableData.slice(
              (query.shipPageIndex - 1) * query.shipPageSize,
              query.shipPageIndex * query.shipPageSize
            )
          "
          border
          class="table"
          ref="shipTable"
          header-cell-class-name="table-header"
        >
          <el-table-column prop="index" label="序号" width="50">
            <template #default="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column
            prop="shipName"
            label="船名"
            width="150"
          ></el-table-column>
          <el-table-column
            prop="fromStation"
            label="登船港"
            width="150"
          ></el-table-column>
          <el-table-column
            prop="toStation"
            label="抵达港"
            width="150"
          ></el-table-column>
          <el-table-column
            prop="startTime"
            label="出发时间"
            width="100"
          ></el-table-column>
          <el-table-column
            prop="endTime"
            label="到达时间"
            width="100"
          ></el-table-column>
          <el-table-column prop="tdPrice" label="特等价格" sortable width="110">
            <template #default="scope">
              {{ scope.row.tdPrice || "--" }}
            </template>
          </el-table-column>
          <el-table-column prop="ydPrice" label="一等价格" sortable width="110">
            <template #default="scope">
              {{ scope.row.ydPrice || "--" }}
            </template>
          </el-table-column>
          <el-table-column
            prop="edAprice"
            label="二等A价格"
            sortable
            width="120"
          >
            <template #default="scope">
              {{ scope.row.edAprice || "--" }}
            </template>
          </el-table-column>
          <el-table-column
            prop="edBprice"
            label="二等B价格"
            sortable
            width="120"
          >
            <template #default="scope">
              {{ scope.row.edBprice || "--" }}
            </template>
          </el-table-column>
          <el-table-column
            prop="sdAPrice"
            label="三等A价格"
            sortable
            width="120"
          >
            <template #default="scope">
              {{ scope.row.sdAPrice || "--" }}
            </template>
          </el-table-column>
          <el-table-column
            prop="sdBPrice"
            label="三等B价格"
            sortable
            width="120"
          >
            <template #default="scope">
              {{ scope.row.sdBPrice || "--" }}
            </template>
          </el-table-column>
          <el-table-column prop="sdPrice" label="四等价格" sortable width="110">
            <template #default="scope">
              {{ scope.row.sdPrice || "--" }}
            </template>
          </el-table-column>
          <el-table-column prop="sxPrice" label="散席价格" sortable width="110">
            <template #default="scope">
              {{ scope.row.sxPrice || "--" }}
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
          <el-table-column
            label="操作"
            fixed="right"
            width="250"
            align="center"
          >
            <template #default="scope">
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
            :current-page="query.shipPageIndex"
            :page-size="query.shipPageSize"
            :total="shipPageTotal"
            @current-change="handleShipPageChange"
          ></el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="汽车" name="bus">
        <el-table
          v-loading="loading"
          :data="
            busTableData.slice(
              (query.busPageIndex - 1) * query.busPageSize,
              query.busPageIndex * query.busPageSize
            )
          "
          border
          class="table"
          ref="busTable"
          header-cell-class-name="table-header"
        >
          <el-table-column prop="index" label="序号" width="50">
            <template #default="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="dptStation" label="出发站" width="150"></el-table-column>
          <el-table-column prop="arrStation" label="到达站" width="150"></el-table-column>
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
          <el-table-column
            prop="ticketLeft"
            label="剩余票数"
            width="100"
          ></el-table-column>
          <el-table-column
            prop="ticketPrice"
            sortable
            label="票价"
            width="100"
          ></el-table-column>
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
          <el-table-column
            label="操作"
            width="250"
            align="center"
            fixed="right"
          >
            <template #default="scope">
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
            :current-page="query.busPageIndex"
            :page-size="query.busPageSize"
            :total="busPageTotal"
            @current-change="handleBusPageChange"
          ></el-pagination>
        </div>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>
<script>
import { computed, onMounted, ref, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import {
  getUserInfo,
  logoutApi,
  getUserComment,
  getUserLike,
} from "../api/index";

export default {
  setup() {
    const username = ref(localStorage.getItem("ms_username"));
    const message = 2;

    const commentVisible = ref(false);
    const commentTableData = ref([]);
    const commentType = ref("航班");

    const likeVisible = ref(false);
    const activeName = ref("air");

    const query = reactive({
      airPageIndex: 1,
      airPageSize: 5,
      trainPageIndex: 1,
      trainPageSize: 5,
      shipPageIndex: 1,
      shipPageSize: 5,
      busPageIndex: 1,
      busPageSize: 5,
    });
    const airTableData = ref([]);
    const airPageTotal = ref(0);
    const trainTableData = ref([]);
    const trainPageTotal = ref(0);
    const shipTableData = ref([]);
    const shipPageTotal = ref(0);
    const busTableData = ref([]);
    const busPageTotal = ref(0);
    // 分页导航
    const handleAirPageChange = (val) => {
      query.airPageIndex = val;
    };
    const handleTrainPageChange = (val) => {
      query.trainPageIndex = val;
    };
    const handleShipPageChange = (val) => {
      query.shipPageIndex = val;
    };
    const handleBusPageChange = (val) => {
      query.busPageIndex = val;
    };

    const store = useStore();
    const collapse = computed(() => store.state.collapse);
    // 侧边栏折叠
    const collapseChage = () => {
      store.commit("handleCollapse", !collapse.value);
    };

    onMounted(() => {
      if (document.body.clientWidth < 1500) {
        collapseChage();
      }
      getUserInfo().then((res) => {
        if (res.code == 0) {
          let userData = res.data;
          localStorage.setItem("ms_username", userData.username);
          localStorage.setItem("ms_userid", userData.id);
          localStorage.setItem(
            "ms_userRole",
            JSON.stringify(userData.authorities)
          );
        } else {
          localStorage.removeItem("ms_username");
          localStorage.removeItem("ms_userid");
          localStorage.removeItem("ms_userRole");
        }
      });
    });

    // 用户名下拉菜单选择事件
    const router = useRouter();
    const handleCommand = (command) => {
      if (command == "loginout") {
        router.push("/dashboard");
        // router.push("/login");
        // 后台退出
        logoutApi().then((res) => {
          if (res.code == 0) {
            username.value = null;
            localStorage.removeItem("ms_username");
            localStorage.removeItem("ms_userid");
            localStorage.removeItem("ms_userRole");
            window.location.reload();
          }
        });
      }
      if (command == "myComment") {
        commentVisible.value = true;
        commentType.value = "航班";
        getComment("air");
      }
      if (command == "myLike") {
        likeVisible.value = true;
        activeName.value = "air";
        getLike("air");
      }
    };

    const getComment = (type) => {
      let params = { userId: localStorage.getItem("ms_userid"), type: type };
      getUserComment(params).then((res) => {
        if (res.code == 0) {
          commentTableData.value = res.data;
        }
      });
    };

    const getLike = (type) => {
      let params = { userId: localStorage.getItem("ms_userid"), type: type };
      getUserLike(params).then((res) => {
        if (res.code == 0 && res.data && res.data.length > 0) {
          if (type == "air") {
            airTableData.value = res.data[0];
            airPageTotal.value = res.data[0].length;
          }
          if (type == "train") {
            trainTableData.value = res.data[0];
            trainPageTotal.value = res.data[0].length;
          }
          if (type == "ship") {
            shipTableData.value = res.data[0];
            shipPageTotal.value = res.data[0].length;
          }
          if (type == "bus") {
            busTableData.value = res.data[0];
            busPageTotal.value = res.data[0].length;
          }
        }
      });
    };

    const tabChange = (val) => {
      getLike(val.props.name);
    }

    const changeCommentType = (val) => {
      if (val == "航班") {
        getComment("air");
        return;
      }
      if (val == "火车") {
        getComment("train");
        return;
      }
      if (val == "游轮") {
        getComment("ship");
        return;
      }
      if (val == "汽车") {
        getComment("bus");
        return;
      }
    };

    // 关闭评论
    const closeComment = () => {
      commentVisible.value = false;
    };

    const closeLike = () => {
      likeVisible.value = false;
    };

    const toLogin = () => {
      router.push("/login");
    };

    const handleBuy = (index, row) => {
      window.open(row.buyUrl, "_blank");
    };

    return {
      username,
      message,
      collapse,
      commentVisible,
      likeVisible,
      commentTableData,
      commentType,
      activeName,
      query,
      airTableData,
      airPageTotal,
      trainTableData,
      trainPageTotal,
      shipTableData,
      shipPageTotal,
      busTableData,
      busPageTotal,
      collapseChage,
      handleCommand,
      toLogin,
      closeComment,
      closeLike,
      changeCommentType,
      handleAirPageChange,
      handleTrainPageChange,
      handleShipPageChange,
      handleBusPageChange,
      handleBuy,
      tabChange
    };
  },
};
</script>
<style scoped>
.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}
.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}
.header-right {
  float: right;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}
.loginText {
  font-size: 14px;
  cursor: pointer;
}
</style>
