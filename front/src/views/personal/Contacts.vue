<template>
  <section>
    <el-button
      style="margin: 10px 0"
      type="primary"
      size="small"
      icon="el-icon-plus"
      @click="addContacts"
      >添加联系人</el-button
    >
    <el-dialog title="添加联系人" :visible.sync="addContactsFormVisible">
      <el-form :model="addContactsForm" label-width="100px" ref="addContactsForm">
        <el-form-item label="姓名" prop="title">
          <el-input v-model="addContactsForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="title">
          <el-input v-model="addContactsForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="title">
          <el-input v-model="addContactsForm.remarks"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addContactsFormVisible = false"
          >取消</el-button
        >
        <el-button size="small" type="primary" @click.native="addContactsSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
    <!--列表-->
    <template>
      <el-table
        :data="contactsList"
        highlight-current-row
        v-loading="loading"
        style="width: 100%"
        max-height="550"
      >
        <el-table-column type="index" width="60" label="序号" align="center"> </el-table-column>
        <el-table-column prop="name" label="联系人姓名" width="200" align="center">
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="200" align="center">
        </el-table-column>
        <el-table-column prop="remarks" label="备注" min-width="180" align="center">
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
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
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input type="textarea" v-model="editForm.remarks"></el-input>
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
  getContactsList,
  editContacts,
  delContacts,
  addContacts,
} from "../../api/contacts.js";
export default {
  data() {
    return {
      filters: {
        name: "",
      },
      read: true,
      loading: false,
      editLoading: false,
      editFormVisible: false,
      contactsList: [],
      editForm: {
        id: "",
        name: "",
        phone: "",
        remarks: "",
      },
      addContactsForm: {
        name: "",
        phone: "",
        remarks: "",
      },
      addContactsFormVisible: false,
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
      getContactsList().then((res) => {
        console.log(res);
        this.contactsList = res.data.contactsList;
        console.log(this.contactsList, "contactsList");
        this.loading = false;
      });
    },
    addContacts() {
      this.addContactsFormVisible = true;
    },
    addContactsSubmit() {
      //   this.addContactsForm.time = new Date().toLocaleDateString();
      let param = {
        name: this.addContactsForm.name,
        phone: this.addContactsForm.phone,
        remarks: this.addContactsForm.remarks,
      };
      addContacts(param).then((res) => {
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
      this.addContactsFormVisible = false;
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此客户吗", "提示", {
        type: "warning",
      })
        .then(() => {
          delContacts(obj).then((res) => {
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
      editContacts(obj).then((res) => {
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
    this.getTableData();
    this.getUserData();
  },
};
</script>

<style scoped></style>
