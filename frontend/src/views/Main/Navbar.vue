<template>
  <nav class="sidenav">
    <profile id="nav-profile" />
    <router-link
      :class="{ sidenav_choiced: currentPathName == 'DashBoard' }"
      to="/dashboard"
      >대시보드</router-link
    >
    <router-link
      :class="{ sidenav_choiced: currentPathName == 'MyPage' }"
      to="/login"
      >마이페이지</router-link
    >
    <router-link
      :class="{ sidenav_choiced: currentPathName == 'TotalList' }"
      to="/dashboard"
      >전체목록</router-link
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
      this.currentPathName = to.name;
    },
  },
  created() {
    this.currentPathName = this.$route.name;
  },
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
