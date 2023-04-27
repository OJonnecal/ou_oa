import axios from 'axios';

let base = 'http://localhost:8081';

export const getCustomerList = params => { return axios.post(`${base}/customer/getCustomerList`, params).then(res => res.data); };

export const editCustomer = params => { return axios.post(`${base}/customer/updateCustomer`, params).then(res => res.data); };

export const delCustomer = params => { return axios.post(`${base}/customer/delCustomer`, params).then(res => res.data); };

export const addCustomer = params => { return axios.post(`${base}/customer/addCustomer`, params).then(res => res.data); };