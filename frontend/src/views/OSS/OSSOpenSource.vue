<template>
	<div>
		<div class="search-bar-div">
			<search-bar
				:placeHolderText="'검색할 오픈소스를 입력하세요.'"
				:filterList="filterList"
			></search-bar>
		</div>
		<div>
			<table class="oss-table">
				<thead>
					<th>NAME</th>
					<th>URL</th>
					<th>LICENSE</th>
					<th>COPYRIGHT</th>
				</thead>
				<tbody>
					<tr
						v-for="(opensource, index) in openSourceList"
						:key="`${index}_openSourceList`"
					>
						<td>{{ opensource.name }}</td>
						<td>{{ opensource.url }}</td>
						<td>{{ opensource.license }}</td>
						<td>{{ opensource.copyright }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>
<script>
import "@/assets/css/OSS/OSSOpenSource.css";
import "@/assets/css/OSS/OSSTable.css";
import SearchBar from "../../components/SearchBar/SearchBar.vue";
export default {
	name: "OSSOpenSource",
	components: { SearchBar },
	data() {
		return {
			filterList: ["NAME", "URL"],
			openSourceList: [],
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
			this.openSourceList = [
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
