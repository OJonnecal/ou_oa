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
        <el-table :data="assignmentInCompleteList.slice(
            (currentPage - 1) * pageSize,
            currentPage * pageSize
          )">
          <el-table-column label="序号" type="index" width="60" align="center">
          </el-table-column>
          <el-table-column
            prop="title"
            label="任务标题"
            width="200"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="content"
            label="内容"
            width="400"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="level"
            label="紧急程度"
            width="200"
            align="center"
          >
            <template #default="scope">
              <template v-if="scope.row.level == '1'">
                <el-tag type="warning"> 紧急 </el-tag>
              </template>
              <template v-if="scope.row.level == '2'">
                <el-tag type="error"> 正常 </el-tag>
              </template>
              <template v-if="scope.row.level == '3'">
                <el-tag type="success"> 不紧急 </el-tag>
              </template>
            </template>
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
          :total="assignmentInCompleteList.length"
        >
        </el-pagination>
      </div>
      </el-tab-pane>
      <el-tab-pane label="已完成任务">
        <el-table
          :data="assignmentCompleteList.slice(
            (currentPage - 1) * pageSize,
            currentPage * pageSize
          )"
          highlight-current-row
          v-loading="loading"
          style="width: 100%"
          max-height="550"
        >
          <el-table-column label="序号" type="index" width="60" align="center">
          </el-table-column>
          <el-table-column
            prop="title"
            label="任务标题"
            width="200"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="content"
            label="内容"
            width="400"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="level"
            label="紧急程度"
            width="200"
            align="center"
          >
            <template #default="scope">
              <template v-if="scope.row.level == '1'">
                <el-tag type="warning"> 紧急 </el-tag>
              </template>
              <template v-if="scope.row.level == '2'">
                <el-tag type="error"> 正常 </el-tag>
              </template>
              <template v-if="scope.row.level == '3'">
                <el-tag type="success"> 不紧急 </el-tag>
              </template>
            </template>
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
          :total="assignmentCompleteList.length"
        >
        </el-pagination>
      </div>
      </el-tab-pane>
    </el-tabs>

    <el-dialog title="添加任务" :visible.sync="addAssignmentFormVisible">
      <el-form
        :model="addAssignmentForm"
        label-width="100px"
        ref="addAssignmentForm"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="addAssignmentForm.title" placeholder="标题不能为空"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="addAssignmentForm.content"></el-input>
        </el-form-item>
        <el-form-item label="紧急程度">
          <el-select v-model="addAssignmentForm.level" placeholder="紧急程度不能为空">
            <el-option label="紧急" value="1"></el-option>
            <el-option label="正常" value="2"></el-option>
            <el-option label="不紧急" value="3"></el-option>
          </el-select>
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
        level: "",
      },
      addAssignmentFormVisible: false,
      currentPage: 1, // 当前页码
      total: 20, // 总条数
      pageSize: 5, // 每页的数据条数
    };
  },
  methods: {
    //获取任务列表
    getTableData: function () {
      this.loading = true;
      getAssignmentInCompleteList().then((res) => {
        this.assignmentInCompleteList = res.data.assignmentInCompleteList;
        this.loading = false;
      });
      getAssignmentCompleteList().then((res) => {
        this.assignmentCompleteList = res.data.assignmentCompleteList;
        this.loading = false;
      });
    },

    addAssignment() {
      this.addAssignmentFormVisible = true;
    },
    addAssignmentSubmit() {
      let param = {
        title: this.addAssignmentForm.title,
        content: this.addAssignmentForm.content,
        level: this.addAssignmentForm.level,
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
        this.getTableData();
      });
    },
    cancel() {
      this.editFormVisible = false;
    },
    cancelNotice() {
      this.noticeFormVisible = false;
    },
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
  },
  mounted() {
    this.getTableData();
    this.getUserData();
  },
};
</script>

<style scoped></style>
