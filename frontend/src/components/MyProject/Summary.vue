<template>
  <div class="summary-container">
    <!-- 컴포넌트 -->
    <div class="sum-container">
      <div class="summary-card">
        <div class="img-container">
          <span class="su-verifycard-difficulty">{{ total_component }}</span>
          <span class="su-verifycard-skill">검증된 오픈소스</span>
        </div>
        <div class="card-content">
          <h2 class="hh2">OPENSOURCE</h2>
          <!-- <span class="font_black"
            >연결된 오픈소스 : {{ mapped_component }}개</span
          ><br /> -->
          <span class="font_red"
            >연결이 필요한 오픈소스 : {{ unmap_component }}개</span
          >
          <p class="excerpt">
            연결되지 않은 오픈소스가 있습니다. '확인하기'를 눌러 mapping을
            진행해 주세요.
          </p>
          <p class="sum-btn" @click="GoOpensource()">확인하기</p>
          <!-- <button class="sum-btn">확인하기</button> -->
        </div>
      </div>
    </div>
    <!-- 컴포넌트 end  -->

    <!-- 라이선스 -->
    <div class="sum-container">
      <div class="summary-card">
        <div class="img-container">
          <span class="su-verifycard-difficulty">{{ mapped_license }}</span>
          <span class="su-verifycard-skill"> 검증된 라이선스 </span>
        </div>
        <div class="card-content">
          <h2 class="hh2">License</h2>
          <span class="font_red"
            >확인이 필요한 라이선스 : {{ unmap_license }}개</span
          >
          <p class="excerpt">
            공개해야하는 라이선스가 있습니다. License 의무사항을 확인하고,
            오픈소스 사용방법을 선택해 주세요.
          </p>
          <p class="sum-btn" @click="GoLicense()">확인하기</p>
          <!-- <button class="sum-btn">확인하기</button> -->
        </div>
      </div>
    </div>
    <!-- 라이선스 end  -->
  </div>
</template>
<script>
import '@/assets/css/MyProject/Summary.scss';
export default {
  data() {
    return {
      total_component: 26,
      mapped_component: 11,
      mapped_license: 28,
      unmap_component: 15,
      unmap_license: 12,
      projectId: this.$route.query.projectId,
    };
  },
  methods: {
    GoOpensource: function () {
      this.$router.push({
        name: 'ResultOpenSource',
        query: { projectId: this.projectId },
      });
    },
    GoLicense: function () {
      this.$router.push({
        name: 'ResultLicense',
        query: { projectId: this.projectId },
      });
    },
  },
};
</script>
