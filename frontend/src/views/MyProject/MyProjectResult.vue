<template>
	<div>
		<my-project-path :department="'내 프로젝트'" :project="projectId" />
		<h1 id="my-project-result-title">{{ projectId }}</h1>
		<tab id="my-project-result-tab" :list="tabList" />
		<router-view />
	</div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
	name: "MyProjectResult",
	components: { MyProjectPath, Tab },
	data() {
		return {
			projectId: this.$route.query.projectId,
			tabList: [],
		};
	},
	created() {
		this.tabList = [
			{
				name: "Summary",
				path: "/project/result/summary",
			},
			{
				name: "Component",
				path: "/project/result/component",
			},
			{
				name: "License",
				path: "/project/result/license",
			},
		];
	},
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectResult.css"></style>
