<template>
  <section>
    <el-button
      style="margin: 10px 0"
      type="primary"
      v-if="ifAdmin"
      size="small"
      icon="el-icon-plus"
      @click="addCustomer"
      >添加客户</el-button
    >
    <el-dialog title="添加客户" :visible="addCustomerFormVisible">
      <el-form :model="addCustomerForm" label-width="100px" ref="addCustomerForm">
        <el-form-item label="客户名称" prop="title">
          <el-input v-model="addCustomerForm.name"></el-input>
        </el-form-item>
        <el-form-item label="客户手机号" prop="title">
          <el-input v-model="addCustomerForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="title">
          <el-input v-model="addCustomerForm.remarks"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addCustomerFormVisible = false"
          >取消</el-button
        >
        <el-button size="small" type="primary" @click.native="addCustomerSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
    <!--列表-->
    <template>
      <el-table
        :data="customerList"
        highlight-current-row
        v-loading="loading"
        style="width: 100%"
        max-height="550"
      >
        <el-table-column type="index" width="60"> </el-table-column>
        <el-table-column prop="name" label="客户姓名" width="150">
        </el-table-column>
        <el-table-column prop="phone" label="客户手机号" width="150">
        </el-table-column>
        <el-table-column prop="remarks" label="备注" min-width="180">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="客户创建时间"
          min-width="180"
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
      title="编辑客户信息"
      :visible.sync="editFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="editForm" label-width="100px" ref="editForm">
        <el-form-item label="客户姓名" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="客户手机号" prop="phone">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input type="textarea" v-model="editForm.remarks"></el-input>
        </el-form-item>
        <el-form-item label="客户创建时间" prop="createTime">
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
  getCustomerList,
  editCustomer,
  delCustomer,
  addCustomer,
} from "../../api/customer.js";
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
      customerList: [],
      editForm: {
        id: "",
        name: "",
        phone: "",
        remarks: "",
        createTime: "",
      },
      addCustomerForm: {
        name: "",
        phone: "",
        remarks: "",
      },
      addCustomerFormVisible: false,
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
      getCustomerList().then((res) => {
        console.log(res);
        this.customerList = res.data.customerList;
        console.log(this.customerList, "user");
        this.loading = false;
      });
    },
    addCustomer() {
      this.addCustomerFormVisible = true;
    },
    addCustomerSubmit() {
      //   this.addCustomerForm.time = new Date().toLocaleDateString();
      let param = {
        name: this.addCustomerForm.name,
        phone: this.addCustomerForm.phone,
        remarks: this.addCustomerForm.remarks,
        // createTime: dateFtt("yyyy-MM-dd hh:mm:ss", new Date()),
      };
      addCustomer(param).then((res) => {
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
      this.addCustomerFormVisible = false;
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此客户吗", "提示", {
        type: "warning",
      })
        .then(() => {
          delCustomer(obj).then((res) => {
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
      this.editForm.name = row.name;
      this.editForm.phone = row.phone;
      this.editForm.remarks = row.remarks;
      this.editForm.createTime = row.createTime;
    },
    editSubmit: function () {
      // this.$refs.editForm.validate((valid) => {
      // 	if (valid) {
      this.editLoading = true;
      //NProgress.start();
      var obj = {
        id: this.editForm.id,
        name: this.editForm.name,
        phone: this.editForm.phone,
        remarks: this.editForm.remarks,
      };
      console.log(obj);
      // if (obj.status == "空闲") {
      //   obj.remarks = "";
      // }
      editCustomer(obj).then((res) => {
        this.editLoading = false;
        this.$message({
          message: res.message,
          type: "success",
        });
        console.log(obj, "1111");
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
    user.permission <= "5" ? (this.ifAdmin = true) : (this.ifAdmin = false);
    this.getTableData();
    this.getUserData();
  },
};
</script>

<style scoped></style>
