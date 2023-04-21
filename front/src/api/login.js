import axios from 'axios';

let base = 'http://localhost:8081';

export const requestLogin = params => { return axios.post(`${base}/user/login`, params).then(res => res.data); };