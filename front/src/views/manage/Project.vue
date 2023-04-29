<template>
  <section>
    <el-form
      ref="queryForm"
      :inline="true"
      :model="queryParams"
      label-width="68px"
    >
      <el-form-item label="名称" prop="title">
        <el-input
          v-model="queryParams.title"
          clearable
          placeholder="请输入项目名称"
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="userName">
        <el-input
          v-model="queryParams.userName"
          clearable
          placeholder="请输入项目负责人"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="Search" type="primary" @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item>
    </el-form>
    <!--列表-->
    <template>
      <el-table
        :data="
          projectList.slice(
            (currentPage - 1) * pageSize,
            currentPage * pageSize
          )
        "
        highlight-current-row
        v-loading="loading"
        style="width: 100%"
        max-height="550"
      >
        <el-table-column type="index" width="60" label="序号" align="center">
        </el-table-column>
        <el-table-column
          prop="title"
          label="项目名称"
          width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="description"
          label="项目描述"
          width="240"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="rate"
          label="项目进度"
          min-width="100"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="userName"
          label="项目负责人"
          min-width="100"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="项目创建时间"
          min-width="180"
          sortable
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" width="150" v-if="ifAdmin" align="center">
          <template scope="scope">
            <el-button size="small" @click="handleEdit(scope.row)"
              >编辑</el-button
            >
            <el-button type="danger" size="small" @click="handleDel(scope.row)"
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
          :total="projectList.length"
        >
        </el-pagination>
      </div>
    </template>

    <!--编辑界面-->
    <el-dialog
      title="编辑项目信息"
      :visible.sync="editFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="editForm" label-width="100px" ref="editForm">
        <el-form-item label="名称" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="进度" prop="rate">
          <el-input v-model="editForm.rate"></el-input>
        </el-form-item>
        <el-form-item label="负责人" prop="userName">
          <el-input v-model="editForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="editForm.createTime" :disabled="read"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel" size="small">取消</el-button>
        <el-button
          type="primary"
          size="small"
          @click.native="editSubmit"
          :loading="editLoading"
          >提交</el-button
        >
      </div>
    </el-dialog>
  </section>
</template>
<script>
import util from "../../common/js/util";
import {
  getProjectList,
  editProject,
  delProject,
  addProject,
} from "../../api/project.js";
export default {
  data() {
    return {
      filters: {
        name: "",
      },
      read: true,
      ifAdmin: true,
      loading: false,
      editLoading: false,
      editFormVisible: false,
      projectList: [],
      editForm: {
        id: "",
        title: "",
        description: "",
        rate: "",
        userName: "",
        createTime: "",
      },
      addProjectForm: {
        title: "",
        description: "",
        rate: "",
        userName: "",
      },
      queryParams: {
        title: "",
        userName: "",
      },
      addProjectFormVisible: false,
      currentPage: 1, // 当前页码
      total: 20, // 总条数
      pageSize: 5, // 每页的数据条数
    };
  },
  methods: {
    handleQuery() {
      this.getTableData();
    },
    //获取客户列表
    getTableData: function () {
      // let obj = {
      // 	hysbh: this.search.hysbh
      // };
      this.loading = true;
      getProjectList(this.queryParams).then((res) => {
        this.projectList = res.data.projectList;
        this.loading = false;
      });
    },
    addProject() {
      this.addProjectFormVisible = true;
    },
    addProjectSubmit() {
      //   this.addCustomerForm.time = new Date().toLocaleDateString();
      let param = {
        title: this.addProjectForm.title,
        description: this.addProjectForm.description,
        rate: this.addProjectForm.rate,
        userName: this.addProjectForm.userName,
        // createTime: dateFtt("yyyy-MM-dd hh:mm:ss", new Date()),
      };
      addProject(param).then((res) => {
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
      this.addProjectFormVisible = false;
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此客户吗", "提示", {
        type: "warning",
      })
        .then(() => {
          delProject(obj).then((res) => {
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

    //编辑按钮触发事件
    handleEdit: function (row) {
      this.editFormVisible = true;
      this.editForm.id = row.id;
      this.editForm.title = row.title;
      this.editForm.description = row.description;
      this.editForm.rate = row.rate;
      this.editForm.userName = row.userName;
      this.editForm.createTime = row.createTime;
    },
    editSubmit: function () {
      // this.$refs.editForm.validate((valid) => {
      // 	if (valid) {
      this.editLoading = true;
      //NProgress.start();
      var obj = {
        id: this.editForm.id,
        title: this.editForm.title,
        description: this.editForm.description,
        rate: this.editForm.rate,
        userName: this.editForm.userName,
      };
      console.log(obj);
      // if (obj.status == "空闲") {
      //   obj.remarks = "";
      // }
      editProject(obj).then((res) => {
        this.editLoading = false;
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
        this.editFormVisible = false;
        this.getTableData();
      });
      // 	}
      // });
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
    var user = sessionStorage.getItem("user");
    user = JSON.parse(user);
    user.permission == "1" ? (this.ifAdmin = true) : (this.ifAdmin = false);
    this.getTableData();
  },
};
</script>
<style scoped></style>
