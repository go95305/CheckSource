<template>
	<div>
		<div class="search-bar-div">
			<search-bar
				:placeHolderText="'검색할 오픈소스를 입력하세요.'"
				:filterList="filterList"
				:filter="typeFilter"
				:keyword="keyword"
				@doSearch="DoSearch"
			></search-bar>
		</div>
		<div>
			<span class="added added-absolute"> : 사용자가 생성한 오픈소스</span>
			<table class="oss-table">
				<thead class="oss-table-thead">
					<th>오픈소스명</th>
					<th>주소</th>
					<th>라이선스</th>
					<th>저작권</th>
				</thead>
				<tbody class="oss-table-tbody">
					<tr
						class="oss-table-tr"
						v-for="(opensource, index) in openSourceList"
						:key="`${index}_openSourceList`"
						@click="GoDetail(opensource)"
					>
						<td :class="{ added: opensource.userId != '0' }">
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
			filterList: ["오픈소스명", "라이선스"],
			filterEnglishList: ["Name", "License"],
			openSourceList: [],
			page: 1,
			size: 10,
			totalPage: 10,
			typeFilter: 1,
			keyword: "",
		};
	},
	created() {
		if (this.$route.query.page) {
			this.page = Number(this.$route.query.page);
		}
		if (this.$route.query.keyword || this.$route.query.filter) {
			this.keyword = this.$route.query.keyword;
			this.typeFilter = Number(this.$route.query.filter);
		}
		console.log("들" + this.keyword);
		this.GetList();
	},
	watch: {
		$route: {
			deep: true,
			handler() {
				if (this.$route.query.page && this.page != this.$route.query.page) {
					this.page = Number(this.$route.query.page);
				} else if (!this.$route.query.page) {
					this.page = 1;
				}
				if (
					(this.$route.query.keyword &&
						this.keyword != this.$route.query.keyword) ||
					(this.$route.query.filter && this.keyword != this.$route.query.filter)
				) {
					this.keyword = this.$route.query.keyword;
					this.typeFilter = Number(this.$route.query.filter);
				} else if (!this.$route.query.keyword || !this.$route.query.filter) {
					this.keyword = "";
					this.typeFilter = 1;
				}
				this.GetList();
			},
		},
	},
	methods: {
		GetQuery: function () {
			if (this.$route.query.page) {
				this.page = Number(this.$route.query.page);
			}
			if (this.$route.query.size) {
				this.size = Number(this.$route.query.size);
			}
		},
		GetList: function () {
			console.log(this.filterEnglishList[this.typeFilter - 1]);
			opensourceApi
				.readOpenSourceList(
					this.keyword,
					this.page,
					this.size,
					this.filterEnglishList[this.typeFilter - 1]
				)
				.then((response) => {
					console.log(response.data);
					this.openSourceList = response.data.list;
					this.totalPage = response.data.totalPage;
				})
				.catch();
		},
		DoSearch: function (filter, keyword) {
			let newQuery = Object.assign({}, this.$route.query);
			newQuery.filter = filter;
			newQuery.keyword = keyword;
			newQuery.page = 1;
			this.$router.push({
				query: newQuery,
			});
		},
		ChangePage: function (page) {
			let newQuery = Object.assign({}, this.$route.query);
			newQuery.page = page;
			this.$router.push({
				query: newQuery,
			});
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
