<template>
	<div class="top-five-chart">
		<no-result v-if="data.length == 0"></no-result>
		<canvas v-show="data.length > 0" id="myChart"></canvas>
	</div>
</template>

<script>
import Chart from "chart.js/auto";
import NoResult from "../MyProject/NoResult.vue";

export default {
	name: "TopFiveGraph",
	components: {
		NoResult,
	},
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
			},
		},
		dataList: {
			deep: true,
			handler() {
				this.data = this.dataList;
				this.createChart();
			},
		},
	},
	methods: {
		async createChart() {
			var ctx = document.getElementById("myChart");
			if (this.chart != null) {
				//chart가 destroy될때까지 대기
				await this.chart.destroy();
			}
			console.log("들");
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
					responsive: true,
					maintainAspectRatio: false,
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
	flex: 1 0 0;
	display: flex;
	justify-content: center;
	align-items: center;
	/* width: 60%;
	height: 60%; */
	padding: 15px;
}

.no-result {
	margin: 30px 0px;
}
</style>
