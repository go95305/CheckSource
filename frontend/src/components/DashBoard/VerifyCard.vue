<template>
  <div class="verifycard">
    <!-- left -->
    <div class="verifycard-aside">
      <div class="verifycard-aside-detail">
        <span class="verifycard-difficulty">{{ project.opensourceCnt }}</span
        ><br />
        <span class="verifycard-skill">오픈소스</span>
      </div>

      <div class="verifycard-detail">
        <span class="verifycard-difficulty">{{ project.licenseCnt }}</span
        ><br />
        <span class="verifycard-skill">라이선스</span>
      </div>
    </div>
    <!-- end of left -->

    <!-- right -->
    <div class="verifycard-main">
      <div class="block" @click="deleteproject">
        <i class="hovicon effect-1 sub-a"
          ><span class="material-icons red500"> delete </span>
        </i>
      </div>
      <div class="verifycard-main-first">
        <p class="verifycard-excerpt">git</p>
        <h3 class="verifycard-title">{{ project.name }}</h3>
        <p>{{ project.branch }}</p>
      </div>

      <div class="verifycard-main-second">
        <div class="verifycard-skill-s">{{ project.status }}</div>
      </div>

      <div class="verifycard-main-third">
        <div class="verifycard-excerpt">Last Scanned</div>
        <div class="verifycard-excerpt-s">{{ project.username }}</div>
        <div class="verifycard-excerpt-s">{{ project.date }}</div>
      </div>

      <!-- down -->
      <div class="verifycard-meta">
        <div class="verifycard-btn">
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
import '@/assets/css/DashBoard/VerifyCard.scss';
import swal from '@/api/alert.js';

export default {
  name: 'VerifyCard',
  props: {
    project: Object,
  },
  created() {
    this.changeStatus();
  },
  methods: {
    changeStatus: function () {
      if (this.project.status == true) {
        this.project.status = '검증완료';
      } else {
        this.project.status = '검증대기중';
      }
    },
    GoReport: function () {
      this.$emit('goReport', this.project.gitType, this.project.projectId);
    },
    deleteproject: function () {
      //   swal.success('성공!');
      swal
        .inputtext(
          '프로젝트 삭제',
          '프로젝트 삭제를 위해 프로젝트명을 기입해주세요.'
        )
        .then((result) => {
          console.log(result);
        });
    },
  },
};
</script>
