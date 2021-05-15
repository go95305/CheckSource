<template>
	<div>
		<my-project-path
			:department="'전체목록'"
			:project="license.name"
			rootPath="/list/license"
		/>
		<h1 class="oss-detail-title">{{ license.name }}</h1>
		<div class="oss-detail-table">
			<OSSDetailLicenceTable :license="license"></OSSDetailLicenceTable>
		</div>

		<tab class="oss-detail-info-title" :list="tabList"></tab>
		<router-view :informationData="license"></router-view>
	</div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import Tab from "@/components/Tab/Tab.vue";
import OSSDetailLicenceTable from "@/components/OSS/OSSDetailLicenceTable.vue";
import licenseApi from "@/api/license.js";

export default {
	name: "OSSDetailLicense",
	components: {
		MyProjectPath,
		Tab,
		OSSDetailLicenceTable,
	},
	data() {
		return {
			licenseId: this.$route.query.id,
			license: {
				id: "",
				name: "",
				identifier: "",
				sourceopen: "",
				url: "",
			},
			tabList: [
				{
					name: "Information",
					path: "/list/detail/license/information",
				},
				{
					name: "Content",
					path: "/list/detail/license/content",
				},
			],
		};
	},
	created() {
		this.GetLicenseDetail();
	},
	methods: {
		GetLicenseDetail: function () {
			licenseApi
				.getDetailLicense(this.licenseId)
				.then((response) => {
					this.license = response.data;
					console.log(this.license.sourceopen);
				})
				.catch();
		},
	},
};
</script>
<style></style>
