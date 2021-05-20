<template>
	<div>
		<div class="main__cards">
			<!-- 저장소(부서) 별 정보 -->
			<div class="depart-card card">
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

					<div class="slider-background" v-if="projectList.length > 0">
						<div
							class="dash-c-btn dash-c-btn-prev"
							aria-label="Previous slide"
							@click="slide(-1)"
						>
							&#10094;
						</div>
						<transition-group id="slider" tag="div" :name="transitionName">
							<div :key="current" class="slide">
								<VerifyCard
									class="dashboard-verifycard"
									:project="projectList[current]"
									:departId="choicedDepartId"
									@goReport="GoReport"
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
			<div class="topfive-card card">
				<div class="card__header">
					<div class="card__header-title text-light">
						오픈소스 <strong>TOP5</strong>
					</div>
				</div>
				<div class="dash-dropdown">
					<DropDown
						name="topfive"
						:orderList="departList"
						@topfiveorderItemChange="GetTopFive"
					/>
				</div>
				<top-five-graph :labels="topFiveLabels" :dataList="topFiveValues">
				</top-five-graph>
			</div>

			<!-- warning -->
			<div class="warning-card card">
				<div class="card__header">
					<div class="card__header-title text-light">
						<strong>라이선스 의무 warning</strong>
					</div>
				</div>
				<div class="dash-dropdown">
					<DropDown
						name="warning"
						:orderList="departList"
						@warningorderItemChange="SetDepartId"
					/>
				</div>
				<license-warning-table
					:departId="warningDepartId"
				></license-warning-table>
			</div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from "vuex";
import dayjs from "dayjs";
import Info from "@/api/info.js";
import verifyApi from "@/api/verify.js";
import dashboardApi from "@/api/dashboard.js";
import TopFiveGraph from "@/components/DashBoard/TopFiveGraph.vue";
import VerifyCard from "@/components/DashBoard/VerifyCard.vue";
import DropDown from "@/components/DropDown/DropDown.vue";
import DashBoardTable from "@/components/DashBoard/DashBoardTable.vue";
import LicenseWarningTable from "../../components/DashBoard/LicenseWarningTable.vue";
import DashBoardOverview from "@/components/DashBoard/DashBoardOverview.vue";
import "vueperslides/dist/vueperslides.css";

export default {
	name: "DashBoard",
	components: {
		TopFiveGraph,
		VerifyCard,
		DropDown,
		DashBoardTable,
		DashBoardOverview,
		LicenseWarningTable,
	},
	data() {
		return {
			statisticsList: [],
			choicedDepartId: -1,
			currentTime: 0,
			projectList: [],
			topFiveLabels: [],
			topFiveValues: [],
			warningDepartId: 0,
			current: 0,
			direction: 1,
			transitionName: "fade",
			status: {
				project: "31",
				opensource: "29",
				license: "7",
				warning: "15",
			},
			departList: [],
		};
	},
	computed: {
		...mapGetters(["getDepartment"]),
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
		SetDepartId(index) {
			this.warningDepartId = index;
		},
		GoReport(gitType, projectId) {
			//레포트 페이지로 가기
			if (this.choicedDepartId == this.getDepartment) {
				this.$router.push({
					name: "Summary",
					query: { gitType: gitType, projectId: projectId },
				});
			} else {
				alert("내 부서의 프로젝트만 볼 수 있습니다.");
			}
		},
		slide(dir) {
			const card = document.getElementsByClassName("slide")[0];
			if (
				!(
					card.classList.contains("slide-next-leave-active") ||
					card.classList.contains("slide-prev-leave-active")
				)
			) {
				this.direction = dir;
				dir === 1
					? (this.transitionName = "slide-next")
					: (this.transitionName = "slide-prev");
				var len = this.projectList.length;
				this.current = (this.current + (dir % len) + len) % len;
			}
		},
	},
};
</script>
<style lang="scss" scoped>
@import "@/assets/css/DashBoard/DashBoard.scss";
</style>
