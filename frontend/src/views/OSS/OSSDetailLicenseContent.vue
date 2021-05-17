<template>
	<div>
		<div class="oss-license-content-div">
			<p>{{ informationData.contents }}</p>
		</div>
	</div>
</template>
<script>
export default {
	name: "OSSDetailLicenseContent",
	props: {
		informationData: Object,
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseContent.css"></style>
