<template>
  <div class="summary-container">
    <!-- 컴포넌트 -->
    <div class="sum-container">
      <div class="summary-card">
        <div class="img-container">
          <span class="su-verifycard-difficulty">{{ analyOpensourceCnt }}</span>
          <span class="su-verifycard-skill">검증된 오픈소스</span>
        </div>
        <div class="card-content">
          <h2 class="hh2">OPENSOURCE</h2>
          <span class="font_red"
            >연결이 필요한 오픈소스 : {{ unmappingOpensourceCnt }}개</span
          >
          <p class="excerpt">
            연결되지 않은 오픈소스가 있습니다. '확인하기'를 눌러 mapping을
            진행해 주세요.
          </p>
          <button class="sum-btn" @click="GoOpensource()">확인하기</button>
        </div>
      </div>
    </div>
    <!-- 컴포넌트 end  -->

    <!-- 라이선스 -->
    <div class="sum-container">
      <div class="summary-card">
        <div class="img-container">
          <span class="su-verifycard-difficulty">{{ analyLicenseCnt }}</span>
          <span class="su-verifycard-skill"> 검증된 라이선스 </span>
        </div>
        <div class="card-content">
          <h2 class="hh2">License</h2>
          <span class="font_red"
            >확인이 필요한 라이선스 : {{ requireCheckingLicenseCnt }}개</span
          >
          <p class="excerpt">
            공개해야하는 라이선스가 있습니다. License 의무사항을 확인하고,
            오픈소스 사용방법을 선택해 주세요.
          </p>
          <button class="sum-btn" @click="GoLicense()">확인하기</button>
        </div>
      </div>
    </div>
    <!-- 라이선스 end  -->
  </div>
</template>
<script>
import '@/assets/css/MyProject/Summary.scss';
import verifyApi from '@/api/verify.js';

export default {
  data() {
    return {
      analyLicenseCnt: '',
      analyOpensourceCnt: '',
      requireCheckingLicenseCnt: '',
      unmappingOpensourceCnt: '',
      projectId: this.$route.query.projectId,
      gitType: this.$route.query.gitType,
    };
  },
  created() {
    this.getSummary();
  },
  methods: {
    GoOpensource: function () {
      this.$router.push({
        name: 'OpenSourceList',
        query: { projectId: this.projectId },
      });
    },
    GoLicense: function () {
      this.$router.push({
        name: 'License',
        query: { projectId: this.projectId },
      });
    },

    getSummary: function () {
      verifyApi
        .readVerifiedSummary(this.gitType, this.projectId)
        .then((response) => {
          console.log(response);
          this.analyLicenseCnt = response.data.analyLicenseCnt;
          this.analyOpensourceCnt = response.data.analyOpensourceCnt;
          this.requireCheckingLicenseCnt =
            response.data.requireCheckingLicenseCnt;
          this.unmappingOpensourceCnt = response.data.unmappingOpensourceCnt;
          this.project = response.data;
        })
        .catch(() => {
          alert('요약정보 불러오기 실패');
        });
    },
  },
};
</script>
