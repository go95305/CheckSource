<template>
	<div class="myproject-edit-gitlab">
		<repository-card
			v-for="(repository, index) in repositoryList"
			:key="`${index}_repositoryList`"
			:index="index"
			:repository="repository"
			:selected="false"
			:checked="IsSelected(repository.id)"
			@addRepoClick="AddRepoClick"
		></repository-card>
	</div>
</template>
<script>
import gitLabApi from "@/api/gitlab.js";
import { mapGetters } from "vuex";
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
export default {
	components: { RepositoryCard },
	name: "MyProjectEditGitLab",
	data() {
		return {
			repositoryList: [],
		};
	},
	props: {
		selectedRepositoryList: Array,
	},
	created() {
		this.GetRepositories();
	},
	methods: {
		GetRepositories: function () {
			//레포지토리 얻어오기
			gitLabApi
				.readGitLabProjects(this.getGitLabId)
				.then((response) => {
					if (response.data.accessflag) {
						this.repositoryList = response.data.projectList;
					} else {
						alert("Gitlab 토큰 기한이 만료되었습니다.\n다시 연동해주세요.");
						this.$router.push("/mypage/scm/gitlab");
					}
				})
				.catch(() => {
					alert("프로젝트 목록을 불러오지 못했습니다.");
				});
			this.repositoryList = [];
		},
		IsSelected: function (id) {
			//선택된 레포지토리인지 확인
			if (this.selectedRepositoryList.findIndex((i) => i.id == id) == -1) {
				return false;
			}
			return true;
		},
		AddRepoClick: function (repo) {
			this.$emit("addRepoClick", repo);
		},
	},
	computed: {
		...mapGetters(["getGitLabId"]),
	},
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEditGitLab.css"></style>
