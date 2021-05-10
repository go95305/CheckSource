<template>
	<div>
		<table class="oss-license-info-table">
			<tr
				class="oss-license-info-table-tr"
				v-for="(information, index) in informationList"
				:key="`${index}_informationList`"
			>
				<td class="oss-license-info-table-td-title">{{ information.name }}</td>
				<td
					class="oss-license-info-table-td-value"
					v-if="getValue(information.value)"
				>
					<span class="material-icons"> done </span>
				</td>
				<td class="oss-license-info-table-td-value" v-else>
					<span class="material-icons"> close </span>
				</td>
			</tr>
		</table>
	</div>
</template>
<script>
export default {
	name: "OSSDetailLicenseInformation",
	data() {
		return {
			informationList: [
				{
					name: "OSI 승인여부",
					value: "this.informationData.is_osi",
				},
				{
					name: "Copyright 고지 여부",
					value: "this.informationData.is_copyright",
				},
				{
					name: "라이선스 고지 여부",
					value: "this.informationData.is_license",
				},
				{
					name: "상업적 사용 가능 여부",
					value: "this.informationData.is_commercial",
				},
				{
					name: "비공개 사용 가능 여부",
					value: "this.informationData.is_modify",
				},
				{
					name: "책임 여부",
					value: "this.informationData.is_respon",
				},
				{
					name: "보증 여부",
					value: "this.informationData.is_warranty",
				},
				{
					name: "동일한 라이선스 유지 여부",
					value: "this.informationData.is_continue",
				},
			],
			informationData: {
				is_osi: true,
				is_copyright: false,
				is_license: false,
				is_commercial: false,
				is_modify: true,
				is_private: false,
				is_patent: true,
				is_respon: true,
				is_warranty: false,
				is_continue: false,
			},
		};
	},
	created() {
		this.getInformationData();
	},
	methods: {
		getInformationData: function () {},
		getValue: function (variableName) {
			return eval(variableName);
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseInformation.css"></style>
