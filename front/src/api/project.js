import axios from 'axios';

let base = 'http://localhost:8081';

//项目进度
export const getJd = params => { return axios.post(`${base}/project/getProjectList`, params).then(res => res.data); };