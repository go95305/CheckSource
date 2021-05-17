<template>
  <div class="top-five-chart">
    <canvas id="myChart"></canvas>
    <!-- 
                    'rgba(48, 138, 197, 0.4)',
                'rgba(35, 154, 110, 0.4)',
                'rgba(223, 107, 25, 0.4)',
                'rgba(206, 69, 99, 0.4)',
                'rgba(113, 116, 122, 0.4)',


                'rgba(255, 99, 132, 0.4)',
                'rgba(54, 162, 235, 0.4)',
                'rgba(255, 206, 86, 0.4)',
                'rgba(75, 192, 192, 0.4)',
                'rgba(153, 102, 255, 0.4)', 
                -->
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
                'rgba(48, 138, 197, 0.6)',
                'rgba(35, 154, 110, 0.6)',
                'rgba(223, 107, 25, 0.6)',
                'rgba(206, 69, 99, 0.6)',
                'rgba(113, 116, 122, 0.6)',
              ],
              borderColor: [
                'rgba(48, 138, 197, 0.7)',
                'rgba(35, 154, 110, 0.7)',
                'rgba(223, 107, 25, 0.7)',
                'rgba(206, 69, 99, 0.7)',
                'rgba(113, 116, 122, 0.7)',
              ],
              borderWidth: 1,
            },
          ],
        },
        options: {
          // maintainAspectRatio: false,
          plugins: {
            legend: {
              position: 'bottom',
              align: 'middle',
              labels: {
                font: {
                  size: 17.5,
                },
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
