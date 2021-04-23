<template>
  <nav class="sidenav">
    <profile id="nav-profile" />
    <router-link
      :class="{ sidenav_choiced: currentPathName == 'dashboard' }"
      to="/dashboard"
      >대시보드</router-link
    >
    <router-link
      :class="{ sidenav_choiced: currentPathName == 'myproject' }"
      to="/myproject/main/summary"
      >내 프로젝트</router-link
    >
    <router-link
      :class="{ sidenav_choiced: currentPathName == 'TotalList' }"
      to="/dashboard"
      >전체목록</router-link
    >
       <router-link
      :class="{ sidenav_choiced: currentPathName == 'mypage' }"
      to="/mypage/profile"
      >마이페이지</router-link
    >
    <div id="nav-logo">
      <img src="@/assets/shinhan.png" width="150px" alt="nav-logo" />
    </div>
  </nav>
</template>

<script>
import Profile from "@/components/Main/Profile";
export default {
  name: "Navbar",
  components: { Profile },
  data() {
    return {
      currentPathName: "",
    };
  },
  watch: {
    $route(to) {
      //경로 이동 시 해당 경로 이름 설정
      this.currentPathName = to.path.split('/')[1];
    },
  },
  created() {
    this.currentPathName = this.$route.path.split('/')[1];
  },
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
