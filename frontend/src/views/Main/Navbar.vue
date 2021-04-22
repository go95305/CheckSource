<template>
  <nav class="sidenav">
    <router-link class="title-font" to="/dashboard">대시보드</router-link>
    <router-link to="/dashboard">마이페이지</router-link>
    <router-link to="/dashboard">전체목록</router-link>
</nav>
</template>

<script>
export default {
  name: "Navbar",
  components: {},
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
