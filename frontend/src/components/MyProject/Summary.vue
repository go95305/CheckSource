<template>
  <div class="summary-container">
    <!-- 컴포넌트 -->
    <div class="sum-container">
      <div class="summary-card">
        <div class="img-container">
          <span class="su-verifycard-difficulty">12</span>
          <span class="su-verifycard-skill">Components</span>
        </div>
        <div class="card-content">
          <h2 class="hh2">COMPONENT</h2>
          <span class="font_red"
            >확인이 필요한 컴포넌트 : {{ unmap_component }}개</span
          >
          <p class="excerpt">
            Component mapping이 완료되지 않았습니다. '확인하기'를 눌러 mapping을
            진행해 주세요.
          </p>
          <p class="sum-btn">확인하기</p>
          <!-- <button class="sum-btn">확인하기</button> -->
        </div>
      </div>
    </div>
    <!-- 컴포넌트 end  -->

    <!-- 라이선스 -->
    <div class="sum-container">
      <div class="summary-card">
        <div class="img-container">
          <span class="su-verifycard-difficulty">12</span>
          <span class="su-verifycard-skill">License</span>
        </div>
        <div class="card-content">
          <h2 class="hh2">License</h2>
          <span class="font_red"
            >확인이 필요한 라이선스 : {{ unmap_component }}개</span
          >
          <p class="excerpt">
            확인이 필요한 License 가 있습니다. License 의무사항을 확인하고,
            오픈소스 사용방법을 선택해 주세요.
          </p>
          <p class="sum-btn">확인하기</p>
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
      unmap_component: 15,
    };
  },
};
</script>
