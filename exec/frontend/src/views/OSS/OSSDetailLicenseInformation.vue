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
  name: 'OSSDetailLicenseInformation',
  props: {
    informationData: Object,
  },
  data() {
    return {
      licenseId: this.$route.query.id,
      informationList: [
        {
          name: 'OSI 승인여부',
          value: 'this.informationData.is_osi',
        },
        {
          name: 'Copyright 고지 여부',
          value: 'this.informationData.is_copyright',
        },
        {
          name: '라이선스 고지 여부',
          value: 'this.informationData.is_license',
        },
        // {
        //   name: '상업적 사용 가능 여부',
        //   value: 'this.informationData.is_commercial',
        // },
        {
          name: '수정 가능 여부',
          value: 'this.informationData.is_modify',
        },
        // {
        //   name: '비공개 사용 가능 여부',
        //   value: 'this.informationData.is_private',
        // },
        // {
        //   name: '특허권 사용 여부',
        //   value: 'this.informationData.is_patent',
        // },
        {
          name: '책임 여부',
          value: 'this.informationData.is_respon',
        },
        {
          name: '보증 여부',
          value: 'this.informationData.is_warranty',
        },
        // {
        //   name: '동일한 라이선스 유지 여부',
        //   value: 'this.informationData.is_continue',
        // },
        {
          name: '배포 가능 여부',
          value: 'this.informationData.is_distribute',
        },
        {
          name: '상태 변경 시 문서화 여부',
          value: 'this.informationData.is_status',
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
