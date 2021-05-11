<template>
	<div>
		<div class="search-bar-div">
			<search-bar
				:placeHolderText="'검색할 오픈소스를 입력하세요.'"
				:filterList="filterList"
				@doSearch="DoSearch"
			></search-bar>
		</div>
		<div>
			<span class="added added-absolute"> : Added by user</span>
			<table class="oss-table">
				<thead class="oss-table-thead">
					<th>NAME</th>
					<th>URL</th>
					<th>LICENSE</th>
					<th>COPYRIGHT</th>
				</thead>
				<tbody class="oss-table-tbody">
					<tr
						class="oss-table-tr"
						v-for="(opensource, index) in openSourceList"
						:key="`${index}_openSourceList`"
						@click="GoDetail(opensource)"
					>
						<td :class="{ added: added }">
							{{ opensource.name }}
						</td>
						<td>{{ opensource.url }}</td>
						<td>
							<span
								v-for="(licenseName, index) in opensource.licenseNameList"
								:key="`${index}_licenseNameList`"
								>{{ licenseName }}</span
							>
						</td>
						<td>{{ opensource.copyright }}</td>
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
import opensourceApi from "@/api/opensource.js";
import SearchBar from "../../components/SearchBar/SearchBar.vue";
import PaginationRemote from "../../components/Pagination/PaginationRemote.vue";
export default {
	name: "OSSOpenSource",
	components: { SearchBar, PaginationRemote },
	data() {
		return {
			filterList: ["Name", "License"],
			openSourceList: [],
			page: 1,
			size: 10,
			totalPage: 10,
			typeFilter: 1,
			keyword: "",
			added: true,
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
			opensourceApi
				.readOpenSourceList(
					this.keyword,
					this.page,
					this.size,
					this.filterList[this.typeFilter - 1]
				)
				.then((response) => {
					this.openSourceList = response.data.list;
					console.log(this.openSourceList);
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
		GoDetail: function (opensource) {
			this.$router.push({
				name: "OSSDetailOpenSource",
				query: { id: opensource.opensourceId },
			});
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSOpenSource.css"></style>
