import axios from 'axios';

let base = 'http://localhost:8081';

export const getUserList = params => { return axios.post(`${base}/user/getUserList`, params).then(res => res.data); };

export const editUser = params => { return axios.post(`${base}/user/updateUser`, params).then(res => res.data); };

export const delUser = params => { return axios.post(`${base}/user/delUser`, params).then(res => res.data); };

export const addUser = params => { return axios.post(`${base}/user/addUser`, params).then(res => res.data); };