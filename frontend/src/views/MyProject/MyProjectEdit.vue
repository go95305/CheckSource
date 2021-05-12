<template>
    <div>
        <my-project-path :department="'프로젝트'" :project="'New Project'" />
        <h1 id="myproject-edit-title">New Project</h1>
        <div id="myproject-edit-container">
            <div id="myproject-edit-selected-div">
                <h3 id="myproject-edit-selected-title">선택된 프로젝트</h3>
                <span class="myproject-edit-selected-info"
                    ><span class="material-icons"> info </span>프로젝트는 한
                    번에 3개까지 선택이 가능합니다.</span
                >
                <repository-card
                    v-for="(repository, index) in selectedRepositoryList"
                    :key="`${index}_repositoryList`"
                    :repository="repository"
                    :selected="true"
                    @selectedRepoClick="SelectedRepoClick"
                ></repository-card>
                <button
                    id="myproject-edit-selected-button"
                    @click="GoVerify"
                    :disabled="selectedRepositoryList.length == 0"
                >
                    검증하기
                </button>
            </div>
            <div id="myproject-edit-tab-div">
                <tab id="myproject-edit-tab" :list="tabList" />

                <router-view
                    id="myproject-edit-routerview"
                    :selectedRepositoryList="selectedRepositoryList"
                    @addRepoClick="AddRepoClick"
                    @clearRepoList="ClearRepoList"
                ></router-view>
            </div>
        </div>
    </div>
</template>
<script>
import "@/assets/css/MyProject/Edit/MyProjectEdit.css";
import gitLabApi from "@/api/gitlab.js";
import { mapGetters } from "vuex";
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
    name: "MyProjectEdit",
    components: { MyProjectPath, Tab, RepositoryCard },
    data() {
        return {
            path: this.$route.path,
            tabList: [
                {
                    name: "GitLab",
                    path: "/project/main/newproject/gitlab",
                },
                {
                    name: "GitHub",
                    path: "/project/main/newproject/github",
                },
            ],
            selectedRepositoryList: [],
        };
    },
    created() {
        this.GetSelectedRepositories();
    },
    watch: {
        $route: function () {
            //형상관리 툴 변경시 선택된 프로젝트 초기화
            this.ClearRepoList();
        },
    },
    methods: {
        GetSelectedRepositories: function () {
            this.selectedRepositoryList = [];
        },
        SelectedRepoClick: function (id) {
            // 선택된 레포지토리 클릭 => selectedList에서 삭제
            let index = this.selectedRepositoryList.findIndex(
                (i) => i.id == id
            );
            this.selectedRepositoryList.splice(index, 1);
        },
        AddRepoClick: function (repo) {
            //레포지토리 클릭 => selectedList에 추가
            if (this.selectedRepositoryList.length < 3) {
                this.selectedRepositoryList.push(repo);
            }
        },
        GoVerify: function () {
            gitLabApi
                .verifyGitLabProjects(
                    this.getGitLabId,
                    this.selectedRepositoryList
                )
                .then(() => {
                    alert("검증을 시작합니다.");
                    this.$router.push("/project/main/status");
                });
        },
        ClearRepoList: function () {
            this.selectedRepositoryList = [];
        },
    },
    computed: {
        ...mapGetters(["getGitLabId"]),
    },
};
</script>
