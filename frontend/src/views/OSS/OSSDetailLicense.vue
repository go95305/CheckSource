<template>
	<div>
		<my-project-path
			:department="'전체목록'"
			:project="license.name"
			rootPath="/list/license"
		/>
		<h1 class="oss-detail-title">{{ license.name }}</h1>
		<div class="oss-detail-table">
			<OSSDetailLicenceTable></OSSDetailLicenceTable>
		</div>

		<tab class="oss-detail-info-title" :list="tabList"></tab>
		<router-view></router-view>
	</div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import Tab from "@/components/Tab/Tab.vue";
import OSSDetailLicenceTable from "@/components/OSS/OSSDetailLicenceTable.vue";
export default {
	name: "OSSDetailLicense",
	components: {
		MyProjectPath,
		Tab,
		OSSDetailLicenceTable,
	},
	data() {
		return {
			id: this.$route.query.id,
			license: {
				id: "",
				name: "라이선스 이름",
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
};
</script>
<style></style>
