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
					<th>FULL NAME</th>
					<th>IDENTIFIER</th>
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
						<td>identifier</td>
						<td>{{ license.url }}</td>

						<td>{{ license.license }}</td>
						<td>{{ license.copyright }}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="oss-pagination">
			<pagination-remote
				:currentPage="page"
				:lastPage="4"
				@changePage="ChangePage"
			></pagination-remote>
		</div>
	</div>
</template>
<script>
import "@/assets/css/OSS/OSSTable.css";
import SearchBar from "../../components/SearchBar/SearchBar.vue";
import PaginationRemote from "../../components/Pagination/PaginationRemote.vue";
export default {
	name: "OSSLicense",
	components: { SearchBar, PaginationRemote },
	data() {
		return {
			filterList: ["NAME", "IDENTIFIER"],
			licenseList: [],
			page: 1,
			size: 20,
		};
	},
	created() {
		if (this.$route.query.page) {
			this.page = Number(this.$route.query.page);
		}
		if (this.$route.query.size) {
			this.size = Number(this.$route.query.size);
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
		ChangePage: function (page) {
			this.page = page;
			this.$router.push({
				query: { page: page, size: this.size },
			});
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSLicense.css"></style>
