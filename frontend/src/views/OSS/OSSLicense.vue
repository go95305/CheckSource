<template>
	<div>
		<div class="search-bar-div">
			<search-bar
				:placeHolderText="'검색할 라이선스를 입력하세요.'"
				:filterList="filterList"
			></search-bar>
		</div>
		<div>
			<table class="oss-table">
				<thead class="oss-table-thead">
					<th>NAME</th>
					<th>URL</th>
					<th>RISK</th>
					<th>NOTICE</th>
				</thead>
				<tbody class="oss-table-tbody">
					<tr
						class="oss-table-tr"
						v-for="(license, index) in licenseList"
						:key="`${index}_openSourceList`"
					>
						<td>{{ license.name }}</td>
						<td>{{ license.url }}</td>
						<td>{{ license.license }}</td>
						<td>{{ license.copyright }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>
<script>
import "@/assets/css/OSS/OSSTable.css";
import SearchBar from "../../components/SearchBar/SearchBar.vue";
export default {
	name: "OSSLicense",
	components: { SearchBar },
	data() {
		return {
			filterList: ["NAME", "URL"],
			licenseList: [],
			page: 1,
			size: 20,
		};
	},
	created() {
		if (this.$route.query.page) {
			this.page = this.$route.query.page;
		}
		if (this.$route.query.size) {
			this.size = this.$route.query.size;
		}

		this.GetList();
	},
	methods: {
		GetList: function () {
			this.licenseList = [
				{
					name: "GLEW",
					url: "https://github.com/nigels-com/glew",
					license: "GLEW Modified BSD3 License",
					copyright: "Copyright 2008-2016",
				},
				{
					name: "httpx",
					url: "https://github.com/encode/httpx",
					license: "GLEW Modified BSD3 License",
					copyright: "Copyright 2008-2016",
				},
			];
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSLicense.css"></style>
