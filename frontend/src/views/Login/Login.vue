<template>
  <div class="background">
    <!-- <div class="logo">
      <img src="@/assets/shinhan.png" />
    </div> -->

    <div class="page-container">
      <div class="login-form-container shadow">
        <div class="login-form-right-side">
          <div class="top-logo-wrap"></div>
          <img id="right-side-logo" src="@/assets/shinhan.png" />
          <h3>Shinhan Bank Opensource Validation</h3>
        </div>
        <div class="login-form-left-side">
          <div class="login-top-wrap"></div>
          <div class="login-input-container">
            <div class="login-input-wrap input-id">
              <i class="far fa-envelope"></i>
              <input v-model="EmployeeNumber" placeholder="사번" type="text" />
            </div>
            <div class="login-input-wrap input-password">
              <i class="fas fa-key"></i>
              <input
                v-model="Password"
                placeholder="비밀번호"
                type="password"
              />
            </div>
          </div>
          <div class="login-btn-wrap">
            <button class="login-btn" @click="login">Login</button>
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
      EmployeeNumber: "",
      Password: "",
      isLogin: true,
    };
  },
  methods: {},
};
</script>
<style scoped src="@/assets/css/Main/Login.css"></style>
