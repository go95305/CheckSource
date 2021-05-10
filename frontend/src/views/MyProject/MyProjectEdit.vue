<template>
	<div>
		<my-project-path :department="'프로젝트'" :project="'Edit'" />
		<h1 id="myproject-edit-title">Edit</h1>
		<div id="myproject-edit-container">
			<div id="myproject-edit-selected-div">
				<h3 id="myproject-edit-selected-title">선택된 프로젝트</h3>
				<repository-card
					v-for="(repository, index) in selectedRepositoryList"
					:key="`${index}_repositoryList`"
					:repository="repository"
					:selected="true"
					@selectedRepoClick="SelectedRepoClick"
				></repository-card>
				<button
					id="myproject-edit-selected-button"
					@click="GoVerify"
					:disabled="selectedRepositoryList.length == 0"
				>
					검증하기
				</button>
			</div>
			<div id="myproject-edit-tab-div">
				<!-- <button id="myproject-edit-scm-button">
                    <span class="material-icons"> settings </span>
                </button> -->
				<tab id="myproject-edit-tab" :list="tabList" />
				<router-view
					id="myproject-edit-routerview"
					:selectedRepositoryList="selectedRepositoryList"
					@addRepoClick="AddRepoClick"
				></router-view>
			</div>
		</div>
	</div>
</template>
<script>
import gitLabApi from "@/api/gitlab.js";
import { mapGetters } from "vuex";
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
	name: "MyProjectEdit",
	components: { MyProjectPath, Tab, RepositoryCard },
	data() {
		return {
			tabList: [
				{
					name: "GitLab",
					path: "/project/main/edit/gitlab",
				},
			],
			selectedRepositoryList: [],
		};
	},
	created() {
		this.GetSelectedRepositories();
	},
	methods: {
		GetSelectedRepositories: function () {
			this.selectedRepositoryList = [];
		},
		SelectedRepoClick: function (id) {
			// 선택된 레포지토리 클릭 => selectedList에서 삭제
			let index = this.selectedRepositoryList.findIndex((i) => i.id == id);
			this.selectedRepositoryList.splice(index, 1);
		},
		AddRepoClick: function (repo) {
			//레포지토리 클릭 => selectedList에 추가
			this.selectedRepositoryList.push(repo);
		},
		GoVerify: function () {
			gitLabApi
				.verifyGitLabProjects(this.getGitLabId, this.selectedRepositoryList)
				.then(() => {
					alert("검증을 시작합니다.");
					this.$router.push("/project/main/status");
				});
		},
	},
	computed: {
		...mapGetters(["getGitLabId"]),
	},
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEdit.css"></style>
