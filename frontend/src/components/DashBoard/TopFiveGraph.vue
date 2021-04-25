<template>
  <div class="top-five-chart">
    <canvas id="myChart"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';

export default {
  methods: {
    createChart() {
      var ctx = document.getElementById('myChart');

      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple'],
          datasets: [
            {
              label: '# of Votes',
              data: [12, 19, 15, 21, 2],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
              ],
              borderWidth: 1,
            },
          ],
        },
        // options: {
        //   legend: {
        //     align: 'middle',
        //   },
        // },
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
