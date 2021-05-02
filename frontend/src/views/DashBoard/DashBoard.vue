<template>
  <div>
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
          <!-- <DashBoardOverview /> -->
          <dash-board-overview :status="status"> </dash-board-overview>
          <DashBoardTable />

          <div id="slider">
            <transition-group
              tag="div"
              :name="transitionName"
              class="slides-group"
            >
              <div v-if="show" :key="current" class="slide">
                <VerifyCard />
              </div>
            </transition-group>
            <div
              class="dash-c-btn dash-c-btn-prev"
              aria-label="Previous slide"
              @click="slide(-1)"
            >
              &#10094;
            </div>
            <div
              class="dash-c-btn dash-c-btn-next"
              aria-label="Next slide"
              @click="slide(1)"
            >
              &#10095;
            </div>
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
        <div>
          <div class="dash-dropdown">
            <DropDown />
          </div>
        </div>
        <TopFiveGraph />
      </div>

      <!-- warning -->
      <div class="card">
        <div class="card__header">
          <div class="card__header-title text-light">
            <strong>라이선스 의무 warning</strong>
          </div>
        </div>
        <div id="chartdiv">
          <div class="dash-dropdown">
            <DropDown />
          </div>

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
                    <th scope="col">Department</th>
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
                    <td>ICT운영부</td>
                    <td>ASC project</td>
                    <td>Elasticsearch Extra Plugins</td>
                    <td>GPL</td>
                  </tr>
                  <tr>
                    <td>gitlab#3</td>
                    <td>ICT운영부</td>
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
import DropDown from '@/components/DropDown/DropDown.vue';
import DashBoardTable from '@/components/DashBoard/DashBoardTable.vue';
import DashBoardOverview from '@/components/DashBoard/DashBoardOverview.vue';
import 'vueperslides/dist/vueperslides.css';

export default {
  name: 'DashBoard',
  components: {
    TopFiveGraph,
    VerifyCard,
    DropDown,
    DashBoardTable,
    DashBoardOverview,
  },
  data() {
    return {
      current: 0,
      direction: 1,
      transitionName: 'fade',
      show: false,
      status: { project: '31', opensource: '29', license: '7', warning: '15' },
    };
  },
  methods: {
    slide(dir) {
      this.direction = dir;
      dir === 1
        ? (this.transitionName = 'slide-next')
        : (this.transitionName = 'slide-prev');
      var len = this.slides.length;
      this.current = (this.current + (dir % len) + len) % len;
    },
  },
  mounted() {
    this.show = true;
  },
};
</script>
