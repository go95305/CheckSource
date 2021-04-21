<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>

export default {
  name:'App',

}
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap%27');
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

* { 
  margin: 0 !important;
}

#app {
  font-family: 'Noto Sans KR';
}

.title-font{
  font-family: 'Do Hyeon';
}
</style>
