<template>
	<table class="detail-table">
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">오픈소스명</td>
			<td class="detail-table-td-body">{{ opensource.name }}</td>
		</tr>

		<tr class="detail-table-tr">
			<td class="detail-table-td-head">라이선스</td>
			<td class="detail-table-td-body">
				<span
					v-for="(licenseName, index) in opensource.licenseList"
					:key="`${index}_licenseList`"
					>{{ licenseName.name }}</span
				>
			</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">저작권</td>
			<td class="detail-table-td-body">{{ opensource.copyright }}</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">주소</td>
			<td class="detail-table-td-body">
				{{ opensource.url }}
			</td>
		</tr>
		<tr class="detail-table-tr">
			<td class="detail-table-td-head">생성자</td>
			<td class="detail-table-td-body">
				{{ opensource.userName }}
			</td>
		</tr>
	</table>
</template>
<script>
import "@/assets/css/OSS/OSSDetailTable.css";
export default {
	name: "OSSDetailOpenSourceTable",
	props: {
		opensource: Object,
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailOpenSourceTable.css"></style>
