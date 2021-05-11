<template>
	<table class="detail-table">
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">FULL NAME</td>
			<td class="detail-table-td-body">이름</td>
		</tr>

		<tr class="detail-table-tr">
			<td class="detail-table-td-head">IDENTIFIER</td>
			<td class="detail-table-td-body">라이선스</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">CODE OPEN</td>
			<td class="detail-table-td-body">
				<span class="material-icons"> done </span>
			</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">URL</td>
			<td class="detail-table-td-body">url</td>
		</tr>
	</table>
</template>
<script>
import "@/assets/css/OSS/OSSDetailTable.css";
export default {
	name: "OSSDetailLicenseTable",
	props: {
		license: Object,
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseTable.css"></style>
