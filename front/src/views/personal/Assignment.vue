<template>
  <div>
    <el-button
      style="margin: 10px 0"
      type="primary"
      size="small"
      icon="el-icon-plus"
      @click="addAssignment"
      >添加任务</el-button
    >
    <el-tabs type="border-card">
      <el-tab-pane label="待完成任务">
        <el-table
          :data="assignmentInCompleteList"
        >
          <el-table-column label="序号" type="index" width="60" align="center"> </el-table-column>
          <el-table-column prop="title" label="任务标题" width="200" align="center">
          </el-table-column>
          <el-table-column prop="content" label="内容" width="400" align="center">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="创建时间"
            min-width="180"
            sortable
            align="center"
          >
          </el-table-column>
          <el-table-column label="操作" width="150" align="center">
            <template scope="scope">
              <el-button size="small" @click="handleComplete(scope.row)"
                >完成</el-button
              >
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
      <el-tab-pane label="已完成任务">
        <el-table
          :data="assignmentCompleteList"
          highlight-current-row
          v-loading="loading"
          style="width: 100%"
          max-height="550"
        >
          <el-table-column label="序号" type="index" width="60" align="center"> </el-table-column>
          <el-table-column prop="title" label="任务标题" width="200" align="center">
          </el-table-column>
          <el-table-column prop="content" label="内容" width="400" align="center">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="创建时间"
            min-width="180"
            sortable
            align="center"
          >
          </el-table-column>
          <el-table-column label="操作" width="150" align="center">
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

 <el-dialog title="添加任务" :visible.sync="addAssignmentFormVisible">
      <el-form
        :model="addAssignmentForm"
        label-width="100px"
        ref="addAssignmentForm"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="addAssignmentForm.title"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="addAssignmentForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addAssignmentFormVisible = false"
          >取消</el-button
        >
        <el-button
          size="small"
          type="primary"
          @click.native="addAssignmentSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import util from "../../common/js/util";
import {
  getAssignmentInCompleteList,
  getAssignmentCompleteList,
  completeAssignment,
  delAssignment,
  addAssignment,
} from "../../api/assignment.js";
export default {
  data() {
    return {
      filters: {
        name: "",
      },
      read: true,
      loading: false,
      assignmentCompleteList: [],
      assignmentInCompleteList: [],
      addAssignmentForm: {
        title: "",
        content: "",
      },
      addAssignmentFormVisible: false,
    };
  },
  methods: {
    change() {
      console.log(this.editForm.status);
    },
    //获取任务列表
    getTableData: function () {
      // let obj = {
      // 	hysbh: this.search.hysbh
      // };
      this.loading = true;
      getAssignmentInCompleteList().then((res) => {
        console.log(res);
        this.assignmentInCompleteList = res.data.assignmentInCompleteList;
        console.log(this.assignmentInCompleteList, "assignmentInCompleteList");
        this.loading = false;
      });
      getAssignmentCompleteList().then((res) => {
        console.log(res);
        this.assignmentCompleteList = res.data.assignmentCompleteList;
        console.log(this.assignmentCompleteList, "assignmentCompleteList");
        this.loading = false;
      });
    },
    
    addAssignment() {
      this.addAssignmentFormVisible = true;
    },
    addAssignmentSubmit() {
      //   this.addCustomerForm.time = new Date().toLocaleDateString();
      let param = {
        title: this.addAssignmentForm.title,
        content: this.addAssignmentForm.content,
        // createTime: dateFtt("yyyy-MM-dd hh:mm:ss", new Date()),
      };
      addAssignment(param).then((res) => {
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
      this.addAssignmentFormVisible = false;
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此任务吗", "提示", {
        type: "warning",
      })
        .then(() => {
          delAssignment(obj).then((res) => {
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

    //完成按钮触发事件
    handleComplete: function (row) {
      completeAssignment(row.id).then((res) => {
        this.$message({
          message: res.message,
          type: "success",
        });
        // this.$refs['editForm'].resetFields();
        this.getTableData();
      });
    },
    cancel() {
      this.editFormVisible = false;
    },
    cancelNotice() {
      this.noticeFormVisible = false;
    },
  },
  mounted() {
    this.getTableData();
    this.getUserData();
  },
};
</script>

<style scoped></style>
