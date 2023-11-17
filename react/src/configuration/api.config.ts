import axios from "axios";

const baseurl: string = process.env.REACT_APP_SERVER_BASE_URL as string
export const instance = axios.create({
    //withCredentials: true,
    baseURL: baseurl,
});

instance.interceptors.request.use(
    (config) => {
        config.headers['userId'] = localStorage.getItem('userId')
        return config
    }
)

