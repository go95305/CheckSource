<template>
	<!-- /project/result-->
	<div>
		<my-project-path
			:department="'프로젝트'"
			:project="projectInfo.name"
			:rootPath="'/project/main/projects'"
		/>
		<div class="my-project-result-head">
			<h1>{{ projectInfo.name }}</h1>
			<div class="my-project-info">
				<span class="my-project-info-weburl">{{ projectInfo.webUrl }}</span>
				<span class="my-project-info-branch">{{ projectInfo.branch }}</span>
			</div>
		</div>
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
			gitType: this.$route.query.gitType,
			projectInfo: "",
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
			verifyApi
				.readVerifiedProjectInfo(this.gitType, this.projectId)
				.then((response) => {
					if (response.data) {
						this.projectInfo = response.data;
					}
				});
		},
	},
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectResult.css"></style>
