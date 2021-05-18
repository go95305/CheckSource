<template>
	<div class="dashboard-table">
		<div class="d-tbl-header">
			<table class="dash-table" cellpadding="0" cellspacing="0" border="0">
				<thead class="dash-thead-css">
					<tr>
						<th scope="col">저장소</th>
						<th scope="col">부서</th>
						<th scope="col">프로젝트</th>
						<th scope="col">경고 라이선스 수</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="d-tbl-content">
			<table class="dash-table" cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr
						class="dash-table-tr"
						v-for="(item, index) of list"
						:key="`${index}_list`"
						@click="GoProjectResult(item.gitType, item.gitProjectId)"
					>
						<td>{{ GetGitType(item.gitType) }}</td>
						<td>{{ GetDepartmentName(item.departId) }}</td>
						<td>{{ item.name }}</td>
						<td>{{ item.cnt }}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<infinite-loading
			ref="InfiniteLoading"
			@infinite="GetList"
			spinner="circles"
		>
			<div class="infinite-loading-message" slot="no-more">
				<!-- <button class="last-list-button">마지막입니다</button> -->
			</div>
			<div class="infinite-loading-message" slot="no-results">
				<!-- 결과가 없습니다. -->
			</div>
			<div class="infinite-loading-message" slot="error">
				<!-- 불러오지 못했습니다. -->
			</div>
		</infinite-loading>
	</div>
</template>
<script>
import "@/assets/css/DashBoard/DashBoardTable.scss";
import InfiniteLoading from "vue-infinite-loading";
import dayjs from "dayjs";
import dashboardApi from "@/api/dashboard.js";
import Info from "@/api/info.js";

export default {
	name: "LicenseWarningTable",
	components: {
		InfiniteLoading,
	},
	data() {
		return {
			page: 1,
			size: 5,
			currentTime: dayjs().format("YYYY-MM-DD HH:mm:ss"),
			list: [],
		};
	},
	props: {
		departId: {
			type: Number,
			default: 0,
		},
	},
	watch: {
		departId: function () {
			this.ResetList();
		},
	},
	methods: {
		ResetList: function () {
			//리스트 초기화
			this.page = 1;
			this.list = [];
			this.currentTime = dayjs().format("YYYY-MM-DD HH:mm:ss");
			if (this.$refs.InfiniteLoading) {
				this.$refs.InfiniteLoading.stateChanger.reset();
			}
		},
		GetList: function ($state) {
			//검증된 프로젝트 리스트 조회
			// console.log('키워드:' + this.keyword);
			if (this.departId == 0) {
				dashboardApi
					.readDashboardWarning(this.page, this.size, this.currentTime)
					.then((response) => {
						console.log(response.data);
						if (response.data.length > 0) {
							this.list = this.list.concat(response.data);
							++this.page;
							$state.loaded();
						} else {
							$state.complete();
						}
					});
			} else {
				dashboardApi
					.readDepartmentDashboardWarning(
						this.page,
						this.departId,
						this.size,
						this.currentTime
					)
					.then((response) => {
						console.log(response.data);
						if (response.data.length > 0) {
							this.list = this.list.concat(response.data);
							++this.page;
							$state.loaded();
						} else {
							$state.complete();
						}
					});
			}
		},
		GetGitType: function (gitId) {
			return gitId < 3 ? "GitLab" : "GitHub";
		},
		GetDepartmentName: function (departId) {
			return Info.GetDepartmentName(departId - 1);
		},
		GoProjectResult: function (gitType, projectId) {
			this.$router.push({
				name: "Summary",
				query: { gitType: gitType, projectId: projectId },
			});
		},
	},
};
</script>
<style scoped>
.dashboard-table {
	display: flex;
	flex-flow: column nowrap;
}

.d-tbl-content {
	flex: 1 0 0;
	max-height: none;
}
</style>
