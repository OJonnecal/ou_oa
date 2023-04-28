<template>
  <div>
    <el-button style="margin: 10px 0" type="primary" size="small" @click="apply" icon="el-icon-plus"
      >离职申请</el-button
    >
    <el-tabs type="border-card">
      <el-tab-pane label="待审批">
        <el-table
          :data="applyQuitList"
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
            width="250"
            sortable
            align="center"
          ></el-table-column>
          <el-table-column
            prop="createTime"
            label="申请时间"
            width="250"
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
      </el-tab-pane>
      <el-tab-pane label="已通过">
        <el-table
          :data="quitList"
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
            width="250"
            sortable
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
      </el-tab-pane>
      <el-tab-pane label="未通过">
        <el-table
          :data="failQuitList"
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
            width="250"
            sortable
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
      </el-tab-pane>
    </el-tabs>
    <el-dialog
      title="离职申请"
      :visible.sync="addFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="addForm" label-width="100px" ref="addForm">
        <el-form-item label="离职原因" prop="title">
          <el-input v-model="addForm.reason"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="离职时间" prop="quitTime">
          <el-date-picker v-model="quitTime" type="date" placeholder="选择日期">
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
import util from "../../common/js/util";
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
      quitTime: '',
      ifAdmin: true,
      addFormVisible: false,
      addForm: {
        reason: "",
        description: "",
      },
    };
  },
  mounted() {
    this.getTableData();
  },
  methods: {
    handleAgree(row, value) {
      console.log(row, value);
      if (value == 1) {
        var params = {
          id: row.id,
          status: "1",
        };
      } else {
        var params = {
          id: row.id,
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
        userId: user.id,
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
        quitTime: this.quitTime.toLocaleDateString(),
        status: "0",
        userId: user.id,
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
  },
};
</script>
