<template>
  <nav class="sidenav">
    <profile id="nav-profile" />
    <router-link :class="{ sidenav_choiced: currentPage == 1 }" to="/dashboard"
      >대시보드</router-link
    >
    <router-link :class="{ sidenav_choiced: currentPage == 2 }" to="/dashboard"
      >마이페이지</router-link
    >
    <router-link :class="{ sidenav_choiced: currentPage == 3 }" to="/dashboard"
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
      currentPage: 0,
      currentPathName: "",
    };
  },
  watch: {
    currentPathName: function () {
      switch (this.currentPathName) {
        case "DashBoard":
          this.currentPage = 1;
          break;
      }
    },
  },
  created() {
    this.currentPathName = this.$route.name;
  },
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
