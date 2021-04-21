<template>
  <div>
    <navbar></navbar>
    <router-view id="main"></router-view>
  </div>
</template>

<script>
import Navbar from '@/views/Main/Navbar.vue';

export default {
   name: "Index",
  components: { Navbar },
};
</script>
<style scoped src="@/assets/css/Main/Index.css">
</style>