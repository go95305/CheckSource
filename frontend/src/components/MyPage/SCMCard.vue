<template>
	<div>
		<div class="mypage-scm-card-background">
			<div class="mypage-scm-card-div">
				<span v-if="gitType<2">{{ getBaseUrl(account.gitlabId) }}</span>
				<span v-else>{{ getBaseUrl(account.githubId) }}</span>
				<span>{{ account.username }}</span>
				<img
					v-if="gitType < 2"
					class="mypage-scm-card-icon"
					src="@/assets/images/gitlab-icon.png"
					alt="gitlab-icon"
				/>
				<img
					v-else
					class="mypage-scm-card-icon"
					src="@/assets/images/github-icon.png"
					alt="gitlab-icon"
				/>
			</div>
			<span v-if="gitType<2"
				class="mypage-scm-card-close-button material-icons"
				@click="deleteSCMCard"
			>
				close
			</span>
			<span v-else
				class="mypage-scm-card-close-button material-icons"
				@click="deleteHubSCMCard"
			>
				close
			</span>
		</div>
	</div>
</template>
<script>
import gitLabApi from "@/api/git.js";
import "@/assets/css/MyPage/SCMCard.css";
export default {
	name: "SCMCard",
	props: {
		index: Number,
		account: Object,
		gitType: Number,
	},
	methods: {
		getBaseUrl: function (index) {
			return gitLabApi.getBaseUrl(index - 1);
		},
		deleteSCMCard: function () {
			this.$emit("deleteSCM", this.index, this.account.gitlabId);
		},
		deleteHubSCMCard: function () {
			this.$emit("deleteSCM", this.index, this.account.githubId);
		},
	},
};
</script>
