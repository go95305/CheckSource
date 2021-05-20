<template>
	<div class="dashboard-table">
		<div class="d-tbl-header">
			<table class="dash-table" cellpadding="0" cellspacing="0" border="0">
				<thead class="dash-thead-css">
					<tr>
						<th scope="col">부서</th>
						<th scope="col">프로젝트</th>
						<th scope="col">오픈소스</th>
						<th scope="col">라이선스</th>
						<th scope="col">주의</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="d-tbl-content">
			<table class="dash-table" cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr
						class="dash-table-tr"
						v-for="(item, index) of defaultList"
						:key="`${index}_list`"
						@click="ClickTable(item.departId)"
					>
						<td>{{ GetDepartmentName(item.departId) }}</td>
						<td>{{ item.projectCnt }}</td>
						<td>{{ item.opensourceCnt }}</td>
						<td>{{ item.licenseCnt }}</td>
						<td>{{ item.warningCnt }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>

<script>
import "@/assets/css/DashBoard/DashBoardTable.scss";
import Info from "@/api/info.js";
export default {
	name: "DashBoardTable",
	data() {
		return {
			defaultList: [
				{
					departId: 0,
					licenseCnt: 0,
					opensourceCnt: 0,
					projectCnt: 0,
					warningCnt: 0,
				},
			],
		};
	},
	props: {
		list: Array,
	},
	watch: {
		list: function () {
			this.defaultList = this.list;
		},
	},
	methods: {
		GetDepartmentName: function (index) {
			return Info.GetDepartmentName(index - 1);
		},
		ClickTable: function (departId) {
			this.$emit("getDepartProjects", departId);
		},
	},
};
</script>
