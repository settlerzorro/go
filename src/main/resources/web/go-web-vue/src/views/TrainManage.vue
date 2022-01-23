<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 火车信息管理
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
        <el-table-column prop="trainCode" label="车次"></el-table-column>
        <el-table-column prop="trainType" label="车次类型"></el-table-column>
        <el-table-column prop="fromStation" label="出发站点"></el-table-column>
        <el-table-column prop="toStation" label="到达站点"></el-table-column>
        <el-table-column
          prop="fromDate"
          label="发车日"
          width="120"
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
    <el-dialog
      :title="dialogTitle"
      v-model="editVisible"
      width="50%"
      @close="cancelEdit"
    >
      <el-form label-width="150px" :rules="rules" :model="form" ref="formRef">
        <el-form-item label="列车号" prop="trainNo">
          <el-input v-model="form.trainNo"></el-input>
        </el-form-item>
        <el-form-item label-width="0px">
          <el-col :span="8">
            <el-form-item label="车次类型" prop="trainType">
              <el-select
                v-model="form.trainType"
                class="m-2"
                size="large"
                @change="trainTypeChange"
              >
                <el-option
                  v-for="item in trainTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-form-item label="车次" prop="trainCode" label-width="100px">
            <el-input v-model="form.trainCode"></el-input>
          </el-form-item>
        </el-form-item>
        <el-form-item label-width="0px">
          <el-col :span="8">
            <el-form-item label="出发站点" prop="fromStation">
              <el-select v-model="form.fromStation" class="m-2" size="large">
                <el-option key="11" label="大连" value="大连"></el-option>
                <el-option key="10" label="大连北" value="大连北"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-form-item label="到达站点" prop="toStation" label-width="100px">
            <el-input v-model="form.toStation"></el-input>
          </el-form-item>
        </el-form-item>
        <el-form-item label="发车日" prop="fromDate">
          <el-date-picker
            v-model="form.fromDate"
            type="date"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="时刻" required>
          <el-col :span="11" style="width: 60%">
            <el-form-item prop="fromTime" label-width="0px">
              <el-time-picker
                placeholder="出发时刻"
                v-model="form.fromTime"
                format="HH:mm"
                value-format="HH:mm"
                style="width: 100%"
              ></el-time-picker>
            </el-form-item>
          </el-col>
          <el-col
            class="text-center"
            :span="2"
            style="width: 8.3333333333%; text-align: center"
          >
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="11" style="width: 60%">
            <el-form-item prop="toTime" label-width="0px">
              <el-time-picker
                placeholder="到达时刻"
                v-model="form.toTime"
                format="HH:mm"
                value-format="HH:mm"
                style="width: 100%"
                @change="toTimeChange"
              ></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="运行时间(小时)" prop="runTime">
          <el-input v-model="form.runTime"></el-input>
        </el-form-item>
        <el-form-item label="是否余票" prop="canBook">
          <el-select v-model="form.canBook" class="m-2" size="large" @change="canBookChange">
            <el-option key="1" label="有余票" :value="true"></el-option>
            <el-option key="0" label="无余票" :value="false"></el-option>
          </el-select>
        </el-form-item>
        <template v-if="form.trainType == 'G' || form.trainType == 'D'">
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="商务座/特等座余票" prop="swzNum">
                <el-input v-model="form.swzNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="商务座/特等座价格" prop="swzPrice">
                <el-input v-model="form.swzPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="一等座余票" prop="ydzNum">
                <el-input v-model="form.ydzNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="一等座价格" prop="ydzPrice">
                <el-input v-model="form.ydzPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="二等座余票" prop="edzNum">
                <el-input v-model="form.edzNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="二等座价格" prop="edzPrice">
                <el-input v-model="form.edzPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="动卧余票" prop="dwNum">
                <el-input v-model="form.dwNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="动卧价格" prop="dwPrice">
                <el-input v-model="form.dwPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
        </template>
        <template v-if="form.trainType == 'K' || form.trainType == 'T'">
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="高级软卧余票" prop="gjrwNum">
                <el-input v-model="form.gjrwNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="高级软卧价格" prop="gjrwPrice">
                <el-input v-model="form.gjrwPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="软卧/一等卧余票" prop="rwNum">
                <el-input v-model="form.rwNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="软卧/一等卧价格" prop="rwPrice">
                <el-input v-model="form.rwPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="硬卧余票" prop="ywNum">
                <el-input v-model="form.ywNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="硬卧价格" prop="ywPrice">
                <el-input v-model="form.ywPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="软座余票" prop="rzNum">
                <el-input v-model="form.rzNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="软座价格" prop="rzPrice">
                <el-input v-model="form.rzPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
          <el-form-item label-width="0px">
            <el-col :span="12" style="width: 60%">
              <el-form-item label="硬座余票" prop="yzNum">
                <el-input v-model="form.yzNum"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" style="width: 60%">
              <el-form-item label="硬座价格" prop="yzPrice">
                <el-input v-model="form.yzPrice" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-form-item>
          <div
            class="message"
            style="position: relative; top: -5px; left: 150px"
          >
            说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
          </div>
        </template>
        <el-form-item label-width="0px">
          <el-col :span="12" style="width: 60%">
            <el-form-item label="无座余票" prop="wzNum">
              <el-input v-model="form.wzNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="width: 60%">
            <el-form-item label="无座价格" prop="wzPrice">
              <el-input v-model="form.wzPrice" type="number"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <div class="message" style="position: relative; top: -5px; left: 150px">
          说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
        </div>
        <el-form-item label-width="0px">
          <el-col :span="12" style="width: 60%">
            <el-form-item label="其他余票" prop="qtNum">
              <el-input v-model="form.qtNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="width: 60%">
            <el-form-item label="其他价格" prop="qtPrice">
              <el-input v-model="form.qtPrice" type="number"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <div class="message" style="position: relative; top: -5px; left: 150px">
          说明：有|表示充足，--|表示无此类型的座位，数字|表示剩余座位数
        </div>
        <el-form-item label="经停站点" prop="trainLines">
          <el-input
            v-model="form.trainLines"
            type="textarea"
            class="trainLines"
          ></el-input>
        </el-form-item>
        <div class="message" style="position: relative; top: -5px; left: 150px">
          说明：各站点间使用“ - ”隔开，如：大连 - 沈阳 - 北京
        </div>
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
    <el-dialog
      title="评论"
      v-model="commentVisible"
      width="60%"
      @close="closeComment"
    >
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
import {
  getTrainList,
  insertTrain,
  updateTrain,
  deleteTrain,
  deleteTrainComment,
} from "../api/index";

