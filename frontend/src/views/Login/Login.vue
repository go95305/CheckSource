<template>
  <div class="background">
    <div class="page-container">
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
              <input
                v-model="loginInfo.EmployeeNumber"
                placeholder="사번"
                type="text"
              />
            </div>
            <div class="login-input-wrap input-password">
              <i class="fas fa-key"></i>
              <input
                v-model="loginInfo.Password"
                placeholder="비밀번호"
                type="password"
              />
            </div>
            <p v-if="!isLogin">로그인 정보가 틀렸습니다. 다시 입력해주세요.</p>
          </div>
          <div class="login-btn-wrap">
            <button class="login-btn" @click="showInfo">Login</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
      loginInfo: {
        EmployeeNumber: "",
        Password: "",
      },
      isLogin: true,
    };
  },
  methods: {
    showInfo() {
      this.$router.push({
        name: "AfterLogin",
        params: { employeeNumber: this.loginInfo.EmployeeNumber },
      });
    },
    login() {
      this.isLogin = false;
    },
  },
};
</script>
<style scoped src="../../assets/css/Main/Login.css"></style>
