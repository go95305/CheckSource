<template>
	<div class="container">
		<div class="table-header">
			<div class="title title-1">Name</div>
			<div class="title title-2">Identifier</div>
			<div class="title title-3">Url</div>
			<div class="title title-3">Code Open</div>
		</div>
		<ul class="responsive-table" :key="index" v-for="(item, index) in list">
			<li class="table-row" :class="{ illegal: item.sourceopen.length > 0 }">
				<div class="col col-1">{{ item.name }}</div>
				<div class="col col-2">{{ item.identifier }}</div>
				<div class="col col-3">{{ item.url }}</div>
				<div class="col col-3" v-if="item.sourceopen.length > 0">
					{{ item.sourceopen }}
				</div>
				<div class="col col-3" v-else>
					<span class="material-icons"> close </span>
				</div>
			</li>
		</ul>
	</div>
</template>
<script>
import verifyApi from "@/api/verify.js";
export default {
	data() {
		return {
			projectId: this.$route.query.projectId,
			list: [
				// {
				// 	name: "Apache License 2.0",
				// 	url: "http://www.apache.org/licenses/LICENSE-2.0",
				// 	obligation: "Apache License 2.0",
				// 	illegal: true,
				// },
				// {
				// 	name: "GNU General Public License v2.0 or later",
				// 	url: "https://opensource.org/licenses/BSD-3-Clause",
				// 	obligation: "Apache License 2.0",
				// 	illegal: true,
				// },
				// {
				// 	name: "Eclipse Public License 2.0",
				// 	url: "https://www.eclipse.org/legal/epl-2.0",
				// 	obligation: "Apache License 2.0",
				// 	illegal: false,
				// },
			],
		};
	},
	created() {
		this.getList();
	},
	methods: {
		getList: function () {
			verifyApi.readVerifiedLicenseList(this.projectId).then((response) => {
				this.list = response.data;
			});
		},
	},
};
</script>
<style scoped src="../../assets/css/MyProject/LicenseList.css"></style>
