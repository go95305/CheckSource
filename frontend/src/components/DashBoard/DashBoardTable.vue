<template>
  <div class="dashboard-table">
    <div class="d-tbl-header">
      <table class="dash-table" cellpadding="0" cellspacing="0" border="0">
        <thead class="dash-thead-css">
          <tr>
            <th scope="col">저장소</th>
            <th scope="col">부서</th>
            <th scope="col">프로젝트</th>
            <th scope="col">오픈소스</th>
            <th scope="col">라이선스</th>
            <th scope="col">주의</th>
          </tr>
        </thead>
      </table>
    </div>
    <div class="d-tbl-content">
      <table class="dash-table" cellpadding="0" cellspacing="0" border="0">
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
</template>

<script>
import '@/assets/css/DashBoard/DashBoardTable.scss';

export default {
  name: 'DashBoardTable',
  props: {
    list: Array,
  },
};
</script>
