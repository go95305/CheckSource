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
        <p class="overviewCard-subtitle">Projects</p>
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
        <p class="overviewCard-subtitle">OpenSource</p>
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
        <p class="overviewCard-subtitle">License</p>
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
        <p class="overviewCard-subtitle">Warning</p>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/DashBoard/DashBoard.scss';

export default {
  props: {
    status: Object,
  },
  name: 'DashBoardOverview',
};
</script>
