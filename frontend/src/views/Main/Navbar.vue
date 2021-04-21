<template>
  <nav class="sidenav">
    <profile id="nav-profile" />
    <router-link class="title-font" to="/dashboard">대시보드</router-link>
    <router-link to="/dashboard">마이페이지</router-link>
    <router-link to="/dashboard">전체목록</router-link>
    <div id="nav-logo">
        <img src="http://placehold.it/100x100" alt="nav-logo" />
    </div>
  </nav>
</template>

<script>
import Profile from "@/components/Main/Profile";
export default {
  name: "Navbar",
  components: { Profile },
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
