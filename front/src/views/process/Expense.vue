<template>
  <div>
    <el-button
      style="margin: 10px 0"
      type="primary"
      size="small"
      @click="apply"
      icon="el-icon-plus"
      >报销申请</el-button
    >
    <el-tabs type="border-card">
      <el-tab-pane label="待审批">
        <el-table
          :data="
            applyExpenseList.slice(
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
            label="报销原因"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="amount"
            label="金额"
            width="150"
            align="center"
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
            :total="applyExpenseList.length"
          >
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="已通过">
        <el-table
          :data="
            expenseList.slice(
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
            label="请假原因"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="amount"
            label="金额"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="approveName"
            label="审批人"
            width="150"
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
            :total="expenseList.length"
          >
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="未通过">
        <el-table
          :data="
            failExpenseList.slice(
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
            label="请假原因"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="amount"
            label="金额"
            width="200"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="approveName"
            label="审批人"
            width="150"
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
            :total="failExpenseList.length"
          >
          </el-pagination>
        </div>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
      title="请假申请"
      :visible.sync="addFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="addForm" label-width="100px" ref="addForm">
        <el-form-item label="请假原因" prop="title">
          <el-input v-model="addForm.reason" placeholder="报销原因不能为空"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="金额" prop="amount">
          <el-input v-model="addForm.amount"></el-input>
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
  getExpenseList,
  getApplyExpenseList,
  getFailExpenseList,
  agreeExpense,
  addExpense,
  delExpense,
} from "../../api/expense.js";
export default {
  data() {
    return {
      expenseList: [],
      applyExpenseList: [],
      failExpenseList: [],
      ifAdmin: true,
      addFormVisible: false,
      addForm: {
        reason: "",
        description: "",
        amount: "",
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
      agreeExpense(params).then((res) => {
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
      getApplyExpenseList(params).then((res) => {
        this.applyExpenseList = res.data.applyExpenseList;
      });
      getFailExpenseList(params).then((res) => {
        this.failExpenseList = res.data.failExpenseList;
      });
      getExpenseList(params).then((res) => {
        this.expenseList = res.data.expenseList;
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
        amount: this.addForm.amount,
        status: "0",
        userName: user.name,
      };
      addExpense(param).then((res) => {
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
          delExpense(obj).then((res) => {
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
  },
};
</script>
