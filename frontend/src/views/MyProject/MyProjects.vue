<template>
	<!-- /project/main/projects -->
	<div>
		<my-project-path :department="'프로젝트'" />
		<div class="myproject-title-div">
			<h1>{{ departmentName }}</h1>
			<router-link
				class="myproject-edit-router-link"
				to="/project/main/newproject"
				><span class="material-icons"> add </span>새로 검증하기</router-link
			>
		</div>
		<hr class="verify-header" />
		<div class="verify-search-div">
			<search-bar
				placeHolderText="검색할 프로젝트명을 입력하세요."
				@doSearch="DoSearch"
			></search-bar>
		</div>
		<div class="verify-body">
			<VerifyCard
				v-for="(project, index) in projectList"
				:key="`${index}_projectList`"
				:project="project"
				@goReport="GoReport"
			/>
		</div>
		<infinite-loading
			ref="InfiniteLoading"
			@infinite="GetProjectList"
			spinner="circles"
		>
			<div class="infinite-loading-message" slot="no-more">
				<button class="last-list-button" @click="scrollUp">마지막입니다</button>
			</div>
			<div class="infinite-loading-message" slot="no-results">
				결과가 없습니다.
			</div>
			<div class="infinite-loading-message" slot="error">
				불러오지 못했습니다.
			</div>
		</infinite-loading>
	</div>
</template>

<script>
import { mapGetters } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import VerifyCard from "@/components/DashBoard/VerifyCard.vue";
import SearchBar from "@/components/SearchBar/SearchBar.vue";
import verifyApi from "@/api/verify.js";
import Info from "@/api/info.js";
import dayjs from "dayjs";
export default {
	name: "MyProjectSummary",
	components: { InfiniteLoading, MyProjectPath, VerifyCard, SearchBar },
	data() {
		return {
			departmentName: "",
			projectList: [],
			filter: "",
			keyword: "",
			page: 1,
			size: 10,
			currentTime: dayjs().format("YYYY-MM-DD HH:mm:ss"),
		};
	},
	computed: {
		...mapGetters(["getDepartment"]),
	},
	created() {
		this.departmentName = Info.GetDepartmentName(this.getDepartment - 1);
	},
	methods: {
		GoReport: function (gitType, projectId) {
			//레포트 페이지로 가기
			this.$router.push({
				name: "Summary",
				query: { gitType: gitType, projectId: projectId },
			});
		},
		DoSearch: function (filter, keyword) {
			//검색 키워드 저장
			this.filter = filter;
			this.keyword = keyword;
			this.ResetList();
		},
		ResetList: function () {
			//리스트 초기화
			this.page = 1;
			this.projectList = [];
			this.currentTime = dayjs().format("YYYY-MM-DD HH:mm:ss");
			if (this.$refs.InfiniteLoading) {
				this.$refs.InfiniteLoading.stateChanger.reset();
			}
		},
		GetProjectList: function ($state) {
			//검증된 프로젝트 리스트 조회
			console.log("키워드:" + this.keyword);
			verifyApi
				.readVerifiedProjectList(
					this.page,
					this.getDepartment,
					this.keyword,
					this.size,
					this.currentTime
				)
				.then((response) => {
					if (response.data.projectList.length > 0) {
						this.projectList = this.projectList.concat(
							response.data.projectList
						);
						++this.page;
						console.log(this.projectList);
						$state.loaded();
					} else {
						$state.complete();
					}
				});
		},

		scrollUp: function () {
			window.scrollTo({
				top: 0,
				left: 0,
				behavior: "smooth",
			});
		},
	},
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjects.css"></style>
