import axios from 'axios';

let base = 'http://localhost:8081';

export const getAssignmentInCompleteList = params => { return axios.post(`${base}/assignment/getAssignmentInCompleteList`, params).then(res => res.data); };

export const getAssignmentCompleteList = params => { return axios.post(`${base}/assignment/getAssignmentCompleteList`, params).then(res => res.data); };

export const completeAssignment = params => { return axios.post(`${base}/assignment/completeAssignment`, params).then(res => res.data); };

export const delAssignment = params => { return axios.post(`${base}/assignment/delAssignment`, params).then(res => res.data); };

export const addAssignment = params => { return axios.post(`${base}/assignment/addAssignment`, params).then(res => res.data); };