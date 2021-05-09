<template>
  <div>
    <my-project-path :department="'프로젝트'" />
    <h1 id="my-project-status-title">Status</h1>
    <div class="status-header">
      <div class="status-overview">
        <dash-board-overview :status="status"> </dash-board-overview>
      </div>
    </div>

    <div class="status-body">
      <div class="status-graph">
        <top-five-graph :labels="labels" :dataList="dataList"> </top-five-graph>
      </div>

      <div class="status-table">
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
</template>
<script>
import MyProjectPath from '../../components/MyProject/MyProjectPath.vue';
import DashBoardOverview from '@/components/DashBoard/DashBoardOverview.vue';
import TopFiveGraph from '@/components/DashBoard/TopFiveGraph.vue';
import '@/assets/css/MyProject/MyProjectStatus.scss';

export default {
  name: 'MyProjectSummary',
  components: { MyProjectPath, DashBoardOverview, TopFiveGraph },
  data() {
    return {
      projectId: 'projectID입니다.',
      status: { project: '27', opensource: '17', license: '5', warning: '3' },
      labels: ['Apache-2.0', 'Ruby', 'MIT', 'JSON', 'GLEW'],
      dataList: [6, 5, 11, 13, 3],
    };
  },
  methods: {
    GoProjectResult: function () {
      this.$router.push({
        path: '/project/result/summary',
        query: { projectId: this.projectId },
      });
    },
  },
};
</script>
