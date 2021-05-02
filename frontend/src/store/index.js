import Vue from "vue";
import Vuex from "vuex";
import loginApi from "../api/login";
import axios from "axios";
import router from '../router/index'
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    Name: "",
    userId: "",
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getName(state) {
      return state.Name;
    },
    getUserId(state) {
      return state.userId;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["token"];
      state.departName = payload["depart"];
      state.userName = payload["name"];
      localStorage.setItem("key", state.accessToken);
      localStorage.setItem("name", state.userName);
      localStorage.setItem("department", state.departName);
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.departName = "";
      state.userName = "";
      localStorage.removeItem("key");
      localStorage.removeItem("name");
      localStorage.removeItem("department");
    },
  },
  actions: {
    CHECKUSER(context, userId) {
      loginApi.checkUser(userId).then((response) => {
        console.log(response.data.token);
        if (response.data.token == null) {
          alert("유저의 데이터가 존재하지 않습니다. 입력 해주세요.");
          router.push({
            name: "AfterLogin",
          });
        } else {
          alert("존재");
          context.commit("LOGIN", response.data);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["token"]}`;
          router.push("/dashboard");
        }
      });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
    },
  },
  modules: {},
});
