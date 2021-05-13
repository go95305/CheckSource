<template>
    <div>
        <my-project-path :department="'프로젝트'" />
        <div class="myproject-title-div">
            <h1>ICT운영부</h1>
            <router-link
                class="myproject-edit-router-link"
                to="/project/main/newproject"
                ><span class="material-icons"> add </span>New
                Project</router-link
            >
        </div>
        <hr class="verify-header" />
        <div class="verify-body">
            <VerifyCard
                v-for="(project, index) in projectList"
                :key="`${index}_projectList`"
                :project="project"
                @goReport="GoReport"
            />
        </div>
    </div>
</template>

<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import VerifyCard from "@/components/DashBoard/VerifyCard.vue";
import { mapGetters } from "vuex";
import verifyApi from "@/api/verify.js";
export default {
    name: "MyProjectSummary",
    components: { MyProjectPath, VerifyCard },
    data() {
        return {
            projectList: [],
        };
    },
    computed: {
        ...mapGetters(["getDepartment"]),
    },
    created() {
        this.GetProjectList();
    },
    methods: {
        GoReport: function (projectId) {
            this.$router.push({
                name: "Summary",
                query: { projectId: projectId },
            });
        },
        GetProjectList: function () {
            verifyApi
                .readVerifiedProjectList(this.getDepartment)
                .then((response) => {
                    this.projectList = response.data;
                });
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjects.css"></style>
