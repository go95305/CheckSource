<template>
    <div>
        <my-project-path :department="'프로젝트'" :project="'Edit'" />
        <h1 id="myproject-edit-title">Edit</h1>
        <div id="myproject-edit-container">
            <div id="myproject-edit-selected-div">
                <h3 id="myproject-edit-selected-title">선택된 프로젝트</h3>
                <repository-card
                    v-for="(repository, index) in selectedRepositoryList"
                    :key="`${index}_repositoryList`"
                    :repository="repository"
                    :selected="true"
                    @selectedRepoClick="SelectedRepoClick"
                ></repository-card>
                <button id="myproject-edit-selected-button">검증하기</button>
            </div>
            <div id="myproject-edit-tab-div">
                <tab id="myproject-edit-tab" :list="tabList" />
                <router-view
                    id="myproject-edit-routerview"
                    :selectedRepositoryList="selectedRepositoryList"
                    @addRepoClick="AddRepoClick"
                ></router-view>
            </div>
        </div>
    </div>
</template>
<script>
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
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
    created() {
        this.GetSelectedRepositories();
    },
    methods: {
        GetSelectedRepositories: function () {
            this.selectedRepositoryList = [
                {
                    project_id: "111",
                    name: "project1",
                    gittype: 1,
                },
                {
                    project_id: "114",
                    name: "project4",
                    gittype: 2,
                },
                {
                    project_id: "112",
                    name: "project2",
                    gittype: 1,
                },
                {
                    project_id: "113",
                    name: "project3",
                    gittype: 2,
                },
            ];
        },
        SelectedRepoClick: function (project_id) {
            // 선택된 레포지토리 클릭 => selectedList에서 삭제
            let index = this.selectedRepositoryList.findIndex(
                (i) => i.project_id == project_id
            );
            this.selectedRepositoryList.splice(index, 1);
        },
        AddRepoClick: function (repo) {
            //레포지토리 클릭 => selectedList에 추가
            this.selectedRepositoryList.push(repo);
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEdit.css"></style>
