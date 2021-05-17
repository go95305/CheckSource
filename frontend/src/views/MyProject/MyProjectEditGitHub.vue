<template>
  <div class="myproject-edit">
    <MyProjectEditNoAccount
      v-if="repositoryList.length == 0"
      :link="scmLink"
    ></MyProjectEditNoAccount>
    <div v-else class="myproject-edit-gitlab">
      <loading v-if="loading"></loading>
      <repository-card
        v-for="(repository, index) in repositoryList"
        :key="`${index}_repositoryList`"
        :index="index"
        :repository="repository"
        :selected="false"
        :gitType="gitType"
        :checked="IsSelected(repository.id)"
        @addRepoClick="AddRepoClick"
      ></repository-card>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import gitApi from "@/api/git.js";
import swal from "@/api/alert.js";
import RepositoryCard from "@/components/MyProject/RepositoryCard.vue";
import Loading from "@/components/Loading/Loading.vue";
import MyProjectEditNoAccount from "@/components/MyProject/MyProjectEditNoAccount.vue";

export default {
  name: "MyProjectEditGitHub",
  components: {
    RepositoryCard,
    Loading,
    MyProjectEditNoAccount,
  },
  data() {
    return {
      repositoryList: [],
      loading: false,
      githubAccountList: [],
      githubAccountValue: 0,
      scmLink: "/mypage/scm/github",
      branchList: [],
      selectRepo: null,
    };
  },
  props: {
    selectedRepositoryList: Array,
    gitType: Number,
  },
  created() {
    this.GetRepositories();
  },
  computed: {
    ...mapGetters(["getGitHubList", "getGithubUsername"]),
  },
  methods: {
    GetRepositories: function () {
      //레포지토리 얻어오기
      this.loading = true;
      this.repositoryList = [];
      gitApi
        .readGitHubProjects(
          this.getGitHubList[this.githubAccountValue].githubId
        )
        .then((response) => {
          this.loading = false;
          if (response.data.accessflag) {
            this.repositoryList = response.data.projectList;
          } else {
            alert("Github 토큰 기한이 만료되었습니다.\n다시 연동해주세요.");
            this.$router.push("/mypage/scm/github");
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
      if (this.selectedRepositoryList.findIndex((i) => i.id == id) == -1) {
        return false;
      }
      return true;
    },
    AddRepoClick: function (repo) {
      //레포지토리 선택
      this.selectRepo = repo;
      this.GetRepoBranch();
    },
    GetRepoBranch: function () {
      //브랜치 목록 가져오기
      gitApi
        .readHubProjectBranches(
          this.getGitHubList[this.githubAccountValue].githubId,
          this.selectRepo.name,
          this.getGithubUsername
        )
        .then((response) => {
          let branchOption = {};
          for (let branch of response.data) {
            branchOption[branch.commit.sha] = branch.name;
          }
          this.SelectBranch(branchOption);
        })
        .catch(() => {
          alert("프로젝트 브랜치 목록을 불러오지 못했습니다.");
        });
    },
    SelectBranch: function (branchOption) {
      //브랜치 선택
      swal
        .selectBranch("Branch 선택", "Branch를 선택하세요.", branchOption)
        .then((result) => {
          if (result.value) {
            let branchRepo = Object.assign({}, this.selectRepo);
            branchRepo.sha = result.value;
            branchRepo.branch = branchOption[result.value];
            console.log(result.value);
            this.$emit("addRepoClick", branchRepo);
          }
        });
    },
  },
};
</script>
