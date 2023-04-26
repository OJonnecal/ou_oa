import axios from 'axios';

let base = 'http://localhost:8081';

export const getLeaveList = params => { return axios.post(`${base}/leave/getLeaveList`, params).then(res => res.data); };

export const getApplyLeaveList = params => { return axios.post(`${base}/leave/getApplyLeaveList`, params).then(res => res.data); };

export const getFailLeaveList = params => { return axios.post(`${base}/leave/getFailLeaveList`, params).then(res => res.data); };

export const editLeave = params => { return axios.post(`${base}/leave/updateLeave`, params).then(res => res.data); };

export const delLeave = params => { return axios.post(`${base}/leave/delLeave`, params).then(res => res.data); };

export const addLeave = params => { return axios.post(`${base}/leave/addLeave`, params).then(res => res.data); };

export const agreeLeave = params => { return axios.post(`${base}/leave/agreeLeave`, params).then(res => res.data); };