<template>
    <div>
        <my-project-path :department="'프로젝트'" :project="'Edit'" />
        <h1 id="myproject-edit-title">Edit</h1>
        <div id="myproject-edit-container">
            <div id="myproject-edit-selected-div">
                <div id="myproject-edit-divider"></div>
                <repository-card
                    v-for="(repository, index) in selectedRepositoryList"
                    :key="`${index}_repositoryList`"
                    :repository="repository"
                    :choiced="true"
                    @selectedRepoClick="SelectedRepoClick(index)"
                ></repository-card>
                <button>submit</button>
            </div>
            <div id="myproject-edit-tab-div">
                <tab id="myproject-edit-tab" :list="tabList" />
                <router-view
                    id="myproject-edit-routerview"
                    @addRepoClick="AddRepoClick"
                ></router-view>
            </div>
        </div>
    </div>
</template>
<script>
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
// import MyProjectEditSelected from "../../components/MyProject/MyProjectEditSelected.vue";
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
    name: "MyProjectEdit",
    components: { MyProjectPath, Tab, RepositoryCard },
    data() {
        return {
            tabList: [
                {
                    name: "GitLab",
                    path: "/project/main/edit/gitlab",
                },
            ],
            selectedRepositoryList: [],
        };
    },
    methods: {
        SelectedRepoClick: function (index) {
            this.selectedRepositoryList.splice(index, 1);
        },
        AddRepoClick: function (repo) {
            this.selectedRepositoryList.push(repo);
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEdit.css"></style>
