<template>
    <div class="myproject-edit">
        <DropDown
            class="myproject-dropdown"
            v-if="gitlabAccountList.length > 0"
            :orderList="gitlabAccountList"
            @orderItemChange="SetGitLabAccountValue"
        ></DropDown>
        <div v-if="gitlabAccountList.length == 0">연결된 계정이 없습니다.</div>
        <div v-else class="myproject-edit-gitlab">
            <loading v-if="loading"></loading>
            <repository-card
                v-for="(repository, index) in repositoryList"
                :key="`${index}_repositoryList`"
                :index="index"
                :repository="repository"
                :selected="false"
                :checked="IsSelected(repository.id)"
                @addRepoClick="AddRepoClick"
            ></repository-card>
        </div>
        <select-branch></select-branch>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import gitLabApi from "@/api/gitlab.js";
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
import Loading from "@/components/Loading/Loading.vue";
import DropDown from "../../components/DropDown/DropDown.vue";
import SelectBranch from "../../components/MyProject/SelectBranch.vue";
export default {
    name: "MyProjectEditGitLab",
    components: { DropDown, RepositoryCard, Loading, SelectBranch },
    data() {
        return {
            repositoryList: [],
            loading: false,
            gitlabAccountList: [],
            gitlabAccountValue: 0,
        };
    },
    props: {
        selectedRepositoryList: Array,
    },
    created() {
        this.GetGitLabAccountList();
        this.GetRepositories();
    },
    computed: {
        ...mapGetters(["getGitLabList"]),
    },
    watch: {
        gitlabAccountValue: function () {
            this.GetRepositories();
            this.$emit("clearRepoList");
        },
    },
    methods: {
        GetGitLabAccountList: function () {
            for (let i = 0; i < this.getGitLabList.length; ++i) {
                this.gitlabAccountList[i] =
                    this.getGitLabList[i].username +
                    "(" +
                    gitLabApi.getBaseUrl(this.getGitLabList[i].gitlabId - 1) +
                    ")";
            }
        },
        SetGitLabAccountValue: function (value) {
            this.gitlabAccountValue = value;
        },
        GetRepositories: function () {
            //레포지토리 얻어오기
            this.loading = true;
            this.repositoryList = [];
            gitLabApi
                .readGitLabProjects(
                    this.getGitLabList[this.gitlabAccountValue].gitlabId
                )
                .then((response) => {
                    this.loading = false;
                    if (response.data.accessflag) {
                        this.repositoryList = response.data.projectList;
                    } else {
                        alert(
                            "Gitlab 토큰 기한이 만료되었습니다.\n다시 연동해주세요."
                        );
                        this.$router.push("/mypage/scm/gitlab");
                    }
                })
                .catch(() => {
                    this.loading = false;
                    this.repositoryList = [];
                    alert("프로젝트 목록을 불러오지 못했습니다.");
                });
        },
        IsSelected: function (id) {
            //선택된 레포지토리인지 확인
            if (
                this.selectedRepositoryList.findIndex((i) => i.id == id) == -1
            ) {
                return false;
            }
            return true;
        },
        AddRepoClick: function (repo) {
            this.$emit("addRepoClick", repo);
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEditGitLab.css"></style>
