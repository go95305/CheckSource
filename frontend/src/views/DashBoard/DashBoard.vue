<template>
  <div>
    <!-- <h1>
      대시보드대시보드대시보드대시보드대시보드대시보드대시보드대시보드대시보드대시보드
    </h1> -->

    <div class="main__cards">
      <!-- 저장소(부서) 별 정보 -->
      <div class="card">
        <div class="card__header">
          <div class="card__header-title">
            <strong>저장소(부서) 별 정보</strong>
          </div>
        </div>
        <!-- 총 갯수 -->
        <div class="card__main">
          <div class="main-overview">
            <div class="overviewCard">
              <div class="overviewCard-icon overviewCard-icon--document">
                <i class="far fa-file-alt"></i>
              </div>
              <div class="overviewCard-description">
                <h3 class="overviewCard-title text-light">
                  <strong>26</strong>
                </h3>
                <p class="overviewCard-subtitle">Projects</p>
              </div>
            </div>
            <div class="overviewCard">
              <div class="overviewCard-icon overviewCard-icon--calendar">
                <i class="far fa-envelope"></i>
              </div>

              <div class="overviewCard-description">
                <h3 class="overviewCard-title text-light">
                  <strong>12</strong>
                </h3>
                <p class="overviewCard-subtitle">OpenSource</p>
              </div>
            </div>
            <div class="overviewCard">
              <div class="overviewCard-icon overviewCard-icon--mail">
                <i class="far fa-envelope"></i>
              </div>
              <div class="overviewCard-description">
                <h3 class="overviewCard-title text-light">
                  <strong>11</strong>
                </h3>
                <p class="overviewCard-subtitle">License</p>
              </div>
            </div>
            <div class="overviewCard">
              <div class="overviewCard-icon overviewCard-icon--photo">
                <i class="far fa-file-image"></i>
              </div>
              <div class="overviewCard-description">
                <h3 class="overviewCard-title text-light">
                  <strong>14</strong>
                </h3>
                <p class="overviewCard-subtitle">Warning</p>
              </div>
            </div>
          </div>
          <!-- 총 갯수 end -->
          <div class="dashboard-table">
            <div class="d-tbl-header">
              <table
                class="dash-table"
                cellpadding="0"
                cellspacing="0"
                border="0"
              >
                <thead class="dash-thead-css">
                  <tr>
                    <th scope="col">저장소</th>
                    <th scope="col">부서</th>
                    <th scope="col">프로젝트</th>
                    <th scope="col">오픈소스</th>
                    <th scope="col">라이선스</th>
                    <th scope="col">Warning</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div class="d-tbl-content">
              <table
                class="dash-table"
                cellpadding="0"
                cellspacing="0"
                border="0"
              >
                <tbody>
                  <tr>
                    <td>gitlab#1</td>
                    <td>ICT운영부</td>
                    <td>21</td>
                    <td>10</td>
                    <td>4</td>
                    <td>11</td>
                  </tr>
                  <tr>
                    <td>gitlab#1</td>
                    <td>ICT운영부</td>
                    <td>21</td>
                    <td>10</td>
                    <td>4</td>
                    <td>11</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- table end -->
          <i class="material-icons">face</i>
          <VerifyCard />
          <div class="project-title">
            <vueper-slides fractions progress>
              <vueper-slide v-for="i in 10" :key="i" />
              <template v-slot:content> hi~ </template>
            </vueper-slides>
          </div>
        </div>
      </div>

      <!-- top5 -->
      <div class="card">
        <div class="card__header">
          <div class="card__header-title text-light">
            오픈소스 <strong>TOP5</strong>
          </div>
        </div>
        <TopFiveGraph />
      </div>

      <!-- warning -->
      <div class="card card--finance">
        <div class="card__header">
          <div class="card__header-title text-light">
            <strong>라이선스 의무 warning</strong>
          </div>
        </div>
        <div id="chartdiv">
          <div class="dashboard-table-warning">
            <div class="d-tbl-header">
              <table
                class="dash-table"
                cellpadding="0"
                cellspacing="0"
                border="0"
              >
                <caption>
                  [코드공개(All) / 고지의무]
                </caption>
                <thead class="dash-thead-css">
                  <tr>
                    <th scope="col">Repository</th>
                    <th scope="col">Project</th>
                    <th scope="col">OpenSource</th>
                    <th scope="col">License</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div class="d-tbl-content">
              <table
                class="dash-table"
                cellpadding="0"
                cellspacing="0"
                border="0"
              >
                <tbody>
                  <tr>
                    <td>gitlab#1</td>
                    <td>ASC project</td>
                    <td>Elasticsearch Extra Plugins</td>
                    <td>GPL</td>
                  </tr>
                  <tr>
                    <td>gitlab#3</td>
                    <td>MinorLoan</td>
                    <td>H2</td>
                    <td>GPL</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/DashBoard/DashBoard.scss';
import TopFiveGraph from '@/components/DashBoard/TopFiveGraph.vue';
import VerifyCard from '@/components/DashBoard/VerifyCard.vue';
import { VueperSlides, VueperSlide } from 'vueperslides';
import 'vueperslides/dist/vueperslides.css';

export default {
  name: 'DashBoard',
  components: {
    TopFiveGraph,
    VerifyCard,
    VueperSlides,
    VueperSlide,
  },
  data() {
    return {};
  },
};
</script>
