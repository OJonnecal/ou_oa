import axios from 'axios';

let base = 'http://localhost:8081';

export const getExpenseList = params => { return axios.post(`${base}/expense/getExpenseList`, params).then(res => res.data); };

export const getApplyExpenseList = params => { return axios.post(`${base}/expense/getApplyExpenseList`, params).then(res => res.data); };

export const getFailExpenseList = params => { return axios.post(`${base}/expense/getFailExpenseList`, params).then(res => res.data); };

export const delExpense = params => { return axios.post(`${base}/expense/delExpense`, params).then(res => res.data); };

export const addExpense = params => { return axios.post(`${base}/expense/addExpense`, params).then(res => res.data); };

export const agreeExpense = params => { return axios.post(`${base}/expense/agreeExpense`, params).then(res => res.data); };