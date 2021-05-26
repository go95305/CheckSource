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
  props: {
    informationData: Object,
  },
  data() {
    return {
      licenseId: this.$route.query.id,
      informationList: [
        {
          name: "OSI 승인여부",
          value: "this.informationData.is_osi",
        },
        {
          name: "저작권 고지 여부",
          value: "this.informationData.is_copyright",
        },
        {
          name: "라이선스 사본 첨부 여부",
          value: "this.informationData.is_license",
        },
        {
          name: "수정 가능 여부",
          value: "this.informationData.is_modify",
        },
        {
          name: "타 라이선스 배포 허용",
          value: "this.informationData.is_anotherlicense",
        },
        {
          name: "수정여부 고지 의무",
          value: "this.informationData.is_modify_notice",
        },
        {
          name: "책임의 제한",
          value: "this.informationData.is_respon",
        },
        {
          name: "보증의 부인",
          value: "this.informationData.is_warranty",
        },
      ],
    };
  },
  methods: {
    getValue: function (variableName) {
      return eval(variableName);
    },
  },
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseInformation.css"></style>
