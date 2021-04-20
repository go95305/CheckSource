<template>
  <v-app>
    <navbar></navbar>
    <v-main id="main">
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import "@/assets/css/App.css"
import Navbar from './views/Navbar.vue';

export default {
  name: "App",

  components: {
    Navbar,
  },

  data: () => ({
    //
  }),
};
</script>
