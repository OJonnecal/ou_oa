<template>
  <section>
    <!--工具条-->
    <!-- <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" size="small" placeholder="会议室编号"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" size="small" v-on:click="getTableData">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col> -->

    <!-- <el-button
      style="margin: 10px 0"
      type="primary"
      v-if="ifAdmin"
      size="small"
      icon="el-icon-chat-dot-round"
      @click="addMeetingNotice"
      >预约会议</el-button
    > -->
    <el-button
      style="margin: 10px 0"
      type="primary"
      v-if="ifAdmin"
      size="small"
      icon="el-icon-plus"
      @click="addHys"
      >添加会议室</el-button
    >
    <el-dialog title="添加会议室" :visible="addHysFormVisible">
      <el-form :model="addHysForm" label-width="100px" ref="addHysForm">
        <el-form-item label="会议室名称" prop="title">
          <el-input v-model="addHysForm.name"></el-input>
        </el-form-item>
        <el-form-item label="会议室状态">
          <el-select v-model="addHysForm.status">
            <el-option label="使用中" value="使用中"></el-option>
            <el-option label="空闲" value="空闲"></el-option>
            <el-option label="维修中" value="维修中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="title">
          <el-input v-model="addHysForm.remarks"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addHysFormVisible = false"
          >取消</el-button
        >
        <el-button size="small" type="primary" @click.native="addHysSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
    <!--列表-->
    <template>
      <el-table
        :data="meetingroomList"
        highlight-current-row
        v-loading="loading"
        style="width: 100%"
        max-height="550"
      >
        <el-table-column type="index" width="60"> </el-table-column>
        <el-table-column prop="name" label="会议室名称" width="150" sortable>
        </el-table-column>
        <el-table-column prop="status" label="会议室状态" width="150" sortable>
        </el-table-column>
        <el-table-column prop="remarks" label="备注" min-width="180">
        </el-table-column>
        <el-table-column label="操作" width="150" v-if="ifAdmin">
          <template scope="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDel(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </template>

    <!--编辑界面-->
    <el-dialog
      title="编辑会议室状态"
      :visible.sync="editFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="editForm" label-width="100px" ref="editForm">
        <el-form-item label="会议室名称" prop="name">
          <el-input v-model="editForm.name" :disabled="read"></el-input>
        </el-form-item>
        <el-form-item label="会议室状态" prop="status">
          <el-select v-model="editForm.status" @change="changee">
            <el-option label="使用中" value="使用中"></el-option>
            <el-option label="空闲" value="空闲"></el-option>
            <el-option label="维修中" value="维修中"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="使用时间">
					<el-date-picker type="date" placeholder="选择日期" v-model="editForm.birth"></el-date-picker>
				</el-form-item> -->
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
  getUserList,
  getHys,
  editHys,
  delHys,
  addHys,
} from "../../api/meetingroom.js";
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
      meetingroomList: [],
      editForm: {
        name: "",
        status: "",
        remarks: "",
      },
      addHysForm: {
        name: "",
        status: "",
        remarks: "",
      },
      addHysFormVisible: false,
    };
  },
  methods: {
    changee() {
      console.log(this.editForm.status);
    },
    //获取用户列表
    getUserData() {
      var obj = {
        userId: 123,
      };
      getUserList(obj).then((res) => {
        console.log(res, "res");
        this.userData = res.data.userList;
      });
    },
    //获取会议室列表
    getTableData: function () {
      // let obj = {
      // 	hysbh: this.search.hysbh
      // };
      this.loading = true;
      getHys().then((res) => {
        console.log(res);
        this.meetingroomList = res.data.meetingRoomList;
        console.log(this.meetingroomList, "user");
        this.loading = false;
      });
    },
    addHys() {
      this.addHysFormVisible = true;
    },
    addHysSubmit() {
      this.addHysForm.time = new Date().toLocaleDateString();
      let param = {
        name: this.addHysForm.name,
        status: this.addHysForm.status,
        remarks: this.addHysForm.remarks,
      };
      addHys(param).then((res) => {
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
      this.addHysFormVisible = false;
    },
    handleDel(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此会议室吗", "提示", {
        type: "warning",
      }).then(() => {
          delHys(obj).then((res) => {
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
      this.editForm.name = row.name;
      this.editForm.status = row.status;
      this.editForm.remarks = row.remarks;
    },
    editSubmit: function () {
      // this.$refs.editForm.validate((valid) => {
      // 	if (valid) {
        this.editLoading = true;
        //NProgress.start();
        var obj = {
          name: this.editForm.name,
          status: this.editForm.status,
          remarks: this.editForm.remarks,
        };
        console.log(obj);
        // if (obj.status == "空闲") {
        //   obj.remarks = "";
        // }
        editHys(obj).then((res) => {
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
    user.permission == "1" ? (this.ifAdmin = true) : (this.ifAdmin = false);
    this.getTableData();
    this.getUserData();
  },
};
</script>

<style scoped></style>
