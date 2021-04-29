<template>
  <div class="login-form-container shadow">
    <div class="login-form-right-side-before">
      <div class="top-logo-wrap"></div>
      <img id="right-side-logo" src="@/assets/shinhan.png" />
      <h3>Shinhan Bank Opensource Validation</h3>
    </div>
    <div class="login-form-left-side">
      <div class="login-top-wrap"></div>
      <div class="login-input-container">
        <div class="login-input-wrap input-id">
          <i class="far fa-envelope"></i>
          <input v-model="loginInfo.userId" placeholder="사번" type="text" />
        </div>
        <div class="login-input-wrap input-password">
          <i class="fas fa-key"></i>
          <input
            v-model="loginInfo.pw"
            placeholder="비밀번호"
            type="password"
          />
        </div>
        <p v-if="!isLogin">로그인 정보가 틀렸습니다. 다시 입력해주세요.</p>
      </div>
      <div class="login-btn-wrap">
        <button class="login-btn" @click="loginChk">Login</button>
      </div>
    </div>
  </div>
</template>
<script>
// import http from "../../api/http.js";
import loginApi from "../../api/login.js";
export default {
  name: "BeforeLogin",
  data() {
    return {
      loginInfo: {
        userId: "",
        pw: "",
      },
      isLogin: true,
    };
  },
  methods: {
    loginChk() {
      loginApi
        .login(this.loginInfo)
        .then((response) => {
          alert(response.data);
          if (response.data == true) {
            console.log("해당 유저 존재");
            this.$store
              .dispatch("CHECKUSER", this.loginInfo.userId)
              .then(() => this.showInfo());
          } else {
            alert("해당 유저는 존재하지 않습니다.");
          }
        })
        .catch(() => {
          console.log("login error");
        });
    },
  },
};
</script>
<style scoped src="../../assets/css/Login/Login.css"></style>
