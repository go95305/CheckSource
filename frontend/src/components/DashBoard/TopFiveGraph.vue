<template>
	<div class="top-five-chart">
		<canvas id="myChart"></canvas>
	</div>
</template>

<script>
import Chart from "chart.js/auto";

export default {
	data() {
		return {
			label: [],
			data: [],
			chart: null,
		};
	},
	props: {
		labels: Array,
		dataList: Array,
	},
	watch: {
		labels: {
			deep: true,
			handler() {
				this.label = this.labels;
				this.chart.destroy();
				this.createChart();
			},
		},
		dataList: {
			deep: true,
			handler() {
				this.data = this.dataList;
				this.chart.destroy();
				this.createChart();
			},
		},
	},
	methods: {
		createChart() {
			var ctx = document.getElementById("myChart");
			this.chart = new Chart(ctx, {
				type: "doughnut",
				data: {
					labels: this.labels,
					datasets: [
						{
							label: "# of Votes",
							data: this.dataList,
							backgroundColor: [
								"rgba(48, 138, 197, 0.6)",
								"rgba(35, 154, 110, 0.6)",
								"rgba(223, 107, 25, 0.6)",
								"rgba(206, 69, 99, 0.6)",
								"rgba(113, 116, 122, 0.6)",
							],
							borderColor: [
								"rgba(48, 138, 197, 0.7)",
								"rgba(35, 154, 110, 0.7)",
								"rgba(223, 107, 25, 0.7)",
								"rgba(206, 69, 99, 0.7)",
								"rgba(113, 116, 122, 0.7)",
							],
							borderWidth: 1,
						},
					],
				},
				options: {
					// maintainAspectRatio: false,
					plugins: {
						legend: {
							position: "bottom",
							align: "middle",
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
