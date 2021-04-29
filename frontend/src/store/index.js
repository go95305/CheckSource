import Vue from "vue";
import Vuex from "vuex";
import loginApi from "../api/login";
import axios from "axios";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    Name: "",
    userId: "",
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
        if (response.data.token == null) {
          alert("유저의 데이터가 존재하지 않습니다. 입력 해주세요.");
          this.$router.push({
            name: "AfterLogin",
            params: { userId: userId },
          });
        } else {
          context.commit("LOGIN", response);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["token"]}`;
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
