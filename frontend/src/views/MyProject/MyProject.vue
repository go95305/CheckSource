<template>
  <div>
    <div id="my-project">
      <my-project-path :department="'내 프로젝트'" />
      <h1 id="my-project-title">내 프로젝트</h1>
      <tab id="my-project-tab" name="my-project" :list="tabList" />
      <router-view/>
    </div>
  </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
  name: "MyProject",
  components: { MyProjectPath, Tab },
  data() {
    return {
      tabList: ["Summary", "Department", "My Project"],
    };
  },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProject.css"></style>
