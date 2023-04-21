import axios from 'axios';

let base = 'http://localhost:8081';

//公告
export const getGg = params => { return axios.post(`${base}/notice/getNoticeList`, params).then(res => res.data); };
//新增公告
export const addGg = params => { return axios.post(`${base}/notice/addNotice`, params).then(res => res.data); };
//删除公告
export const delGg = params => { return axios.post(`${base}/notice/delNotice`, params).then(res => res.data); };