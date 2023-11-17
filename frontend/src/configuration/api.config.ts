import axios from "axios";

const baseurl: string = process.env.REACT_APP_SERVER_BASE_URL as string
export const instance = axios.create({
    //withCredentials: true,
    headers: {'userId': localStorage.getItem('userId')},
    baseURL: baseurl,
});

