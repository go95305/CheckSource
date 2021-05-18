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
        <license-warning-table
          :departId="warningDepartId"
        ></license-warning-table>
      </div>
    </div>
  </div>
</template>
<script>
import MyProjectPath from '../../components/MyProject/MyProjectPath.vue';
import TopFiveGraph from '@/components/DashBoard/TopFiveGraph.vue';
import StatusOverview from '@/components/MyProject/StatusOverview.vue';
import { mapGetters } from 'vuex';
import dashboardApi from '@/api/dashboard.js';
import LicenseWarningTable from '@/components/DashBoard/LicenseWarningTable.vue';

export default {
  name: 'MyProjectSummary',
  components: {
    MyProjectPath,
    TopFiveGraph,
    StatusOverview,
    LicenseWarningTable,
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
      warningDepartId: '',
    };
  },
  computed: {
    ...mapGetters(['getDepartment']),
  },
  created() {
    this.getDepartmentStatus();
    this.getDepartmentGraph();
    this.SetDepartId();
  },
  methods: {
    SetDepartId: function () {
      this.warningDepartId = this.getDepartment;
      console.log(this.warningDepartId);
    },
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
