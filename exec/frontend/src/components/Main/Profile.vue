<template>
	<div>
		<img id="profile-image" :src="image.url" :alt="image.src" />
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
			image: {
				url: "",
			},
			name: "",
			department: 1,
			job: 1,
		};
	},
	computed: {
		...mapGetters(["getName", "getJob", "getDepartment", "getUserImg"]),
	},
	watch: {
		getName: function () {
			this.name = this.getName;
		},
		getJob: function () {
			this.job = Info.GetJobName(this.getJob - 1);
		},
		getDepartment: function () {
			this.department = Info.GetDepartmentName(this.getDepartment - 1);
		},
		getUserImg: function () {
			const img = Info.GetProfileImage(this.getUserImg - 1);
			if (img) {
				this.image = img;
			}
		},
	},
	created() {
		const img = Info.GetProfileImage(this.getUserImg - 1);
		if (img) {
			this.image = img;
		}
		this.name = this.getName;
		this.department = Info.GetDepartmentName(this.getDepartment - 1);
		this.job = Info.GetJobName(this.getJob - 1);
	},
};
</script>
<style scoped src="@/assets/css/Main/Profile.css"></style>
