import axios from 'axios';

let base = 'http://localhost:8081';

export const getUserList = params => { return axios.post(`${base}/user/getUserList`, params).then(res => res.data); };

export const getHys = params => { return axios.post(`${base}/meetingroom/getMeetingRoomList`, params).then(res => res.data); };

export const editHys = params => { return axios.post(`${base}/meetingroom/updateMeetingRoom`, params).then(res => res.data); };

export const delHys = params => { return axios.post(`${base}/meetingroom/delMeetingRoom`, params).then(res => res.data); };

export const addHys = params => { return axios.post(`${base}/meetingroom/addMeetingRoom`, params).then(res => res.data); };