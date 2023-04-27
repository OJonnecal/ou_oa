import axios from 'axios';

let base = 'http://localhost:8081';

export const getQuitList = params => { return axios.post(`${base}/quit/getQuitList`, params).then(res => res.data); };

export const getApplyQuitList = params => { return axios.post(`${base}/quit/getApplyQuitList`, params).then(res => res.data); };

export const getFailQuitList = params => { return axios.post(`${base}/quit/getFailQuitList`, params).then(res => res.data); };

export const delQuit = params => { return axios.post(`${base}/quit/delQuit`, params).then(res => res.data); };

export const addQuit = params => { return axios.post(`${base}/quit/addQuit`, params).then(res => res.data); };

export const agreeQuit = params => { return axios.post(`${base}/quit/agreeQuit`, params).then(res => res.data); };