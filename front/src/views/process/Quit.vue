<template>
  <div>
    <el-button
      style="margin: 10px 0"
      type="primary"
      size="small"
      @click="apply"
      icon="el-icon-plus"
      >离职申请</el-button
    >
    <el-tabs type="border-card">
      <el-tab-pane label="待审批">
        <el-table
          :data="
            applyQuitList.slice(
              (currentPage - 1) * pageSize,
              currentPage * pageSize
            )
          "
          highlight-current-row
          style="width: 100%"
          max-height="550"
        >
          <el-table-column type="index" width="60" label="序号" align="center">
          </el-table-column>
          <el-table-column
            prop="reason"
            label="离职原因"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="quitTime"
            label="离职时间"
            width="200"
            sortable
            align="center"
            :formatter="formatDate"
          ></el-table-column>
          <el-table-column
            prop="userName"
            label="申请人"
            width="150"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="createTime"
            label="申请时间"
            width="200"
            sortable
            align="center"
          ></el-table-column>
          <el-table-column label="操作" v-if="ifAdmin" align="center">
            <template scope="scope">
              <el-button
                type="success"
                size="small"
                :disabled="buttonAble(scope.row)"
                @click="handleAgree(scope.row, 1)"
                >同意</el-button
              >
              <el-button
                type="danger"
                size="small"
                :disabled="buttonAble(scope.row)"
                @click="handleAgree(scope.row, 2)"
                >拒绝</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <div class="block" style="margin-top: 15px">
          <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[1, 5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="applyQuitList.length"
          >
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="已通过">
        <el-table
          :data="
            quitList.slice((currentPage - 1) * pageSize, currentPage * pageSize)
          "
          highlight-current-row
          style="width: 100%"
          max-height="550"
        >
          <el-table-column type="index" width="60" label="序号" align="center">
          </el-table-column>
         <el-table-column
            prop="reason"
            label="离职原因"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="quitTime"
            label="离职时间"
            width="200"
            sortable
            align="center"
            :formatter="formatDate"
          ></el-table-column>
          <el-table-column
            prop="approveName"
            label="审批人"
            min-width="150"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="approveTime"
            label="审批时间"
            min-width="200"
            sortable
            align="center"
          ></el-table-column>
          <el-table-column label="操作" v-if="ifAdmin" align="center">
            <template scope="scope">
              <el-button
                type="danger"
                size="small"
                @click="handleDel(scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <div class="block" style="margin-top: 15px">
          <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[1, 5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="quitList.length"
          >
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="未通过">
        <el-table
          :data="
            failQuitList.slice(
              (currentPage - 1) * pageSize,
              currentPage * pageSize
            )
          "
          highlight-current-row
          style="width: 100%"
          max-height="550"
        >
          <el-table-column type="index" width="60" label="序号" align="center">
          </el-table-column>
          <el-table-column
            prop="reason"
            label="离职原因"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="quitTime"
            label="离职时间"
            width="200"
            sortable
            align="center"
            :formatter="formatDate"
          ></el-table-column>
          <el-table-column
            prop="approveName"
            label="审批人"
            min-width="150"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="approveTime"
            label="审批时间"
            min-width="200"
            sortable
            align="center"
          ></el-table-column>
          <el-table-column label="操作" v-if="ifAdmin" align="center">
            <template scope="scope">
              <el-button
                type="danger"
                size="small"
                @click="handleDel(scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页器 -->
        <div class="block" style="margin-top: 15px">
          <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[1, 5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="failQuitList.length"
          >
          </el-pagination>
        </div>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
      title="离职申请"
      :visible.sync="addFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="addForm" label-width="100px" ref="addForm">
        <el-form-item label="离职原因" prop="title">
          <el-input
            v-model="addForm.reason"
            placeholder="离职原因不能为空"
          ></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="离职时间" prop="quitTime">
          <el-date-picker
            v-model="quitTime"
            type="date"
            placeholder="离职时间不能为空"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addFormVisible = false"
          >取消</el-button
        >
        <el-button size="small" type="primary" @click.native="addSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getQuitList,
  getApplyQuitList,
  getFailQuitList,
  agreeQuit,
  addQuit,
  delQuit,
} from "../../api/quit.js";
export default {
  data() {
    return {
      quitList: [],
      applyQuitList: [],
      failQuitList: [],
      quitTime: "",
      ifAdmin: true,
      addFormVisible: false,
      addForm: {
        reason: "",
        description: "",
      },
      currentPage: 1, // 当前页码
      total: 20, // 总条数
      pageSize: 5, // 每页的数据条数
    };
  },
  mounted() {
    this.getTableData();
  },
  methods: {
    //每页条数改变时触发 选择一页显示多少行
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    },
    handleAgree(row, value) {
      var user = sessionStorage.getItem("user");
      user = JSON.parse(user);
      if (value == 1) {
        var params = {
          id: row.id,
          approveName: user.name,
          status: "1",
        };
      } else {
        var params = {
          id: row.id,
          approveName: user.name,
          status: "2",
        };
      }
      agreeQuit(params).then((res) => {
        const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: "success",
          });
          this.getTableData();
        } else {
          this.$message({
            message: res.message,
            type: "error",
          });
        }
        this.getTableData();
      });
    },
    getTableData() {
      var user = sessionStorage.getItem("user");
      user = JSON.parse(user);
      user.permission == "1" ? (this.ifAdmin = true) : (this.ifAdmin = false);
      var params = {
        userName: user.name,
      };
      getApplyQuitList(params).then((res) => {
        this.applyQuitList = res.data.applyQuitList;
      });
      getFailQuitList(params).then((res) => {
        this.failQuitList = res.data.failQuitList;
      });
      getQuitList(params).then((res) => {
        this.quitList = res.data.quitList;
      });
    },
    buttonAble(row) {
      if (row.status == "0") {
        return false;
      } else {
        return true;
      }
    },
    apply() {
      this.addFormVisible = true;
    },
    addSubmit() {
      var user = sessionStorage.getItem("user");
      user = JSON.parse(user);
      let param = {
        reason: this.addForm.reason,
        description: this.addForm.description,
        quitTime: this.quitTime,
        status: "0",
        userName: user.name,
      };
      addQuit(param).then((res) => {
        const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: "success",
          });
          this.getTableData();
        } else {
          this.$message({
            message: res.message,
            type: "error",
          });
        }
      });
      this.addFormVisible = false;
      this.getTableData();
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此未通过报销申请吗", "提示", {
        type: "warning",
      })
        .then(() => {
          delQuit(obj).then((res) => {
            const statusCode = res.code;
            if (statusCode == 200) {
              this.$message({
                message: res.message,
                type: "success",
              });
              this.getTableData();
            } else {
              this.$message({
                message: res.message,
                type: "error",
              });
            }
          });
        })
        .catch(() => {});
    },
    //时间格式化
    formatDate(row, column) {
      // 获取单元格数据
      let datac = row[column.property];
      let dtc = new Date(datac);
      //获取月,默认月份从0开始
      let dtuMonth = dtc.getMonth() + 1;
      //获取日
      let dtuDay = dtc.getDate();
      //处理1-9月前面加0
      if (dtuMonth < 10) {
        dtuMonth = "0" + (dtc.getMonth() + 1);
      }
      //处理1-9天前面加0
      if (dtuDay < 10) {
        dtuDay = "0" + dtc.getDate();
      }
      //获取小时
      let dtuHours = dtc.getHours();
      //处理1-9时前面加0
      if (dtuHours < 10) {
        dtuHours = "0" + dtc.getHours();
      }
      //获取分钟
      let dtuMinutes = dtc.getMinutes();
      //处理1-9分前面加0
      if (dtuMinutes < 10) {
        dtuMinutes = "0" + dtc.getMinutes();
      }
      //获取秒
      let dtuSeconds = dtc.getSeconds();
      //处理1-9秒前面加0
      if (dtuSeconds < 10) {
        dtuSeconds = "0" + dtc.getSeconds();
      }
      //组装年月日时分秒,按自己的要求来
      let dd = dtc.getFullYear() + "-" + dtuMonth + "-" + dtuDay;
      return (row.TableIsbibei = dd);
      //+ " " + dtuHours + ":" + dtuMinutes + ":" + dtuSeconds
    },
  },
};
</script>
