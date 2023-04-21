<template>
  <div>
    <el-button
      style="margin: 10px 0"
      type="primary"
      v-if="ifAdmin"
      size="small"
      icon="el-icon-plus"
      @click="addGg"
      >添加公告</el-button
    >
    <el-button
      style="margin: 10px 0"
      type="primary"
      v-if="ifAdmin"
      size="small"
      icon="el-icon-plus"
      @click="addTopContacts"
      >添加常用联系人</el-button
    >
    <el-tabs type="border-card">
      <el-tab-pane label="公告栏">
        <el-table :data="GgList">
          <el-table-column
            prop="title"
            label="公告名称"
            width="150"
          ></el-table-column>
          <el-table-column prop="content" label="公告内容"></el-table-column>
          <el-table-column
            prop="time"
            label="公告发布时间"
            width="120"
          ></el-table-column>
          <el-table-column label="操作" width="150" v-if="ifAdmin">
            <template scope="scope">
              <el-button
                type="danger"
                size="small"
                @click.native="handleDelGg(scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="项目进度">
        <template>
          <div>
            <div class="jdt" v-for="item in jdArr" :key="item.id">
              <div class="word" style="display: inline">{{ item.name }}</div>
              <el-progress
                :percentage="item.rate"
                style="margin: 0 15px"
                type="circle"
                :stroke-width="18"
                :status="item.status"
              ></el-progress>
            </div>
          </div>
        </template>
      </el-tab-pane>
      <el-tab-pane label="常用联系人">
        <template>
          <el-table :data="topContactsList">
            <el-table-column
              prop="name"
              label="姓名"
              width="150"
            ></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column label="操作" width="150" v-if="ifAdmin">
              <template scope="scope">
                <el-button
                  type="danger"
                  size="small"
                  @click="handleDelTopContacts(scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-tab-pane>
    </el-tabs>
    <el-dialog title="添加公告" :visible="addGgFormVisible">
      <el-form :model="addGgForm" label-width="100px" ref="addGgForm">
        <el-form-item label="公告名称" prop="title">
          <el-input v-model="addGgForm.title"></el-input>
        </el-form-item>
        <el-form-item label="公告内容">
          <el-input v-model="addGgForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="addGgFormVisible = false"
          >取消</el-button
        >
        <el-button size="small" type="primary" @click.native="addGgSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
    <el-dialog title="添加常用联系人" :visible="addTopContactsFormVisible">
      <el-form
        :model="addTopContactsForm"
        label-width="100px"
        ref="addTopContactsForm"
      >
        <el-form-item label="联系人姓名" prop="title">
          <el-input v-model="addTopContactsForm.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addTopContactsForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="addTopContactsForm.email"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          size="small"
          @click.native="addTopContactsFormVisible = false"
          >取消</el-button
        >
        <el-button
          size="small"
          type="primary"
          @click.native="addTopContactsSubmit"
          >提交</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import util from "../common/js/util.js";
import { getGg, addGg, delGg } from "../api/notice.js";
import { getJd } from "../api/project.js";
import {
  getTopContactsList,
  addTopContacts,
  delTopContacts,
} from "../api/topcontacts.js";
export default {
  data() {
    return {
      GgList: [],
      jdArr: [],
      topContactsList: [],
      ifAdmin: false,
      addGgForm: {
        title: "",
        content: "",
        time: "",
      },
      addTopContactsForm: {
        name: "",
        phone: "",
        email: "",
      },
      addGgFormVisible: false,
      addTopContactsFormVisible: false,
    };
  },
  methods: {
    getGonggao() {
      var user = sessionStorage.getItem("user");
      user = JSON.parse(user);
      user.permission <= "5" ? (this.ifAdmin = true) : (this.ifAdmin = false);
      getGg().then((res) => {
        this.GgList = res.data.noticeList;
      });
    },
    getJindu() {
      getJd().then((res) => {
        this.jdArr = res.data.projectList;
      });
    },
    getTopContacts() {
      getTopContactsList().then((res) => {
        this.topContactsList = res.data.topContactsList;
      });
    },
    addGg() {
      this.addGgFormVisible = true;
    },
    addGgSubmit() {
      this.addGgForm.time = new Date().toLocaleDateString();
      let param = {
        title: this.addGgForm.title,
        content: this.addGgForm.content,
        // time: this.addForm.time.replace(/\//g,"-"),
        time: util.formatDate.format(
          new Date(this.addGgForm.time),
          "yyyy-MM-dd"
        ),
      };
      addGg(param).then((res) => {
        const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: "success",
          });
          this.getGonggao();
        } else {
          this.$message({
            message: res.message,
            type: "error",
          });
        }
      });
      this.addGgFormVisible = false;
    },
    addTopContacts() {
      this.addTopContactsFormVisible = true;
    },
    addTopContactsSubmit() {
      let param = {
        name: this.addTopContactsForm.name,
        phone: this.addTopContactsForm.phone,
        email: this.addTopContactsForm.email,
      };
      addTopContacts(param).then((res) => {
        const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: "success",
          });
          this.getTopContacts();
        } else {
          this.$message({
            message: res.message,
            type: "error",
          });
        }
      });
      this.addTopContactsFormVisible = false;
    },
    handleDelGg(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此公告吗", "提示", {
        type: "warning",
      }).then(() => {
        delGg(obj).then((res) => {
          const statusCode = res.code;
          if (statusCode == 200) {
            this.$message({
              message: res.message,
              type: "success",
            });
            this.getGonggao();
          } else {
            this.$message({
              message: res.message,
              type: "error",
            });
          }
        })
      })
      .catch(() => {});
    },
    handleDelTopContacts(row) {
      var obj = {
        id: row.id,
      };
      this.$confirm("确定要删除此常用联系人吗", "提示", {
        type: "warning",
      }).then(() => {
        delTopContacts(obj).then((res) => {
        const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: "success",
          });
          this.getTopContacts();
        } else {
          this.$message({
            message: res.message,
            type: "error",
          });
        }
      })
    })
      .catch(() => {});
    },
  },
  mounted() {
    this.getGonggao();
    this.getJindu();
    this.getTopContacts();
  },
};
</script>

<style scoped>
.el-progress {
  padding: 20px 0;
}
.jdt {
  display: inline;
}
.word {
  position: relative;
  top: -50px;
}
</style>
