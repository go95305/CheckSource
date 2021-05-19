import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import loginApi from "../api/login";
import router from "../router/index";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    accessToken: null,
    userId: "",
    name: "",
    job: "",
    department: "",
    userImg: "",
    gitlabList: [],
    githubList: [],
    githubUsername:"",
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    },
    getName(state) {
      return state.name;
    },
    getJob(state) {
      return state.job;
    },
    getDepartment(state) {
      return state.department;
    },
    getUserImg(state) {
      return state.userImg;
    },
    getGitLabList(state) {
      return state.gitlabList;
    },
    getGitHubList(state) {
      return state.githubList;
    },
    getGithubUsername(state){
      return state.githubUsername;
    }
  },
  mutations: {
    SAVEUSERID(state, userId) {
      state.userId = userId;
    },
    CONNECTGITLAB(state, account) {
      //flag 요소 지우기
      delete account.flag;

      //같은 gitlabid가 있으면 삭제
      let temp = state.gitlabList;
      for (let i = 0; i < temp.length; ++i) {
        if (temp[i].gitlabId == account.gitlabId) {
          temp.splice(i, 1);
        }
      }
      //배열에 추가
      temp.push(account);
      //gitlabid 순서대로 정렬
      temp.sort(function (a, b) {
        return a.gitlabId - b.gitlabId;
      });
      state.gitlabList = [];
      state.gitlabList = temp;
    },
    CONNECTGITHUB(state, account) {
      delete account.flag;

      let temp = state.githubList;
      for (let i = 0; i < temp.length; ++i) {
        if (temp[i].githubId == account.githubId) {
          temp.splice(i, 1);
        }
      }

      temp.push(account);
      //githubid 순서대로 정렬
      temp.sort(function (a, b) {
        return a.githubId - b.githubId;
      });
      state.githubUsername=account.username;
      state.githubList = [];
      state.githubList = temp;
    },
    DISCONNECTGITLAB(state, index) {
      state.gitlabList.splice(index, 1);
    },
    DISCONNECTGITHUB(state, index) {
      state.githubList.splice(index, 1);
    },
    LOGIN(state, payload) {
      state.accessToken = payload["token"];
      state.job = payload["job"];
      state.userImg = payload["userImg"];
      state.gitlabList = payload["gitlabList"];
      state.githubList=payload["githubList"];
      state.name = payload["name"];
      state.department = payload["depart"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = "";
      state.job = "";
      state.userImg = "";
      state.gitlabList = [];
      state.githubList=[];
      state.name = "";
      state.department = "";
    },
  },
  actions: {
    CHECKUSER(context, userId) {
      //사용자 정보 유무 확인
      loginApi.checkUser(userId).then((response) => {
        context.commit("SAVEUSERID", userId);
        console.log(response.data);
        if (response.data.flag) {
          //사용자 정보가 있으면
          context.commit("LOGIN", response.data);
          router.push("/dashboard");
        } else {
          //사용자 정보가 없으면
          router.push("/afterLogin");
        }
      });
    },
    USERFORM(context, userform) {
      //사용자 초기 정보 입력
      loginApi
        .userForm(userform)
        .then((response) => {
          if (response.data.flag) {
            //입력 완료
            context.commit("LOGIN", response.data);
            router.push("/dashboard");
          } else {
            alert("사용자 정보 입력에 실패했습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
          alert("사용자 정보 입력에 실패했습니다.");
        });
    },
    UPDATEUSER(context, userform) {
      loginApi
        .userUpdate(userform)
        .then((response) => {
          if (response.data.flag) {
            //입력 완료
            context.commit("LOGIN", response.data);
            alert("변경 성공");
          } else {
            alert("사용자 정보 변경에 실패했습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    LOGOUT(context) {
      //로그아웃
      loginApi
        .userLogout()
        .then(() => {
          context.commit("LOGOUT");
          router.push("/");
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
  },
});
