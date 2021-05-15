<template>
	<table class="detail-table">
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">라이선스명</td>
			<td class="detail-table-td-body">{{ license.name }}</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">식별자</td>
			<td class="detail-table-td-body">{{ license.identifier }}</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">코드 공개여부</td>
			<td class="detail-table-td-body" v-if="license.sourceopen.length != 0">
				{{ license.sourceopen }}
			</td>
			<td class="detail-table-td-body" v-else>
				<span class="material-icons"> close </span>
			</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">주소</td>
			<td class="detail-table-td-body">{{ license.url }}</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">생성자</td>
			<td class="detail-table-td-body">{{ license.userName }}</td>
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
