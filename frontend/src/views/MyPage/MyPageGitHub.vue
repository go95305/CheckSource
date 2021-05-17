<template>
	<div id="mypage-gitlab-container">
		<!-- 현재 연동된 계정 정보 -->
		<div v-if="github.length > 0" class="mypage-scm-account-background">
			<h3 class="mypage-gitlab-title">현재 연동된 계정</h3>
			<SCMCard
				v-for="(account, index) in github"
				:key="`${index}_accountList`"
				:index="index"
				:account="account"
				:gitType="3"
				@deleteSCM="DeleteAccount"
			></SCMCard>
		</div>

		<!-- 새로 입력할 계정 정보 -->
		<div id="mypage-gitlab-new-account" :class="{ open: openInputArea }">
			<h3 class="mypage-gitlab-title">새로 연동할 계정 정보</h3>
			<label class="mypage-gitlab-label" for="mypage-gitlab-account-input-token"
				>PersonalAccessToken</label
			>
			<input
				id="mypage-gitlab-account-input-token"
				class="mypage-gitlab-input"
				name="personalAccessToken"
				type="text"
				v-model="newGitHubAccount.personalAccessToken"
			/>
			<label
				class="mypage-gitlab-label"
				for="mypage-gitlab-account-input-username"
				>UserName</label
			>
			<input
				id="mypage-gitlab-account-input-username"
				class="mypage-gitlab-input"
				name="username"
				type="text"
				v-model="newGitHubAccount.username"
			/>
		</div>

		<!-- 관리 버튼 -->
		<div id="mypage-gitlab-buttons">
			<button
				v-if="newGitHubAccount.username.length > 0 && openInputArea"
				class="mypage-gitlab-button new-button"
				@click="CreateAccount"
			>
				<div>
					<i class="material-icons"> check </i>
					<span>연동하기</span>
				</div></button
			><button
				v-else-if="newGitHubAccount.username.length == 0 && openInputArea"
				class="mypage-gitlab-button new-button"
				@click="OpenAndCloseInputArea"
			>
				<div>
					<i class="material-icons"> close </i>
					<span>창 닫기</span>
				</div>
			</button>
			<button
				v-else
				class="mypage-gitlab-button new-button"
				@click="OpenAndCloseInputArea"
			>
				<div>
					<i class="material-icons"> add </i>
					<span>계정추가</span>
				</div>
			</button>
		</div>
	</div>
</template>
<script>
import SCMCard from "@/components/MyPage/SCMCard.vue";
import { mapGetters } from "vuex";
import gitApi from "@/api/git.js";
export default {
	name: "MyPageGitHub",
	components: { SCMCard },
	data() {
		return {
			baseURL: "https://api.github.com/",
			openInputArea: false,
			newGitHubAccount: {
				personalAccessToken: "",
				username: "",
			},
			github: [],
		};
	},
	created() {
		this.github = this.getGitHubList;
		if (this.github.length == 0) {
			this.openInputArea = true;
		}
	},
	watch: {
		openInputArea: function () {
			if (!this.openInputArea) {
				//계정 추가 영역 열 때 비우기
				this.newGitHubAccount.username = "";
				this.newGitHubAccount.personalAccessToken = "";
			}
		},
		getGitHubList: {
			deep: true,
			handler(newData) {
				this.github = [];
				this.github = newData;
			},
		},
	},
	methods: {
		OpenAndCloseInputArea: function () {
			//계정 추가 영역 열고 닫기
			this.openInputArea = !this.openInputArea;
		},
		CreateAccount: function () {
			//계정 연동
			gitApi
				.createGitHubConnect(this.newGitHubAccount)
				.then((response) => {
					if (response.data.flag) {
						alert("계정이 연동되었습니다.");
						this.$store.commit("CONNECTGITHUB", response.data);
						this.OpenAndCloseInputArea();
					} else {
						alert("존재하지 않는 계정입니다.\n다시 확인해주세요.");
					}
				})
				.catch(() => {
					alert("오류가 발생했습니다.");
				});
		},
		DeleteAccount: function (index, githubId) {
			//연결끊기
			console.log(githubId);
			gitApi
				.deleteGitHubConnect(githubId)
				.then(() => {
					alert("연동이 중지되었습니다.");
					this.$store.commit("DISCONNECTGITHUB", index);
				})
				.catch(() => {
					alert("오류가 발생했습니다.");
				});
		},
	},
	computed: {
		...mapGetters(["getGitHubList"]),
	},
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageGitLab.css"></style>
