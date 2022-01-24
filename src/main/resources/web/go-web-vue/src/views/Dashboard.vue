<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-carousel v-loading="loading">
          <el-carousel-item v-for="item in adList" :key="item.id">
            <img
              :src="'/img/' + item.localUrl"
              style="
                width: 100%;
                height: 300px;
                left: 0px;
                top: 0px;
                border-radius: 0px;
                box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px 0px;
              "
            />
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <h3 style="margin-top: 16px">综合推荐</h3>
        <div class="handle-box">
          <el-input
            v-model="query.toCity"
            placeholder="到达城市"
            class="handle-input mr10"
          ></el-input>
          <el-date-picker
            v-model="query.fromDate"
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
        <el-tabs v-model="activeName">
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
              <el-table-column
                prop="oapName"
                label="出发站点"
              ></el-table-column>
              <el-table-column
                prop="aapName"
                label="到达站点"
              ></el-table-column>
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
                width="100"
              ></el-table-column>
              <el-table-column
                prop="aep"
                label="价格"
                width="100"
              ></el-table-column>
              <el-table-column label="操作" width="250" align="center">
                <template #default="scope">
                  <el-button
                    type="text"
                    icon="el-icon-lx-comment"
                    class="opButton"
                    @click="viewComment('air', scope.$index, scope.row)"
                    >评论
                  </el-button>
                  {{}}
                  <el-button
                    type="text"
                    :icon="
                      handleGetLike('air', scope.$index, scope.row)
                        ? 'el-icon-lx-likefill'
                        : 'el-icon-lx-like'
                    "
                    class="opButton red"
                    @click="handleLike('air', scope.$index, scope.row)"
                    >{{
                      handleGetLike("air", scope.$index, scope.row)
                        ? "已收藏"
                        : "收藏"
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
              <el-table-column
                prop="toStation"
                label="到达站点"
              ></el-table-column>
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
                width="100"
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
                label="操作"
                width="250"
                align="center"
                fixed="right"
              >
                <template #default="scope">
                  <el-button
                    type="text"
                    icon="el-icon-lx-comment"
                    class="opButton"
                    @click="viewComment('train', scope.$index, scope.row)"
                    >评论
                  </el-button>
                  {{}}
                  <el-button
                    type="text"
                    :icon="
                      handleGetLike('train', scope.$index, scope.row)
                        ? 'el-icon-lx-likefill'
                        : 'el-icon-lx-like'
                    "
                    class="opButton red"
                    @click="handleLike('train', scope.$index, scope.row)"
                    >{{
                      handleGetLike("train", scope.$index, scope.row)
                        ? "已收藏"
                        : "收藏"
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
              <el-table-column prop="tdPrice" label="特等价格" width="100">
                <template #default="scope">
                  {{ scope.row.tdPrice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="ydPrice" label="一等价格" width="100">
                <template #default="scope">
                  {{ scope.row.ydPrice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="edAprice" label="二等A价格" width="100">
                <template #default="scope">
                  {{ scope.row.edAprice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="edBprice" label="二等B价格" width="100">
                <template #default="scope">
                  {{ scope.row.edBprice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="sdAPrice" label="三等A价格" width="100">
                <template #default="scope">
                  {{ scope.row.sdAPrice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="sdBPrice" label="三等B价格" width="100">
                <template #default="scope">
                  {{ scope.row.sdBPrice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="sdPrice" label="四等价格" width="100">
                <template #default="scope">
                  {{ scope.row.sdPrice || "--" }}
                </template>
              </el-table-column>
              <el-table-column prop="sxPrice" label="散席价格" width="100">
                <template #default="scope">
                  {{ scope.row.sxPrice || "--" }}
                </template>
              </el-table-column>
              <el-table-column
                label="操作"
                fixed="right"
                width="250"
                align="center"
              >
                <template #default="scope">
                  <el-button
                    type="text"
                    icon="el-icon-lx-comment"
                    class="opButton"
                    @click="viewComment('ship', scope.$index, scope.row)"
                    >评论
                  </el-button>
                  {{}}
                  <el-button
                    type="text"
                    :icon="
                      handleGetLike('ship', scope.$index, scope.row)
                        ? 'el-icon-lx-likefill'
                        : 'el-icon-lx-like'
                    "
                    class="opButton red"
                    @click="handleLike('ship', scope.$index, scope.row)"
                    >{{
                      handleGetLike("ship", scope.$index, scope.row)
                        ? "已收藏"
                        : "收藏"
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
              <el-table-column
                prop="dptStation"
                label="出发站"
              ></el-table-column>
              <el-table-column
                prop="arrStation"
                label="到达站"
              ></el-table-column>
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
                label="票价"
                width="100"
              ></el-table-column>
              <el-table-column label="操作" width="250" align="center">
                <template #default="scope">
                  <el-button
                    type="text"
                    icon="el-icon-lx-comment"
                    class="opButton"
                    @click="viewComment('bus', scope.$index, scope.row)"
                    >评论
                  </el-button>
                  {{}}
                  <el-button
                    type="text"
                    :icon="
                      handleGetLike('bus', scope.$index, scope.row)
                        ? 'el-icon-lx-likefill'
                        : 'el-icon-lx-like'
                    "
                    class="opButton red"
                    @click="handleLike('bus', scope.$index, scope.row)"
                    >{{
                      handleGetLike("bus", scope.$index, scope.row)
                        ? "已收藏"
                        : "收藏"
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
                :current-page="query.busPageIndex"
                :page-size="query.busPageSize"
                :total="busPageTotal"
                @current-change="handleBusPageChange"
              ></el-pagination>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>

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
import { reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import {
  getAdList,
  getTransportList,
  commentAir,
  likeAir,
  dislikeAir,
  commentTrain,
  likeTrain,
  dislikeTrain,
  commentShip,
  likeShip,
  dislikeShip,
  commentBus,
  likeBus,
  dislikeBus,
} from "../api/index";

export default {
  name: "dashboard",
  setup() {
    const name = localStorage.getItem("ms_username");
    const userId = ref(localStorage.getItem("ms_userid"));
    const activeName = ref("air");
    const adList = ref([]);
    // 查询数据
    const query = reactive({
      fromCity: "大连",
      toCity: null,
      fromDate: null,
      userId: userId,
      airPageIndex: 1,
      airPageSize: 5,
      trainPageIndex: 1,
      trainPageSize: 5,
      shipPageIndex: 1,
      shipPageSize: 5,
      busPageIndex: 1,
      busPageSize: 5,
    });

    const loading = ref(true);

    const getAd = () => {
      getAdList().then((res) => {
        if (res.code == 0) {
          res.data.map((item) => {
            if (item.showAd) {
              adList.value.push(item);
            }
          });
          loading.value = false;
        }
      });
    };
    getAd();

    const airTableData = ref([]);
    const airPageTotal = ref(0);
    const trainTableData = ref([]);
    const trainPageTotal = ref(0);
    const shipTableData = ref([]);
    const shipPageTotal = ref(0);
    const busTableData = ref([]);
    const busPageTotal = ref(0);

    // 获取表格数据
    const getData = () => {
      if (query.toCity && query.fromDate) {
        getTransportList(query).then((res) => {
          if (res.code == 0) {
            if (res.data.air) {
              airTableData.value = res.data.air;
              airPageTotal.value = res.data.air.length;
            }
            if (res.data.train) {
              trainTableData.value = res.data.train;
              trainPageTotal.value = res.data.train.length;
            }
            if (res.data.ship) {
              shipTableData.value = res.data.ship;
              shipPageTotal.value = res.data.ship.length;
            }
            if (res.data.bus_cheap) {
              busTableData.value = res.data.bus_cheap;
              busPageTotal.value = res.data.bus_cheap.length;
            }
            if (res.data.drive) {
              airTableData.value = [];
              airPageTotal.value = 0;
              trainTableData.value = [];
              trainPageTotal.value = 0;
              shipTableData.value = [];
              shipPageTotal.value = 0;
              busTableData.value = [];
              busPageTotal.value = 0;
              ElMessage.warning(res.data.drive);
            }
            // 刷新评论信息
            if (commentVisible.value == true) {
              let key = "";
              if (commentType == "air") {
                key = "air";
              }
              if (commentType == "train") {
                key = "train";
              }
              if (commentType == "ship") {
                key = "ship";
              }
              if (commentType == "bus") {
                key = "bus_cheap";
              }
              for (let i = 0; i < res.data[key].length; i++) {
                if (res.data[key][i].id == id) {
                  viewComment(commentType, i, res.data[key][i]);
                  break;
                }
              }
            }
          } else {
            ElMessage.error(res.message);
          }
        });
      }
    };
    getData();
    // 查询操作
    const handleSearch = () => {
      query.airPageIndex = 1;
      query.trainPageIndex = 1;
      query.shipPageIndex = 1;
      query.busPageIndex = 1;
      airTableData.value = [];
      airPageTotal.value = 0;
      trainTableData.value = [];
      trainPageTotal.value = 0;
      shipTableData.value = [];
      shipPageTotal.value = 0;
      busTableData.value = [];
      busPageTotal.value = 0;
      getData();
    };
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

    const commentVisible = ref(false);
    const commentTableData = ref([]);
    const comment = ref("");
    let id = "";
    let commentType = "";
    // 查看评论
    const viewComment = (type, index, row) => {
      commentVisible.value = true;
      id = row.id;
      commentType = type;
      let key = "";
      if (type == "air") {
        key = "commentAairs";
      }
      if (type == "train") {
        key = "commentTrains";
      }
      if (type == "ship") {
        key = "commentShips";
      }
      if (type == "bus") {
        key = "commentBuses";
      }
      if (row[key]) {
        commentTableData.value = row[key];
      }
    };

    // 评论
    const handleComment = () => {
      if (!comment.value) {
        ElMessage.warning("请输入评论内容，不要无故刷评论");
        return;
      }
      let data = {
        userId: userId.value,
        content: comment.value,
      };
      if (commentType == "air") {
        data.airId = id;
        commentAir(data)
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
      }
      if (commentType == "train") {
        data.trainId = id;
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
      }
      if (commentType == "ship") {
        data.shipId = id;
        commentShip(data)
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
      }
      if (commentType == "bus") {
        data.busId = id;
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
      }
    };

    // 关闭评论
    const closeComment = () => {
      commentVisible.value = false;
      getData();
    };

    const handleGetLike = (type, index, row) => {
      let key = "";
      if (type == "air") {
        key = "likeAirs";
      }
      if (type == "train") {
        key = "likes";
      }
      if (type == "ship") {
        key = "likeShips";
      }
      if (type == "bus") {
        key = "likeBuses";
      }
      if (row[key] && row[key].length > 0) {
        return true;
      }
      return false;
    };

    // 收藏操作
    const handleLike = (type, index, row) => {
      // 游客模式访问想收藏直接跳转到登录页面
      if (!userId.value) {
        router.push("/login");
        return;
      }
      let key = "";
      if (type == "air") {
        key = "likeAirs";
      }
      if (type == "train") {
        key = "likes";
      }
      if (type == "ship") {
        key = "likeShips";
      }
      if (type == "bus") {
        key = "likeBuses";
      }
      // 取消收藏
      if (row[key] && row[key].length > 0) {
        handleDisLike(type, 0, {}, row[key]);
        return;
      }
      let param = { userId: userId.value };
      if (type == "air") {
        param.airId = row.id;
        likeAir(param)
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
      }
      if (type == "train") {
        param.trainId = row.id;
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
      }
      if (type == "ship") {
        param.shipId = row.id;
        likeShip(param)
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
      }
      if (type == "bus") {
        param.busId = row.id;
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
      }
    };

    // 取消收藏操作
    const handleDisLike = (type, index, row, likes) => {
      likes.map((like) => {
        if (type == "air") {
          dislikeAir(like.id)
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
        }
        if (type == "train") {
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
        }
        if (type == "ship") {
          dislikeShip(like.id)
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
        }
        if (type == "bus") {
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
        }
      });
    };

    // 跳转购买地址
    const handleBuy = (index, row) => {
      window.open(row.buyUrl, "_blank");
    };

    return {
      loading,
      userId,
      activeName,
      name,
      adList,
      query,
      airTableData,
      airPageTotal,
      trainTableData,
      trainPageTotal,
      shipTableData,
      shipPageTotal,
      busTableData,
      busPageTotal,
      commentVisible,
      commentTableData,
      comment,
      getAd,
      handleGetLike,
      handleLike,
      handleDisLike,
      viewComment,
      handleComment,
      closeComment,
      handleSearch,
      handleAirPageChange,
      handleTrainPageChange,
      handleShipPageChange,
      handleBusPageChange,
      handleBuy,
    };
  },
};
</script>

<style>
.el-row {
  margin-bottom: 20px;
  background: #fff;
  margin-left: -10px;
  margin-right: -10px;
}
.handle-box {
  margin-top: 16px;
  margin-bottom: 16px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.mr10 {
  margin-right: 10px;
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
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
</style>