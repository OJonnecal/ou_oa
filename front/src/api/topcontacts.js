import axios from 'axios';

let base = 'http://localhost:8081';
//常用联系人api
export const getTopContactsList = params => { return axios.post(`${base}/topcontacts/getTopContactsList`, params).then(res => res.data); };
export const addTopContacts = params => { return axios.post(`${base}/topcontacts/addTopContacts`, params).then(res => res.data); };
export const delTopContacts = params => { return axios.post(`${base}/topcontacts/delTopContacts`, params).then(res => res.data); };
export const editTopContacts = params => { return axios.post(`${base}/topcontacts/updateTopContacts`, params).then(res => res.data); };