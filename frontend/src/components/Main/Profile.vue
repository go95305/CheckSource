<template>
	<div>
		<img
			id="profile-image"
			:src="require(`@/assets/images/shoo.png`)"
			alt="ProfileImage"
		/>
		<div id="profile-info">
			<p id="profile-info__name">{{ name }}</p>
			<span id="profile-info__department">{{ department }}</span>
			<span id="profile-info__rank">{{ job }}</span>
		</div>
		<div id="profile-hr"></div>
	</div>
</template>
<script>
import Info from "@/api/info.js";
import { mapGetters } from "vuex";
export default {
	name: "NavbarProfile",
	data() {
		return {
			image: 1,
			name: "",
			department: 1,
			job: 1,
		};
	},
	computed: {
		...mapGetters(["getName", "getJob", "getDepartment", "getUserImg"]),
	},
	created() {
		// const img = new URL("@/assets/images/shoo.png");
		// console.log(typeof Info.GetProfileImage(this.getUserImg));
		// console.log(typeof "@/assets/images/shoo.png");
		// this.image = require(Info.GetProfileImage(this.getUserImg));
		this.name = this.getName;
		this.department = Info.GetDepartmentName(this.getDepartment);
		this.job = Info.GetJobName(this.getJob);
	},
};
</script>
<style scoped src="@/assets/css/Main/Profile.css"></style>
