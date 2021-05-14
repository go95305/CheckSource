<template>
  <table class="detail-table">
    <tr class="detail-table-tr">
      <td class="detail-table-td-head">NAME</td>
      <td class="detail-table-td-body">{{ opensource.name }}</td>
    </tr>

    <tr class="detail-table-tr">
      <td class="detail-table-td-head">LICENSE</td>
      <td class="detail-table-td-body">
        <span
          v-for="(licenseName, index) in opensource.licenseList"
          :key="`${index}_licenseList`"
          >{{ licenseName.name }}</span
        >
      </td>
    </tr>
    <tr class="detail-table-tr">
      <td class="detail-table-td-head">COPYRIGHT</td>
      <td class="detail-table-td-body">{{ opensource.copyright }}</td>
    </tr>
    <tr class="detail-table-tr">
      <td class="detail-table-td-head">URL</td>
      <td class="detail-table-td-body">
        {{ opensource.url }}
      </td>
    </tr>
  </table>
</template>
<script>
import '@/assets/css/OSS/OSSDetailTable.css';
export default {
  name: 'OSSDetailOpenSourceTable',
  props: {
    opensource: Object,
  },
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailOpenSourceTable.css"></style>
