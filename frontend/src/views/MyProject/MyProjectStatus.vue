<template>
    <div>
        <my-project-path :department="'프로젝트'" />
        <h1 id="my-project-status-title">Status</h1>
        <button @click="GoProjectResult">프로젝트 결과 보기</button>
    </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
export default {
    name: "MyProjectSummary",
    components: { MyProjectPath },
    data() {
        return {
            projectId: "projectID입니다.",
        };
    },
    methods: {
        GoProjectResult: function () {
            this.$router.push({
                path: "/project/result/summary",
                query: { projectId: this.projectId },
            });
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectStatus.css"></style>
