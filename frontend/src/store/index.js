import Vue from "vue";
import Vuex from "vuex";
import http from "../api/http";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    departName: "",
    userName: "",
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.departName = payload["departName"];
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
    LOGIN(context, loginInfo) {
      return http.login(loginInfo).then((response) => {
        context.commit("LOGIN", response.data);
        axios.defaults.headers.common[
          "auth-token"
        ] = `${response.data["auth-token"]}`;
      });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
    },
  },
  modules: {},
});
