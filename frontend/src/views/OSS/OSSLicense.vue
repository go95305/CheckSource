<template>
	<div>
		<div class="search-bar-div">
			<search-bar
				:placeHolderText="'검색할 라이선스를 입력하세요.'"
				:filterList="filterList"
				@doSearch="DoSearch"
			></search-bar>
		</div>
		<div>
			<table class="oss-table">
				<thead class="oss-table-thead">
					<th>라이선스명</th>
					<th>식별자</th>
					<th>주소</th>
					<th>코드 공개여부</th>
				</thead>
				<tbody class="oss-table-tbody">
					<tr
						class="oss-table-tr"
						v-for="(license, index) in licenseList"
						:key="`${index}_openSourceList`"
						@click="GoDetail(license)"
					>
						<td>{{ license.name }}</td>
						<td>{{ license.identifier }}</td>
						<td>{{ license.url }}</td>
						<td v-if="license.sourceopen.length != 0">
							{{ license.sourceopen }}
						</td>
						<td v-else>의무 없음</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="oss-pagination">
			<pagination-remote
				:currentPage="page"
				:lastPage="totalPage"
				@changePage="ChangePage"
			></pagination-remote>
		</div>
	</div>
</template>
<script>
import "@/assets/css/OSS/OSSTable.css";
import licenseApi from "@/api/license.js";
import SearchBar from "../../components/SearchBar/SearchBar.vue";
import PaginationRemote from "../../components/Pagination/PaginationRemote.vue";
export default {
	name: "OSSLicense",
	components: { SearchBar, PaginationRemote },
	data() {
		return {
			filterList: ["라이선스명", "식별자"],
			filterEnglistList: ["Name", "Identifier"],
			licenseList: [],
			page: 1,
			size: 20,
			totalPage: 10,
			typeFilter: 1,
			keyword: "",
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
			licenseApi
				.readLicenseList(
					this.keyword,
					this.page,
					this.size,
					this.filterEnglistList[this.typeFilter - 1]
				)
				.then((response) => {
					this.licenseList = response.data.licenseList;
					this.totalPage = response.data.totalPage;
				})
				.catch();
		},
		DoSearch: function (filter, keyword) {
			this.typeFilter = filter;
			this.keyword = keyword;
			this.GetList();
		},
		ChangePage: function (page) {
			this.page = page;
			this.$router.push({
				query: { page: page, size: this.size },
			});
			this.GetList();
		},
		GoDetail: function (license) {
			this.$router.push({
				name: "OSSDetailLicenseInformation",
				query: { id: license.licenseId },
			});
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSLicense.css"></style>
