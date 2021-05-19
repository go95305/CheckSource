<template>
	<div>
		<div class="oss-license-content-div">
			<p>{{ EncordingContent }}</p>
		</div>
	</div>
</template>
<script>
export default {
	name: "OSSDetailLicenseContent",
	props: {
		informationData: Object,
	},
	computed: {
		EncordingContent: function () {
			return window.atob(this.informationData.contents);
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseContent.css"></style>
