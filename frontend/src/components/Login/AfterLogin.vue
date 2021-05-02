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
          <div class="login-input-wrap input-name">
            <i class="far fa-envelope"></i>
            <input placeholder="성명" type="text" />
          </div>
          <div class="login-input-wrap input-department">
            <i class="fas fa-key"></i>
            <select
              name="department"
              id="mypage-select"
              v-model="profile.department"
            >
              <option value="1">금융개발부</option>
              <option value="2">디지털개발부</option>
              <option value="3">글로벌개발부</option>
              <option value="4">기관개발부</option>
              <option value="5">정보개발부</option>
              <option value="6">ICT운영부</option>
              <option value="7">ICT기획부</option>
            </select>
          </div>
          <div class="login-input-wrap input-rank">
            <i class="fas fa-key"></i>
            <select name="rank" id="mypage-select" v-model="profile.rank">
              <option value="1">선임</option>
              <option value="2">수석</option>
              <option value="3">부장</option>
            </select>
          </div>
          <div class="login-input-wrap input-gitlabId">
            <i class="fas fa-key"></i>
            <input placeholder="GitLab 아이디(선택)" type="text" />
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
  name: "AfterLogin",
  data() {
    return {
      profile: {
        name: "김신한",
        department: "2",
        rank: "1",
      },
    };
  },
};
</script>
<style scoped src="../../assets/css/Login/Login.css"></style>
