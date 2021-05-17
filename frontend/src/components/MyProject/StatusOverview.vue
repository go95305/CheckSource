<template>
  <div class="depart-status-container">
    <div class="panel project">
      <div class="status-title">
        <span>{{ status.project }}</span
        >프로젝트
      </div>
      <div class="tooltip-content">
        <p>하잉 오늘은 화요일이예요</p>
      </div>
    </div>
    <div class="panel opensource">
      <div class="status-title">
        <span>{{ status.opensource }}</span
        >오픈소스
      </div>
    </div>
    <div class="panel license">
      <div class="status-title">
        <span>{{ status.license }}</span
        >라이선스
      </div>
    </div>
    <div class="panel warning">
      <div class="status-title">
        <span>{{ status.warning }}</span
        >주의
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'StatusOverview',
  data() {
    return {};
  },
  props: {
    status: Object,
  },
};
</script>
<style
  lang="scss"
  scoped
  src="../../assets/css/MyProject/StatusOverview.scss"
></style>
