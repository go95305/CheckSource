<template>
	<div>
		<my-project-path :department="'마이페이지'" />
		<h1 id="mypage-scm-title">SCM 관리</h1>
		<tab id="mypage-scm-tab" :list="tabList" />
		<router-view></router-view>
	</div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
	name: "MyPageSCM",
	components: { MyProjectPath, Tab },
	data() {
		return {
			tabList: [
				{
					name: "GitLab",
					path: "/mypage/scm/gitlab",
				},
				{
					name: "GitHub",
					path: "/mypage/scm/github",
				},
			],
		};
	},
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageSCM.css"></style>
