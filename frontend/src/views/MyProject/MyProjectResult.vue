<template>
	<!-- /project/result-->
	<div>
		<my-project-path
			:department="'프로젝트'"
			:project="projectName"
			:rootPath="'/project/main/projects'"
		/>
		<h1 id="my-project-result-title">{{ projectName }}</h1>
		<tab id="my-project-result-tab" :list="tabList" />
		<router-view />
	</div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
import verifyApi from "@/api/verify.js";
export default {
	name: "MyProjectResult",
	components: { MyProjectPath, Tab },
	data() {
		return {
			projectId: this.$route.query.projectId,
			projectName: "",
			tabList: [],
		};
	},
	created() {
		this.tabList = [
			{
				name: "분석 결과",
				path: "/project/result/summary",
			},
			{
				name: "오픈소스",
				path: "/project/result/opensource",
			},
			{
				name: "라이선스",
				path: "/project/result/license",
			},
		];
		this.GetProjectName();
	},
	methods: {
		GetProjectName: function () {
			verifyApi.readVerifiedProjectName(this.projectId).then((response) => {
				this.projectName = response.data;
			});
		},
	},
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectResult.css"></style>
