<template>
	<div id="mypage-gitlab-container">
		<!-- 현재 연동된 계정 정보 -->
		<div id="mypage-gitlab-current" v-if="currentAccount != null">
			<span id="mypage-gitlab-current-title">현재 연동된 계정</span>
			<span id="mypage-gitlab-current-account">{{ currentAccount }}</span>
		</div>

		<div id="mypage-gitlab-new-account" :class="{ open: openInputArea }">
			<label class="mypage-gitlab-label" for="mypage-gitlab-account-input"
				>UserName</label
			>
			<input
				id="mypage-gitlab-account-input"
				class="mypage-gitlab-input"
				type="text"
				v-model="newGitLabAccount.username"
			/>
			<label class="mypage-gitlab-label" for="mypage-gitlab-token-input"
				>Access Token</label
			>
			<input
				id="mypage-gitlab-token-input"
				class="mypage-gitlab-input"
				type="text"
				v-model="newGitLabAccount.accessToken"
			/>
		</div>

		<!-- 관리 버튼 -->
		<div id="mypage-gitlab-buttons">
			<button
				v-if="
					currentAccount == null ||
					(newGitLabAccount.username.length > 0 &&
						newGitLabAccount.accessToken.length > 0)
				"
				class="mypage-gitlab-button new-button"
				@click="MakeAccount"
			>
				<div>
					<i class="material-icons"> check </i>
					<span>연동하기</span>
				</div>
			</button>
			<button
				v-else
				class="mypage-gitlab-button new-button"
				@click="OpenAndCloseInputArea"
			>
				<div>
					<i class="material-icons"> swap_horiz </i>
					<span>다른 계정으로 연동하기</span>
				</div>
			</button>
			<button
				v-if="currentAccount != null"
				class="mypage-gitlab-button close-button"
				@click="CloseAccout"
			>
				<div>
					<i class="material-icons"> close </i>
					<span>연동끊기</span>
				</div>
			</button>
		</div>
	</div>
</template>
<script>
import { mapGetters } from "vuex";
import gitLabApi from "@/api/gitlab.js";
export default {
	name: "MyPageGitLab",
	data() {
		return {
			currentAccount: "checksource",
			openInputArea: false,
			newGitLabAccount: {
				gitlabId: "",
				username: "",
				accessToken: "",
			},
		};
	},
	watch: {
		openInputArea: function () {
			if (!this.openInputArea) {
				this.newGitLabAccount.username = "";
				this.newGitLabAccount.accessToken = "";
			}
		},
		getUserName: function () {
			this.currentAccount = this.getUserName;
			this.openInputArea = this.currentAccount == null;
		},
	},
	created() {
		this.currentAccount = this.getUserName;
		if (this.currentAccount == null) {
			this.openInputArea = true;
		}
	},
	methods: {
		OpenAndCloseInputArea: function () {
			this.openInputArea = !this.openInputArea;
		},
		MakeAccount: function () {
			if (this.currentAccount == null) {
				this.CreateAccount();
			} else {
				this.ChangeAccount();
			}
		},
		CreateAccount: function () {
			//계정 연동
			gitLabApi
				.createGitLabConnect(this.newGitLabAccount)
				.then((response) => {
					if (response.data.flag && response.data.accessflag) {
						alert("계정이 연동되었습니다.");
						this.$store.commit("CONNECTGITLAB", response.data);
					} else {
						alert("계정 연동에 실패했습니다.\n다시 한번 확인해주세요.");
					}
				})
				.catch(() => {
					alert("오류가 발생했습니다.");
				});
		},
		ChangeAccount: function () {
			//계정 연동 수정
			this.newGitLabAccount.gitlabId = this.getGitLabId;
			console.log(this.newGitLabAccount);
			gitLabApi
				.updateGitLabConnect(this.newGitLabAccount)
				.then((response) => {
					if (response.data.flag && response.data.accessflag) {
						alert("계정이 연동되었습니다.");
						this.$store.commit("CONNECTGITLAB", response.data);
					} else {
						alert("계정 연동에 실패했습니다.\n다시 한번 확인해주세요.");
					}
				})
				.catch(() => {
					alert("오류가 발생했습니다.");
				});
		},
		CloseAccout: function () {
			//연결끊기
			gitLabApi
				.deleteGitLabConnect(this.getGitLabId)
				.then((response) => {
					console.log(response.data);
					if (response.data) {
						alert("연동이 중지되었습니다.");
						this.$store.commit("DISCONNECTGITLAB");
					} else {
						alert("연동 중지에 실패했습니다.\n다시 한번 확인해주세요.");
					}
				})
				.catch(() => {
					alert("오류가 발생했습니다.");
				});
		},
	},
	computed: {
		...mapGetters(["getUserName", "getGitLabId"]),
	},
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageGitLab.css"></style>
