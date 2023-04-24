<template>
  <section>
    <el-button
      style="margin: 10px 0"
      type="primary"
      v-if="ifAdmin"
      size="small"
      icon="el-icon-plus"
      @click="addUser"
      >添加用户</el-button
    >
    <el-dialog title="添加用户" :visible="addUserFormVisible">
      <el-form :model="addUserForm" label-width="100px" ref="addUserForm">
        <el-form-item label="账号" prop="account">
          <el-input v-model="addUserForm.account"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input v-model="addUserForm.pwd"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input v-model="addUserForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="addUserForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="addUserForm.position"></el-input>
        </el-form-item>
        <el-form-item label="是否为管理员" prop="permission">
          <el-switch
            v-model="isAdmin"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addUserFormVisible = false"
          >取消</el-button
        >
        <el-button size="small" type="primary" @click.native="addUserSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
    <!--列表-->
    <template>
      <el-table
        :data="userList"
        highlight-current-row
        v-loading="loading"
        style="width: 100%"
        max-height="550"
      >
        <el-table-column type="index" width="60"> </el-table-column>
        <el-table-column prop="account" label="账号" width="150">
        </el-table-column>
        <el-table-column prop="name" label="昵称">
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="120">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="180">
        </el-table-column>
        <el-table-column prop="position" label="职位">
        </el-table-column>
        <el-table-column prop="permission" label="权限">
          <template #default="scope">
          <template v-if="scope.row.permission == '1'">
            <el-tag type="success"> 管理员 </el-tag>
          </template>
        </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="用户创建时间"
          min-width="150"
          sortable
        >
        </el-table-column>
        <el-table-column label="操作" width="150" v-if="ifAdmin">
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
    </template>

    <!--编辑界面-->
    <el-dialog
      title="编辑用户信息"
      :visible.sync="editFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="editForm" label-width="100px" ref="editForm">
        <el-form-item label="账号" prop="account">
          <el-input v-model="editForm.account"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="editForm.position"></el-input>
        </el-form-item>
        <el-form-item label="是否为管理员" prop="permission">
          <el-switch
            v-model="isAdmin"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
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
  getUserList,
  editUser,
  delUser,
  addUser,
} from "../../api/user.js";
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
      userList: [],
      isAdmin: false,
      editForm: {
        id: "",
        account: "",
        name: "",
        phone: "",
        email: "",
        position: "",
        createTime: "",
        permission: "",
      },
      addUserForm: {
        account: "",
        name: "",
        pwd: "",
        phone: "",
        email: "",
        position: "",
        createTime: "",
        permission: "",
      },
      addUserFormVisible: false,
    };
  },
  methods: {
    changee() {
      console.log(this.editForm.status);
    },
    //获取客户列表
    getTableData: function () {
      // let obj = {
      // 	hysbh: this.search.hysbh
      // };
      this.loading = true;
      getUserList().then((res) => {
        console.log(res);
        this.userList = res.data.userList;
        console.log(this.userList, "userList");
        this.loading = false;
      });
    },
    addUser() {
      this.addUserFormVisible = true;
    },
    addUserSubmit() {
      let param = {
        account :this.addUserForm.account,
        name: this.addUserForm.name,
        pwd: this.addUserForm.pwd,
        phone: this.addUserForm.phone,
        email: this.addUserForm.email,
        position: this.addUserForm.position,
        permission: this.isAdmin ? "1" : "2",
        // createTime: dateFtt("yyyy-MM-dd hh:mm:ss", new Date()),
      };
      addUser(param).then((res) => {
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
      this.addUserFormVisible = false;
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此用户吗", "提示", {
        type: "warning",
      })
        .then(() => {
          delUser(obj).then((res) => {
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
      this.editForm.account = row.account;
      this.editForm.name = row.name;
      this.editForm.phone = row.phone;
      this.editForm.email = row.email;
      this.editForm.position = row.position;
      this.editForm.createTime = row.createTime;
      this.isAdmin = row.permission == "1" ? true : false;
    },
    editSubmit: function () {
      // this.$refs.editForm.validate((valid) => {
      // 	if (valid) {
      this.editLoading = true;
      //NProgress.start();
      var obj = {
        id: this.editForm.id,
        account: this.editForm.account,
        name: this.editForm.name,
        phone: this.editForm.phone,
        email: this.editForm.email,
        position: this.editForm.position,
        permission: this.isAdmin ? "1" : "2",
      };
      console.log(obj);
      // if (obj.status == "空闲") {
      //   obj.remarks = "";
      // }
      editUser(obj).then((res) => {
        this.editLoading = false;
        this.$message({
          message: res.message,
          type: "success",
        });
        console.log(obj, "1111");1111
        // this.$refs['editForm'].resetFields();
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
  },
  mounted() {
    var user = sessionStorage.getItem("user");
    user = JSON.parse(user);
    user.permission == "1" ? (this.ifAdmin = true) : (this.ifAdmin = false);
    this.getTableData();
    this.getUserData();
  },
};
</script>

<style scoped></style>
