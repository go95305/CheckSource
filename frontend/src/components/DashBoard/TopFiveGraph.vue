<template>
  <div class="top-five-chart">
    <canvas id="myChart"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';

export default {
  props: {
    labels: Array,
    dataList: Array,
  },
  methods: {
    createChart() {
      var ctx = document.getElementById('myChart');

      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: '# of Votes',
              data: this.dataList,
              backgroundColor: [
                'rgba(255, 99, 132, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 206, 86, 0.5)',
                'rgba(75, 192, 192, 0.5)',
                'rgba(153, 102, 255, 0.5)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)',
              ],
              borderWidth: 1,
            },
          ],
        },
        options: {
          legend: {
            align: 'middle',
            labels: {
              font: {
                size: 16,
              },
            },
          },
        },
      });
    },
  },
  mounted() {
    this.createChart();
  },
};
</script>

<style scoped>
.top-five-chart {
  margin: auto;
  width: 60%;
  height: 60%;
  padding: 15px;
}
</style>