export default {
  name: "trainManage",
  setup() {
    // 查询数据
    const query = reactive({
      fromStation: "大连",
      toStation: null,
      fromDate: null,
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
      getTrainList(query).then((res) => {
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
    };

    // 表格编辑时弹窗和保存
    const editVisible = ref(false);
    const commentVisible = ref(false);
    // 编辑时的操作类型
    let editMode = "add"; // update
    const dialogTitle = ref("新增"); // 修改
    const formRef = ref(null);
    // 车次类型下拉数据
    const trainTypeOptions = reactive([
      {
        label: "G",
        value: "G",
      },
      {
        label: "K",
        value: "K",
      },
      {
        label: "T",
        value: "T",
      },
      {
        label: "D",
        value: "D",
      },
    ]);
    // 表单数据
    let form = reactive({
      id: null,
      trainNo: null,
      trainCode: null,
      trainType: null,
      fromStation: "大连",
      toStation: null,
      fromTime: null,
      toTime: null,
      runTime: null,
      canBook: null,
      swzNum: null,
      swzPrice: null,
      ydzNum: null,
      ydzPrice: null,
      edzNum: null,
      edzPrice: null,
      gjrwNum: null,
      gjrwPrice: null,
      rwNum: null,
      rwPrice: null,
      dwNum: null,
      dwPrice: null,
      ywNum: null,
      ywPrice: null,
      rzNum: null,
      rzPrice: null,
      yzNum: null,
      yzPrice: null,
      wzNum: null,
      wzPrice: null,
      qtNum: null,
      qtPrice: null,
      trainLines: null,
      fromDate: null,
      buyUrl: null,
    });
    // 表单校验规则
    const rules = reactive({
      trainNo: [
        {
          required: true,
          message: "请输入列车号",
          trigger: "blur",
        },
      ],
      trainCode: [
        {
          required: true,
          message: "请输入车次",
          trigger: "blur",
        },
      ],
      trainType: [
        {
          required: true,
          message: "请选择车次类型",
          trigger: "blur",
        },
      ],
      fromStation: [
        {
          required: true,
          message: "请选择出发站点",
          trigger: "blur",
        },
      ],
      toStation: [
        {
          required: true,
          message: "请输入到达站点",
          trigger: "blur",
        },
      ],
      fromTime: [
        {
          required: true,
          message: "请选择出发时间",
          trigger: "blur",
        },
      ],
      toTime: [
        {
          required: true,
          message: "请选择到达时间",
          trigger: "blur",
        },
      ],
      runTime: [
        {
          required: true,
          message: "请输入运行时间",
          trigger: "blur",
        },
      ],
      canBook: [
        {
          required: true,
          message: "请选择是否有余票",
          trigger: "blur",
        },
      ],
      swzNum: [
        {
          required: true,
          message: "请输入商务座/特等座剩余票数",
          trigger: "blur",
        },
      ],
      swzPrice: [
        {
          required: true,
          message: "请输入商务座/特等座价格",
          trigger: "blur",
        },
      ],
      ydzNum: [
        {
          required: true,
          message: "请输入一等座剩余票数",
          trigger: "blur",
        },
      ],
      ydzPrice: [
        {
          required: true,
          message: "请输入一等座价格",
          trigger: "blur",
        },
      ],
      edzNum: [
        {
          required: true,
          message: "请输入二等座剩余",
          trigger: "blur",
        },
      ],
      edzPrice: [
        {
          required: true,
          message: "请输入二等座价格",
          trigger: "blur",
        },
      ],
      gjrwNum: [
        {
          required: true,
          message: "请输入高级软卧剩余票数",
          trigger: "blur",
        },
      ],
      gjrwPrice: [
        {
          required: true,
          message: "请输入高级软卧价格",
          trigger: "blur",
        },
      ],
      rwNum: [
        {
          required: true,
          message: "请输入软卧/一等卧剩余票数",
          trigger: "blur",
        },
      ],
      rwPrice: [
        {
          required: true,
          message: "请输入软卧/一等卧价格",
          trigger: "blur",
        },
      ],
      dwNum: [
        {
          required: true,
          message: "请输入动卧剩余票数",
          trigger: "blur",
        },
      ],
      dwPrice: [
        {
          required: true,
          message: "请输入动卧价格",
          trigger: "blur",
        },
      ],
      ywNum: [
        {
          required: true,
          message: "请输入硬卧剩余票数",
          trigger: "blur",
        },
      ],
      ywPrice: [
        {
          required: true,
          message: "请输入硬卧价格",
          trigger: "blur",
        },
      ],
      rzNum: [
        {
          required: true,
          message: "请输入软卧剩余票数",
          trigger: "blur",
        },
      ],
      rzPrice: [
        {
          required: true,
          message: "请输入软卧价格",
          trigger: "blur",
        },
      ],
      yzNum: [
        {
          required: true,
          message: "请输入硬座剩余票数",
          trigger: "blur",
        },
      ],
      yzPrice: [
        {
          required: true,
          message: "请输入硬座价格",
          trigger: "blur",
        },
      ],
      wzNum: [
        {
          required: true,
          message: "请输入无座剩余票数",
          trigger: "blur",
        },
      ],
      wzPrice: [
        {
          required: true,
          message: "请输入无座价格",
          trigger: "blur",
        },
      ],
      trainLines: [
        {
          required: true,
          message: "请输入车次经停站点",
          trigger: "blur",
        },
      ],
      fromDate: [
        {
          required: true,
          message: "请选择发车日期",
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
            insertTrain(form)
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
            updateTrain(form)
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
          deleteTrain(row.id)
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
    };

    const handleCommentDelete = (index, row) => {
      // 二次确认删除
      ElMessageBox.confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          deleteTrainComment(row.id)
            .then((res) => {
              if (res.code == 0) {
                ElMessage.success("删除成功");
                let startIndex =
                  (commentQuery.pageIndex - 1) * commentQuery.pageSize;
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
    };

    const closeComment = () => {
      commentVisible.value = false;
      getData();
    };

    const trainTypeChange = (val) => {
      if (val == "G" || val == "D") {
        form.gjrwNum = form.rwNum = form.dwNum = form.ywNum = form.rzNum = form.yzNum = form.wzNum = null;
        form.gjrwPrice = form.rwPrice = form.dwPrice = form.ywPrice = form.rzPrice = form.yzPrice = form.wzPrice = null;
      }
      if (val == "K" || val == "T") {
        form.swzNum = form.ydzNum = form.edzNum = form.dwNum = form.wzNum = null;
        form.swzPrice = form.ydzPrice = form.edzPrice = form.dwPrice = form.wzPrice = null;
      }
      form.canBook = null;
      if (form.trainCode) {
        form.trainCode = val + form.trainCode.substr(1, form.trainCode.length);
        return;
      }
      form.trainCode = val;
    };

    const toTimeChange = (val) => {
      if (form.fromTime) {
        let fromTime = form.fromTime;
        if (fromTime > val) {
          ElMessage.warning("出发时刻不能大于到达时刻");
          form.runTime = null;
          return;
        }
        let min1 =
          parseInt(fromTime.split(":")[0]) * 60 +
          parseInt(fromTime.split(":")[1]);
        let min2 =
          parseInt(val.split(":")[0]) * 60 + parseInt(val.split(":")[1]);
        let min = min2 - min1;
        let hour = parseInt(min / 60);
        min = min % 60;
        form.runTime =
          (hour >= 10 ? hour : "0" + hour) +
          ":" +
          (min >= 10 ? min : "0" + min);
      }
    };

    const canBookChange = (val) => {
      let num = "0";
      if (val) {
        num = "有";
      }
      if (form.trainType == "G" || form.trainType == "D") {
        form.swzNum = form.ydzNum = form.edzNum = form.dwNum = form.wzNum = num;
      }
      if (form.trainType == "K" || form.trainType == "T") {
        form.gjrwNum = form.rwNum = form.dwNum = form.ywNum = form.rzNum = form.yzNum = form.wzNum = num;
      }
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
      closeComment,
      trainTypeOptions,
      trainTypeChange,
      toTimeChange,
      canBookChange
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
.trainLines .el-textarea__inner {
  height: 150px;
  overflow-y: auto;
  /* overflow-y: auto;兼容ie  */
}
</style>
