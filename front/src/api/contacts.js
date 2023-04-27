import axios from 'axios';

let base = 'http://localhost:8081';

export const getContactsList = params => { return axios.post(`${base}/contacts/getContactsList`, params).then(res => res.data); };

export const editContacts = params => { return axios.post(`${base}/contacts/updateContacts`, params).then(res => res.data); };

export const delContacts = params => { return axios.post(`${base}/contacts/delContacts`, params).then(res => res.data); };

export const addContacts = params => { return axios.post(`${base}/contacts/addContacts`, params).then(res => res.data); };