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
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          clearable
          @keyup.enter.native="handleQuery"
          placeholder="请输入会议室名称"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          :clearable="true"
          placeholder="会议室状态"
        >
          <el-option key="1" label="使用中" value="使用中" />
          <el-option key="2" label="空闲" value="空闲" />
          <el-option key="3" label="维修中" value="维修中" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="Search" type="primary" @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item>
    </el-form>
    <el-dialog title="添加会议室" :visible.sync="addHysFormVisible">
      <el-form :model="addHysForm" label-width="100px" ref="addHysForm">
        <el-form-item label="会议室名称" prop="title">
          <el-input v-model="addHysForm.name" placeholder="会议室名称不能为空"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="addHysForm.status" placeholder="状态不能为空">
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
        :data="
          meetingroomList.slice(
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
          prop="name"
          label="会议室名称"
          width="200"
          sortable
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="status"
          label="状态"
          width="200"
          sortable
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="remarks"
          label="备注"
          min-width="180"
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
          :total="meetingroomList.length"
        >
        </el-pagination>
      </div>
    </template>

    <!--编辑界面-->
    <el-dialog
      title="编辑会议室状态"
      :visible.sync="editFormVisible"
      :close-on-click-modal="false"
    >
      <el-form :model="editForm" label-width="100px" ref="editForm">
        <el-form-item label="会议室名称" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="会议室状态" prop="status">
          <el-select v-model="editForm.status">
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
        id: "",
        name: "",
        status: "",
        remarks: "",
      },
      addHysForm: {
        name: "",
        status: "",
        remarks: "",
      },
      queryParams: {
        name: "",
        status: "",
      },
      addHysFormVisible: false,
      currentPage: 1, // 当前页码
      total: 20, // 总条数
      pageSize: 5, // 每页的数据条数
    };
  },
  methods: {
    handleQuery() {
      this.getTableData();
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
      this.loading = true;
      getHys(this.queryParams).then((res) => {
        this.meetingroomList = res.data.meetingRoomList;
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
      })
        .then(() => {
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
      this.editForm.id = row.id;
      this.editForm.name = row.name;
      this.editForm.status = row.status;
      this.editForm.remarks = row.remarks;
    },
    editSubmit: function () {
      this.editLoading = true;
      var obj = {
        id: this.editForm.id,
        name: this.editForm.name,
        status: this.editForm.status,
        remarks: this.editForm.remarks,
      };
      editHys(obj).then((res) => {
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
    this.getUserData();
  },
};
</script>

<style scoped></style>
