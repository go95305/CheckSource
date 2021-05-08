<template>
	<div>
		<my-project-path :department="'프로젝트'" />
		<div class="myproject-gitlab-title-div">
			<h1>Gitlab</h1>
			<!-- <router-link class="myproject-edit-router-link" to="/project/main/edit"
				><span class="material-icons"> edit </span>Edit</router-link
			> -->
			<router-link class="myproject-edit-router-link" to="/project/main/edit"
				><span class="material-icons"> add </span>New Project</router-link
			>
		</div>
		<hr class="verify-header" />
		<div class="verify-body">
			<VerifyCard />
			<VerifyCard />
		</div>
	</div>
</template>

<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import VerifyCard from "../../components/DashBoard/VerifyCard.vue";

export default {
	name: "MyProjectSummary",
	components: { MyProjectPath, VerifyCard },
	data() {
		return {};
	},
	methods: {},
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectGitlab.css"></style>
