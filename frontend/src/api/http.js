import axios from "axios";
const instance = axios.create({
	// baseURL: process.env.VUE_APP_BASE_URL,
	baseURL: "http://52.79.151.0:8080/",
});

export default instance;
