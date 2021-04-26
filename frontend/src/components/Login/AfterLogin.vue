<template>
  <div class="background">
    <div class="login-form-container shadow">
      <div class="login-form-right-side-after">
        <div class="top-logo-wrap"></div>
        <!-- <img id="right-side-logo" src="@/assets/shinhan.png" /> -->
        <h2>{{ MyInfo.EmployeeNumber }}님 반갑습니다.</h2>
        <p>처음 사용하시는 사원은 개인 정보를 입력해주세요.</p>
      </div>
      <div class="login-form-left-side">
        <div class="login-top-wrap"></div>
        <div class="login-input-container">
          <div class="login-input-wrap input-name">
            <i class="far fa-envelope"></i>
            <input placeholder="성명" type="text" />
          </div>
          <div class="login-input-wrap input-department">
            <i class="fas fa-key"></i>
            <input placeholder="부서" type="text" />
          </div>
          <div class="login-input-wrap input-rank">
            <i class="fas fa-key"></i>
            <input placeholder="직급" type="text" />
          </div>
        </div>
        <div class="login-btn-wrap">
          <button class="login-btn">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ["employeeNumber"],
  name: "AfterLogin",
  data() {
    return {
      MyInfo: {
        EmployeeNumber: "",
      },
      Password: "",
      isLogin: true,
    };
  },
  created() {
    this.MyInfo.EmployeeNumber = this.$route.params.employeeNumber;
  },
};
</script>
<style scoped src="../../assets/css/Login/Login.css"></style>
