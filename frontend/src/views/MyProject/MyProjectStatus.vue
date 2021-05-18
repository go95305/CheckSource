<template>
  <!-- /project/main/result/status -->
  <div>
    <my-project-path :department="'내 부서'" />
    <h1 id="my-project-status-title">통계</h1>
    <div class="status-header">
      <div class="status-overview">
        <status-overview :status="status"></status-overview>
      </div>
    </div>

    <div class="status-body">
      <div class="card status-graph">
        <div class="card__header">
          <div class="card__header-title">오픈소스 <strong>TOP5</strong></div>
        </div>
        <top-five-graph :labels="labels" :dataList="dataList"> </top-five-graph>
      </div>

      <!-- warning -->
      <div class="card status-table-layout">
        <div class="card__header">
          <div class="card__header-title">
            <strong>라이선스 의무 warning</strong>
          </div>
        </div>
        <div id="chartdiv">
          <div class="status-table-warning">
            <div class="d-tbl-header">
              <table
                class="status-table"
                cellpadding="0"
                cellspacing="0"
                border="0"
              >
                <caption>
                  [코드공개(All) / 고지의무]
                </caption>
                <thead class="status-thead-css">
                  <tr>
                    <th scope="col">저장소</th>
                    <th scope="col">부서</th>
                    <th scope="col">프로젝트</th>
                    <th scope="col">오픈소스</th>
                    <th scope="col">라이선스</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div class="s-tbl-content">
              <table
                class="status-table"
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

    <!--
    <div class="status-body">
      <div class="status-graph">
        <top-five-graph :labels="labels" :dataList="dataList"> </top-five-graph>
      </div>

      <div class="status-table-layout">
        <div class="dashboard-table-warning">
          <div class="d-tbl-header">
            <table
              class="status-table"
              cellpadding="0"
              cellspacing="0"
              border="0"
            >
              <caption>
                [코드공개(All) / 고지의무]
              </caption>
              <thead class="status-thead-css">
                <tr>
                  <th scope="col">저장소</th>
                  <th scope="col">부서</th>
                  <th scope="col">프로젝트</th>
                  <th scope="col">오픈소스</th>
                  <th scope="col">라이선스</th>
                </tr>
              </thead>
            </table>
          </div>
          <div class="s-tbl-content">
            <table
              class="status-table"
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
    -->
  </div>
</template>
<script>
import MyProjectPath from '../../components/MyProject/MyProjectPath.vue';
import TopFiveGraph from '@/components/DashBoard/TopFiveGraph.vue';
import StatusOverview from '@/components/MyProject/StatusOverview.vue';
import { mapGetters } from 'vuex';
import dashboardApi from '@/api/dashboard.js';

export default {
  name: 'MyProjectSummary',
  components: {
    MyProjectPath,
    TopFiveGraph,
    StatusOverview,
  },
  data() {
    return {
      projectId: 'projectID입니다.',
      status: {
        project: '',
        opensource: '',
        license: '',
        warning: '',
      },
      // labels: ['Apache-2.0', 'Ruby', 'MIT', 'JSON', 'GLEW'],
      // dataList: [6, 5, 11, 13, 3],
      labels: [],
      dataList: [],
      departId: '',
    };
  },
  computed: {
    ...mapGetters(['getDepartment']),
  },
  created() {
    this.getDepartmentStatus();
    this.getDepartmentGraph();
  },
  methods: {
    getDepartmentGraph: function () {
      this.departId = this.getDepartment;
      dashboardApi
        .readTopFiveDepart(this.departId)
        .then((response) => {
          this.SetTopFive(response.data);
        })
        .catch();
    },
    SetTopFive(list) {
      let labels = [];
      let values = [];
      console.log(list);
      for (let item of list) {
        labels.push(item.name);
        values.push(item.cnt);
      }
      this.labels = labels;
      this.dataList = values;
    },
    getDepartmentStatus: function () {
      this.departId = this.getDepartment;
      dashboardApi
        .readDepartmentStatus(this.departId)
        .then((response) => {
          this.status.project = response.data.projectCnt;
          this.status.opensource = response.data.opensourceCnt;
          this.status.license = response.data.licenseCnt;
          this.status.warning = response.data.warningCnt;
        })
        .catch();
    },
    GoProjectResult: function () {
      this.$router.push({
        path: '/project/result/summary',
        query: { projectId: this.projectId },
      });
    },
  },
};
</script>

<style
  lang="scss"
  scoped
  src="@/assets/css/MyProject/MyProjectStatus.scss"
></style>
