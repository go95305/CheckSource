<template>
	<div id="mypage-gitlab-container">
		<!-- 현재 연동된 계정 정보 -->
		<h3 class="mypage-gitlab-title">현재 연동된 계정</h3>
		<div
			v-for="(account, index) in gitlab"
			:key="`${index}_accountList`"
			class="mypage-gitlab-background"
		>
			<div class="mypage-gitlab-div">
				<span>{{ account.baseurl }}</span>
				<span>{{ account.username }}</span>
			</div>
			<span class="mypage-gitlab-close-button material-icons"> close </span>
		</div>

		<div id="mypage-gitlab-new-account" :class="{ open: openInputArea }">
			<h3 class="mypage-gitlab-title">새로 연동할 계정 정보</h3>
			<label class="mypage-gitlab-label" for="mypage-gitlab-baseurl"
				>BaseURL</label
			>
			<select
				v-model="newGitLabAccount.gitlabId"
				name="gitlab-baseurl"
				id="mypage-gitlab-baseurl"
				class="mypage-gitlab-input"
			>
				<option
					v-for="(baseUrl, index) in baseUrlList"
					:key="`${index}_baseUrlList`"
					:value="index"
				>
					{{ baseUrl }}
				</option>
			</select>
			<label class="mypage-gitlab-label" for="mypage-gitlab-account-input"
				>UserName</label
			>
			<input
				id="mypage-gitlab-account-input"
				class="mypage-gitlab-input"
				type="text"
				v-model="newGitLabAccount.username"
			/>
		</div>

		<!-- 관리 버튼 -->
		<div id="mypage-gitlab-buttons">
			<button
				v-if="newGitLabAccount.username.length > 0 && openInputArea"
				class="mypage-gitlab-button new-button"
				@click="CreateAccount"
			>
				<div>
					<i class="material-icons"> check </i>
					<span>연동하기</span>
				</div></button
			><button
				v-else-if="newGitLabAccount.username.length == 0 && openInputArea"
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
import { mapGetters } from "vuex";
import gitLabApi from "@/api/gitlab.js";
export default {
	name: "MyPageGitLab",
	data() {
		return {
			baseUrlList: [],
			openInputArea: false,
			newGitLabAccount: {
				gitlabId: 0,
				username: "",
			},
			gitlab: [
				{
					baseurl: "gitlab.com",
					username: "계정1",
				},
				{
					baseurl: "lab.ssafy.com",
					username: "계정2",
				},
			],
		};
	},
	watch: {
		openInputArea: function () {
			if (!this.openInputArea) {
				this.newGitLabAccount.username = "";
				this.newGitLabAccount.accessToken = "";
			}
		},
		// getUserName: function () {
		// 	this.currentAccount = this.getUserName;
		// 	this.openInputArea = this.currentAccount == null;
		// },
	},
	created() {
		this.baseUrlList = gitLabApi.getBaseURL();
		// this.currentAccount = this.getUserName;
		// if (this.currentAccount == null) {
		// 	this.openInputArea = true;
		// }
	},
	methods: {
		OpenAndCloseInputArea: function () {
			this.openInputArea = !this.openInputArea;
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
