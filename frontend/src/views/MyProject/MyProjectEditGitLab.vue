<template>
	<!-- /project/main/newproject/gitlab -->
	<div class="myproject-edit">
		<DropDown
			class="myproject-dropdown"
			v-if="gitlabAccountList.length > 0"
			:orderList="gitlabAccountList"
			@orderItemChange="SetGitLabAccountValue"
		></DropDown>
		<MyProjectEditNoAccount
			v-if="gitlabAccountList.length == 0"
			:link="scmLink"
		></MyProjectEditNoAccount>
		<div v-else class="myproject-edit-gitlab">
			<loading v-if="loading"></loading>
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
	</div>
</template>
<script>
import { mapGetters } from "vuex";
import gitLabApi from "@/api/git.js";
import RepositoryCard from "@/components/MyProject/RepositoryCard.vue";
import Loading from "@/components/Loading/Loading.vue";
import DropDown from "@/components/DropDown/DropDown.vue";
import MyProjectEditNoAccount from "@/components/MyProject/MyProjectEditNoAccount.vue";
export default {
	name: "MyProjectEditGitLab",
	components: {
		DropDown,
		RepositoryCard,
		Loading,
		MyProjectEditNoAccount,
	},
	data() {
		return {
			repositoryList: [],
			loading: false,
			gitlabAccountList: [],
			gitlabAccountValue: 0,
			scmLink: "/mypage/scm/gitlab",
			branchList: [],
			selectRepo: null,
		};
	},
	props: {
		selectedRepositoryList: Array,
	},
	created() {
		this.GetGitLabAccountList();
		this.GetRepositories();
	},
	computed: {
		...mapGetters(["getGitLabList"]),
	},
	watch: {
		gitlabAccountValue: function () {
			this.GetRepositories();
			this.$emit(
				"changeAccountValue",
				this.getGitLabList[this.gitlabAccountValue].gitlabId
			);
		},
	},
	methods: {
		GetGitLabAccountList: function () {
			for (let i = 0; i < this.getGitLabList.length; ++i) {
				this.gitlabAccountList[i] =
					this.getGitLabList[i].username +
					"(" +
					gitLabApi.getBaseUrl(this.getGitLabList[i].gitlabId - 1) +
					")";
			}
		},
		SetGitLabAccountValue: function (value) {
			this.gitlabAccountValue = value;
		},
		GetRepositories: function () {
			//레포지토리 얻어오기
			this.loading = true;
			this.repositoryList = [];
			gitLabApi
				.readGitLabProjects(
					this.getGitLabList[this.gitlabAccountValue].gitlabId
				)
				.then((response) => {
					this.loading = false;
					if (response.data.accessflag) {
						this.repositoryList = response.data.projectList;
					} else {
						alert("Gitlab 토큰 기한이 만료되었습니다.\n다시 연동해주세요.");
						this.$router.push("/mypage/scm/gitlab");
					}
				})
				.catch(() => {
					this.loading = false;
					this.repositoryList = [];
					alert("프로젝트 목록을 불러오지 못했습니다.");
				});
		},
		IsSelected: function (id) {
			//선택된 레포지토리인지 확인
			if (this.selectedRepositoryList.findIndex((i) => i.id == id) == -1) {
				return false;
			}
			return true;
		},
		AddRepoClick: function (repo) {
			this.selectRepo = repo;
			this.GetRepoBranch();
		},
		GetRepoBranch: function () {
			//브랜치 목록 가져오기
			gitLabApi
				.readProjectBranches(
					this.getGitLabList[this.gitlabAccountValue].gitlabId,
					this.selectRepo.id
				)
				.then((response) => {
					let branchOption;
					for (let branch of response.data) {
						branchOption[branch] = branch;
					}
					console.log(branchOption);
				})
				.catch(() => {
					alert("프로젝트 브랜치 목록을 불러오지 못했습니다.");
				});
		},
		SelectBranch: function (branchName) {
			//브랜치 선택 완료
			let branchRepo = Object.assign({}, this.selectRepo);
			branchRepo.branch = branchName;
			this.$emit("addRepoClick", branchRepo);
		},
		CancelBranch: function () {
			//브랜치 선택 취소
			this.branchList = [];
		},
	},
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEditGitLab.css"></style>
