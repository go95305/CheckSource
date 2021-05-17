<template>
	<div>
		<div class="main__cards">
			<!-- 저장소(부서) 별 정보 -->
			<div class="card">
				<div class="card__header">
					<div class="card__header-title">
						<strong>부서별 정보</strong>
					</div>
				</div>
				<!-- 총 갯수 -->
				<div class="card__main">
					<!-- <DashBoardOverview /> -->
					<dash-board-overview :status="status"> </dash-board-overview>
					<DashBoardTable
						:list="statisticsList"
						@getDepartProjects="GetDepartProjects"
					/>

					<div id="slider">
						<div
							class="dash-c-btn dash-c-btn-prev"
							aria-label="Previous slide"
							@click="slide(-1)"
						>
							&#10094;
						</div>
						<transition-group
							tag="div"
							:name="transitionName"
							class="slides-group"
						>
							<div v-if="projectList.length > 0" :key="current" class="slide">
								<VerifyCard
									class="dashboard-verifycard"
									:project="projectList[current]"
								/>
							</div>
						</transition-group>

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
						<DropDown :orderList="departList" @orderItemChange="GetTopFive" />
					</div>
				</div>
				<top-five-graph :labels="topFiveLabels" :dataList="topFiveValues">
				</top-five-graph>
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
						<DropDown :orderList="departList" />
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
										<th scope="col">저장소</th>
										<th scope="col">부서</th>
										<th scope="col">프로젝트</th>
										<th scope="col">오픈소스</th>
										<th scope="col">라이선스</th>
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
import dayjs from "dayjs";
import Info from "@/api/info.js";
import verifyApi from "@/api/verify.js";
import dashboardApi from "@/api/dashboard.js";
import TopFiveGraph from "@/components/DashBoard/TopFiveGraph.vue";
import VerifyCard from "@/components/DashBoard/VerifyCard.vue";
import DropDown from "@/components/DropDown/DropDown.vue";
import DashBoardTable from "@/components/DashBoard/DashBoardTable.vue";
import DashBoardOverview from "@/components/DashBoard/DashBoardOverview.vue";
import "vueperslides/dist/vueperslides.css";
import "@/assets/css/DashBoard/DashBoard.scss";

export default {
	name: "DashBoard",
	components: {
		TopFiveGraph,
		VerifyCard,
		DropDown,
		DashBoardTable,
		DashBoardOverview,
	},
	data() {
		return {
			statisticsList: [],
			choicedDepartId: -1,
			currentTime: 0,
			projectList: [],
			topFiveLabels: [],
			topFiveValues: [],
			current: 0,
			direction: 1,
			transitionName: "fade",
			show: false,
			status: { project: "31", opensource: "29", license: "7", warning: "15" },
			departList: [],
		};
	},
	created() {
		this.departList = Info.GetDepartmentList().slice();
		this.departList.unshift("전체");
		this.GetStatistics();
		this.GetTopFive(0);
	},
	methods: {
		GetStatistics() {
			//부서별 통계정보 조회
			dashboardApi.readStatistics().then((response) => {
				if (response.data) {
					this.statisticsList = response.data.statisticsList;
					this.status.project = response.data.totalProjectCnt;
					this.status.opensource = response.data.totalOpensourceCnt;
					this.status.license = response.data.totalLicenseCnt;
					this.status.warning = response.data.totalWarningCnt;
				}
			});
		},
		GetDepartProjects(departId) {
			//클릭한 부서의 프로젝트 리스트 조회
			this.choicedDepartId = departId;
			this.currentTime = dayjs().format("YYYY-MM-DD HH:mm:ss");
			verifyApi
				.readVerifiedProjectList(1, departId, "", 100, this.currentTime)
				.then((response) => {
					this.projectList = response.data.projectList;
				});
		},
		GetTopFive(index) {
			//top5 정보 조회
			console.log(index);
			if (index == 0) {
				dashboardApi.readTopFive().then((response) => {
					this.SetTopFive(response.data);
				});
			} else {
				dashboardApi.readTopFiveDepart(index).then((response) => {
					this.SetTopFive(response.data);
					// console.log(this.topFiveList);
				});
			}
		},
		SetTopFive(list) {
			let labels = [];
			let values = [];
			console.log(list);
			for (let item of list) {
				labels.push(item.name);
				values.push(item.cnt);
			}
			this.topFiveLabels = labels;
			this.topFiveValues = values;
		},
		slide(dir) {
			console.log(this.current);
			this.direction = dir;
			dir === 1
				? (this.transitionName = "slide-next")
				: (this.transitionName = "slide-prev");
			var len = this.projectList.length;
			this.current = (this.current + (dir % len) + len) % len;
		},
	},
	mounted() {
		this.show = true;
	},
};
</script>
