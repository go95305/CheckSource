<template>
    <div>
        <my-project-path :department="'내 프로젝트'" />
        <h1 id="my-project-main-title">내 프로젝트</h1>
        <!-- <tab id="my-project-main-tab" :list="tabList" /> -->
        <router-view />
    </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
export default {
    name: "MyProjectMain",
    components: { MyProjectPath },
    data() {
        return {
            tabList: [
                {
                    name: "Summary",
                    path: "/myproject/main/summary",
                },
                {
                    name: "Department",
                    path: "/myproject/main/department",
                },
                {
                    name: "Project",
                    path: "/myproject/main/project",
                },
            ],
        };
    },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectMain.css"></style>
