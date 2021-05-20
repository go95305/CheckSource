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
			if (this.informationData.contents)
				return window.atob(this.informationData.contents);
			else return "";
		},
	},
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseContent.css"></style>
