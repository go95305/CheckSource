<template>
	<div>
		<div class="mypage-scm-card-background">
			<div class="mypage-scm-card-div">
				<span>{{ account.baseurl }}</span>
				<span>{{ account.username }}</span>
				<img
					class="mypage-scm-card-icon"
					src="@/assets/images/gitlab-icon.png"
					alt="gitlab-icon"
				/>
			</div>
			<span class="mypage-scm-card-close-button material-icons"> close </span>
		</div>
	</div>
</template>
<script>
import "@/assets/css/MyPage/SCMCard.css";
export default {
	name: "SCMCard",
	props: {
		account: Object,
	},
};
</script>
