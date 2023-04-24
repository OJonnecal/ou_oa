import axios from 'axios';

let base = 'http://localhost:8081';

//项目进度
export const getProjectList = params => { return axios.post(`${base}/project/getProjectList`, params).then(res => res.data); };

export const editProject = params => { return axios.post(`${base}/project/updateProject`, params).then(res => res.data); };

export const delProject = params => { return axios.post(`${base}/project/delProject`, params).then(res => res.data); };

export const addProject = params => { return axios.post(`${base}/project/addProject`, params).then(res => res.data); };