<template>
    <div>
        <button @click="GoProjectResult">프로젝트 결과 보기</button>
    </div>
</template>
<script>
export default {
    name: "MyProjectSummary",
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
