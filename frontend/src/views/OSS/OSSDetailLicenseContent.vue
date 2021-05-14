<template>
  <div>
    <div class="oss-license-content-div">
      <p>{{ license.contents }}</p>
    </div>
  </div>
</template>
<script>
import licenseApi from '@/api/license.js';

export default {
  name: 'OSSDetailLicenseContent',
  data() {
    return {
      licenseId: this.$route.query.id,
      license: {
        contents: '',
      },
    };
  },
  created() {
    this.GetDetailInfo();
  },
  methods: {
    GetDetailInfo: function () {
      licenseApi
        .getDetailLicense(this.licenseId)
        .then((response) => {
          //   console.log(response);
          this.license = response.data;
        })
        .catch();
    },
  },
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseContent.css"></style>
