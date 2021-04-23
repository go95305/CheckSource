<template>
  <div>
    <my-project-path :department="'내 프로젝트 결과'" />
    <h1 id="my-project-result-title">{{projectId}}</h1>
    <tab id="my-project-result-tab" :list="tabList" />
    <router-view />
  </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
  name: "MyProjectResult",
  components: { MyProjectPath, Tab },
  data() {
    return {
      projectId: this.$route.query.projectId,
      tabList: [],
    };
  },
  created() {
    this.tabList = [
      {
        name: "Summary",
        path: "/myproject/result/summary?projectId=" + this.projectId,
      },
      {
        name: "Component",
        path: "/myproject/result/component?projectId=" + this.projectId,
      },
      {
        name: "License",
        path: "/myproject/result/license?projectId=" + this.projectId,
      },
    ];
  },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectResult.css"></style>
