<template>
  <div class="verifycard">
    <div v-if="loading" class="verifycard-loading">
      <Loading></Loading>
    </div>
    <!-- left -->
    <div class="verifycard-aside">
      <div class="verifycard-aside-detail">
        <span class="verifycard-difficulty">{{
          project.opensourceCnt ? project.opensourceCnt : 0
        }}</span
        ><br />
        <span class="verifycard-skill">오픈소스</span>
      </div>

      <div class="verifycard-detail">
        <span class="verifycard-difficulty">{{
          project.licenseCnt ? project.licenseCnt : 0
        }}</span
        ><br />
        <span class="verifycard-skill">라이선스</span>
      </div>
    </div>
    <!-- end of left -->

    <!-- right -->
    <div class="verifycard-main">
      <div v-if="isMyproject" class="button-holder" @click="DeleteProjectCheck">
        <button class="delete-btn">
          <span class="material-icons"> delete </span>
        </button>
      </div>
      <div class="verifycard-main-first">
        <p class="verifycard-excerpt">{{ getGitType }}</p>
        <h3 class="verifycard-title">{{ project.name }}</h3>
        <p>{{ project.branch }}</p>
      </div>

      <div class="verifycard-main-second">
        <div class="verifycard-skill-s">{{ getStatus }}</div>
      </div>

      <div class="verifycard-main-third">
        <div class="verifycard-excerpt">Last Scanned</div>
        <div class="verifycard-excerpt-s">
          {{ project.username }} / {{ project.date }}
        </div>
      </div>

      <!-- down -->
      <div class="verifycard-meta">
        <div class="verifycard-btn" @click="Reverify">
          <span class="material-icons"> replay </span>
          <span class="vc-btn-detail">재검증</span>
        </div>

        <div class="verifycard-btn" @click="GoReport">
          <span class="material-icons"> text_snippet </span>
          <span class="vc-btn-detail">보고서</span>
        </div>
      </div>
      <!-- end of down -->
    </div>
    <!-- end of right -->
  </div>
</template>

<script>
import gitLabApi from '@/api/git.js';
import swal from '@/api/alert.js';
import dayjs from 'dayjs';
import Loading from '../Loading/Loading';

export default {
  name: 'VerifyCard',
  components: { Loading },
  data() {
    return {
      loading: false,
    };
  },
  props: {
    project: Object,
    isMyproject: Boolean,
  },
  created() {
    console.log(this.project);
    this.changeTime();
  },
  computed: {
    getGitType: function () {
      if (this.project.gitType <= 2) {
        return 'GitLab';
      }
      return 'GitHub';
    },
    getStatus: function () {
      if (this.project.status == true) {
        return '검증완료';
      } else {
        return '검증대기중';
      }
    },
  },
  methods: {
    changeTime: function () {
      this.project.date = dayjs().format('YYYY-MM-DD HH:mm:ss');
    },
    GoReport: function () {
      this.$emit('goReport', this.project.gitType, this.project.projectId);
    },
    Reverify: function () {
      this.GetRepoBranch();
    },
    GetRepoBranch: function () {
      //브랜치 목록 가져오기
      gitLabApi
        .readProjectBranches(this.project.gitType, this.project.projectId)
        .then((response) => {
          let branchOption = {};
          for (let branch of response.data) {
            branchOption[branch.name] = branch.name;
          }
          this.SelectBranch(branchOption);
        })
        .catch(() => {
          alert('프로젝트 브랜치 목록을 불러오지 못했습니다.');
        });
    },
    SelectBranch: function (branchOption) {
      //브랜치 선택
      swal
        .selectBranch('Branch 선택', 'Branch를 선택하세요.', branchOption)
        .then((result) => {
          if (result.value) {
            console.log(result.value);
            let repoList = [
              {
                branch: result.value,
                id: this.project.projectId,
                name: this.project.name,
                verified: this.project.status,
                web_url: this.project.webUrl,
              },
            ];
            console.log(repoList[0].web_url);
            this.ReverifyGitLab(repoList);
          }
        });
    },
    ReverifyGitLab: function (repoList) {
      //재검증 시작
      this.loading = true;
      gitLabApi
        .verifyGitLabProjects(this.project.gitType, repoList)
        .then(() => {
          alert('검증이 완료되었습니다.');
          this.loading = false;
          window.location.reload();
        })
        .catch(() => {
          alert('검증에 실패했습니다.');
          this.loading = false;
        });
    },
    DeleteProjectCheck: function () {
      swal
        .inputtext(
          '프로젝트 삭제',
          '프로젝트 삭제를 위해 프로젝트명을 기입해주세요.'
        )
        .then((result) => {
          if (result.value == this.project.name) {
            this.DeleteProject();
          }
        });
    },
    DeleteProject: function () {
      //프로젝트 삭제
      this.loading = true;
      gitLabApi
        .deleteProject(this.project.projectId, this.project.gitType)
        .then(() => {
          // alert("프로젝트가 삭제되었습니다.");
          swal.success('프로젝트가 삭제되었습니다.');
          this.loading = false;
          window.location.reload();
        })
        .catch(() => {
          alert('삭제에 실패했습니다.');
          this.loading = false;
        });
    },
  },
};
</script>

<style lang="scss" scoped src="@/assets/css/DashBoard/VerifyCard.scss"></style>
