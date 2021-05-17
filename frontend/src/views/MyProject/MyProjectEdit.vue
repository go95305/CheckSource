<template>
  <!-- project/main/newproject -->
  <div>
    <my-project-path
      :department="'프로젝트'"
      :project="'새로 검증하기'"
      :rootPath="'/project/main/projects'"
    />
    <h1 id="myproject-edit-title">검증할 프로젝트 선택</h1>
    <div id="myproject-edit-container">
      <div id="myproject-edit-selected-div">
        <h3 id="myproject-edit-selected-title">선택된 프로젝트</h3>
        <span class="myproject-edit-selected-info"
          ><span class="material-icons"> info </span>프로젝트는 한 번에 3개까지
          선택이 가능합니다.</span
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
          :gitlabId="gitlabId"
          @addRepoClick="AddRepoClick"
          @changeAccountValue="ChangeAccountValue"
        ></router-view>
      </div>
    </div>
    <div v-if="fullLoading" class="loading">
      <Loading></Loading>
    </div>
  </div>
</template>
<script>
import "@/assets/css/MyProject/Edit/MyProjectEdit.css";
import Loading from "@/components/Loading/Loading.vue";
import gitApi from "@/api/git.js";
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
  name: "MyProjectEdit",
  components: { MyProjectPath, Tab, RepositoryCard, Loading },
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
      gitlabId: 1,
      fullLoading: false,
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
      let index = this.selectedRepositoryList.findIndex((i) => i.id == id);
      this.selectedRepositoryList.splice(index, 1);
    },
    AddRepoClick: function (repo) {
      //레포지토리 클릭 => selectedList에 추가
      if (this.selectedRepositoryList.length < 3) {
        this.selectedRepositoryList.push(repo);
      }
    },
    GoVerify: function () {
      //검증 시작
      this.fullLoading = true;
      gitApi
        .verifyGitLabProjects(this.gitlabId, this.selectedRepositoryList)
        .then(() => {
          alert("검증이 완료되었습니다.");
          this.fullLoading = false;
          this.$router.push("/project/main/projects");
        })
        .catch(() => {
          alert("검증에 실패했습니다.");
          this.fullLoading = false;
        });
    },
    ClearRepoList: function () {
      //선택된 프로젝트 목록 초기화
      this.ChangeAccountValue(1);
    },
    ChangeAccountValue: function (gitlabId) {
      //선택된 프로젝트 목록 초기화
      this.gitlabId = gitlabId;
      this.selectedRepositoryList = [];
    },
  },
};
</script>
