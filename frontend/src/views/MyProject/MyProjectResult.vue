<template>
    <div>
        <my-project-path :department="'내 프로젝트'" :project="projectName" />
        <h1 id="my-project-result-title">{{ projectName }}</h1>
        <tab id="my-project-result-tab" :list="tabList" />
        <router-view />
    </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
import verifyApi from "@/api/verify.js";
export default {
    name: "MyProjectResult",
    components: { MyProjectPath, Tab },
    data() {
        return {
            projectId: this.$route.query.projectId,
            projectName: "",
            tabList: [],
        };
    },
    created() {
        this.tabList = [
            {
                name: "Summary",
                path: "/project/result/summary",
            },
            {
                name: "Opensource",
                path: "/project/result/opensource",
            },
            {
                name: "License",
                path: "/project/result/license",
            },
        ];
        this.GetProjectName();
    },
    methods: {
        GetProjectName: function () {
            verifyApi
                .readVerifiedProjectName(this.projectId)
                .then((response) => {
                    this.projectName = response.data;
                });
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectResult.css"></style>
