<template>
  <div>
      <el-button
        type="primary"
        icon="el-icon-s-claim"
        style="margin-top: 10px; font-size: 10px"
        @click.native="start"
        >上班打卡</el-button
      >
      <el-button
        type="primary"
        icon="el-icon-s-claim"
        style="margin-top: 10px; font-size: 10px"
        @click.native="end"
        >下班打卡</el-button
      >
    <el-calendar>
      <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
      <template slot="dateCell" slot-scope="{ date, data }"  >
        <p :class="data.isSelected ? 'is-selected' : '' ">
          {{ data.day.split("-").slice(1).join("-") }}
          <i  v-for="item in dataList" v-if="item.date==data.day">
              {{ item.type==1 ? "上" : "" }}{{ item.type==2 ? "下" : "" }}
          </i>
          <!-- {{ data.isSelected ? "✔️" : "" }} -->
        </p>
      </template>
    </el-calendar>
  </div>
</template>

<script>
import { addStart,addEnd,getData } from '../../api/clockin.js';
export default {
  data() {
    return {
      dataList: [],
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      var user = sessionStorage.getItem('user');
      getData(user).then((res) => {
				this.dataList = res.data.dataList
			});
    },
    start() {
      var user = sessionStorage.getItem('user');
      addStart(user).then((res) => {
				const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: 'success'
          });
          this.getData()
        } else {
          this.$message({
            message: res.message,
            type: 'error'
          });
        }
			})
    },
    end() {
      var user = sessionStorage.getItem('user');
      addEnd(user).then((res) => {
        const statusCode = res.code;
        if (statusCode == 200) {
          this.$message({
            message: res.message,
            type: 'success'
          });
          this.getData()
        } else {
          this.$message({
            message: res.message,
            type: 'error'
          });
        }
			})

    },
  },
};
</script>
<style>
  .is-selected {
    color: #1989FA;
  }
</style>
