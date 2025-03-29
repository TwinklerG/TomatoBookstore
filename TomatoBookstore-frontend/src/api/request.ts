import axios from "axios";

const service = axios.create();

service.defaults.withCredentials = true;

// Backend Address and Timeout
service.defaults.baseURL = "http://localhost:8080";
service.defaults.timeout = 3000;

export { service as axios };
