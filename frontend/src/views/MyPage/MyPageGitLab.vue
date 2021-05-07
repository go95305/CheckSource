<template>
	<div id="mypage-gitlab-container">
		<!-- 현재 연동된 계정 정보 -->
		<div id="mypage-gitlab-current" v-if="isCurrentAccount">
			<span id="mypage-gitlab-current-title">현재 연동된 계정</span>
			<span id="mypage-gitlab-current-account">{{ currentAccount }}</span>
		</div>

		<div
			id="mypage-gitlab-new-account"
			:class="{ open: openNewAccount || !isCurrentAccount }"
		>
			<label class="mypage-gitlab-label" for="mypage-gitlab-account-input"
				>UserName</label
			>
			<input
				id="mypage-gitlab-account-input"
				class="mypage-gitlab-input"
				type="text"
				v-model="newAccount"
			/>
			<label class="mypage-gitlab-label" for="mypage-gitlab-token-input"
				>Access Token</label
			>
			<input
				id="mypage-gitlab-token-input"
				class="mypage-gitlab-input"
				type="text"
				v-model="newAccount"
			/>
		</div>

		<!-- 관리 버튼 -->
		<div id="mypage-gitlab-buttons">
			<button class="mypage-gitlab-button new-button" @click="CheckNewAccount">
				<div v-if="newAccount.length > 0 || !isCurrentAccount">
					<i class="material-icons"> check </i>
					<span>연동하기</span>
				</div>
				<div v-else>
					<i class="material-icons"> swap_horiz </i>
					<span>다른 계정으로 연동하기</span>
				</div>
			</button>
			<button
				v-if="isCurrentAccount"
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
export default {
	name: "MyPageGitLab",
	data() {
		return {
			isCurrentAccount: true,
			currentAccount: "checksource",
			openNewAccount: false,
			newAccount: "",
			newToken: "",
		};
	},
	watch: {
		openNewAccount: function () {
			if (!this.openNewAccount) {
				this.newAccount = "";
			}
		},
	},
	methods: {
		CheckNewAccount: function () {
			//새 계정 입력에 따른 연산
			if (!this.openNewAccount || this.newAccount.length == 0) {
				this.openNewAccount = !this.openNewAccount;
			} else {
				//변경시도
				alert("변경시도");
			}
		},
		CloseAccout: function () {
			//연결끊기 시도
			this.isCurrentAccount = false;
		},
	},
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageGitLab.css"></style>
