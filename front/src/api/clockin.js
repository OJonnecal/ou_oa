import axios from "axios";
axios.defaults.headers["Content-Type"] = "application/json; charset=utf-8";

let base = "http://localhost:8081";

export const addStart = (params) => {
  return axios.post(`${base}/clockin/addStart`, params).then((res) => res.data);
};
export const addEnd = (params) => {
  return axios.post(`${base}/clockin/addEnd`, params).then((res) => res.data);
};
export const getData = (params) => {
  return axios.post(`${base}/clockin/getData`, params).then((res) => res.data);
};
