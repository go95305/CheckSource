<template>
  <div class="main-overview">
    <!-- 프로젝트 -->
    <div class="overviewCard">
      <div class="overviewCard-icon overviewCard-icon--photo">
        <span class="material-icons"> description </span>
      </div>
      <div class="overviewCard-description">
        <h3 class="overviewCard-title text-light">
          <strong>{{ status.project }}</strong>
        </h3>
        <p class="overviewCard-subtitle">프로젝트</p>
      </div>
      <div class="tooltip-content-left">
        <p class="tooltip-title">{{ msg.project }}</p>
      </div>
    </div>

    <!-- 오픈소스 -->
    <div class="overviewCard">
      <div class="overviewCard-icon overviewCard-icon--calendar">
        <span class="material-icons"> view_list </span>
      </div>

      <div class="overviewCard-description">
        <h3 class="overviewCard-title text-light">
          <strong>{{ status.opensource }}</strong>
        </h3>
        <p class="overviewCard-subtitle">오픈소스</p>
      </div>
      <div class="tooltip-content">
        <p class="tooltip-title">{{ msg.opensource }}</p>
      </div>
    </div>

    <!-- 라이선스 -->
    <div class="overviewCard">
      <div class="overviewCard-icon overviewCard-icon--document">
        <span class="material-icons"> verified </span>
      </div>
      <div class="overviewCard-description">
        <h3 class="overviewCard-title text-light">
          <strong>{{ status.license }}</strong>
        </h3>
        <p class="overviewCard-subtitle">라이선스</p>
      </div>
      <div class="tooltip-content">
        <p class="tooltip-title">{{ msg.license }}</p>
      </div>
    </div>

    <!-- 경고 -->
    <div class="overviewCard">
      <div class="overviewCard-icon overviewCard-icon--mail">
        <span class="material-icons"> report_problem </span>
      </div>
      <div class="overviewCard-description">
        <h3 class="overviewCard-title text-light">
          <strong>{{ status.warning }}</strong>
        </h3>
        <p class="overviewCard-subtitle">주의</p>
      </div>
      <div class="tooltip-content-right">
        <p class="tooltip-title">{{ msg.warning }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    status: Object,
  },
  data() {
    return {
      msg: {
        project: '검증된 프로젝트 수 입니다.',
        opensource: '검증된 프로젝트의 오픈소스 종류입니다.',
        license: '검증된 프로젝트의 라이선스의 종류입니다.',
        warning: '사용 시 주의해야하는 라이선스 개수입니다.',
      },
    };
  },
  name: 'DashBoardOverview',
};
</script>

<style
  lang="scss"
  scoped
  src="../../assets/css/DashBoard/DashBoard.scss"
></style>
