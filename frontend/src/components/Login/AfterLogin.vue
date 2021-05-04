<template>
  <div class="background">
    <div class="login-form-container shadow">
      <div class="login-form-right-side-after">
        <div class="top-logo-wrap"></div>
        <!-- <img id="right-side-logo" src="@/assets/shinhan.png" /> -->
        <h2>반갑습니다.</h2>
        <p>처음 사용하시는 사원은 개인 정보를 입력해주세요.</p>
      </div>
      <div class="login-form-left-side">
        <div class="login-top-wrap"></div>
        <div class="login-input-container">
          <div class="title-name">
            <h3>이름</h3>
          </div>
          <div class="login-input-wrap input-name">
            <input placeholder="성명" type="text" />
          </div>
          <div class="title-name">
            <h3>부서</h3>
          </div>
          <div class="login-input-wrap input-department" >
            <label class="custom-select" for="styledSelect1"
              ><select id="styledSelect1" name="options" v-model="profile.department">
                <option value="">금융개발부</option>
                <option value="1">금융개발부</option>
                <option value="2">디지털개발부</option>
                <option value="3">글로벌개발부</option>
                <option value="4">기관개발부</option>
                <option value="4">정보개발부</option>
                <option value="4">ICT운영부</option>
                <option value="4">ICT기획부</option>
              </select></label
            >
          </div>
          <div class="title-name">
            <h3>직급</h3>
          </div>
          <div class="login-input-wrap input-department" >
            <label class="custom-select" for="styledSelect1"
              ><select id="styledSelect1" name="options" v-model="profile.rank">
                <option value="">선임</option>
                <option value="1">사원</option>
                <option value="2">대리</option>
              </select></label
            >
          </div>
        </div>
        <div class="login-btn-wrap">
          <button class="login-btn" @click="insert">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "AfterLogin",
  data() {
    return {
      profile: {
        name: "",
        department: "",
        rank: "",
      },
    };
  },
  methods: {
  },
};
</script>
<style scoped src="../../assets/css/Login/AfterLogin.css"></style>
