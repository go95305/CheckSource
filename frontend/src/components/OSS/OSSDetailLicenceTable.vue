<template>
  <table class="detail-table">
    <tr class="detail-table-tr">
      <td class="detail-table-td-head">FULL NAME</td>
      <td class="detail-table-td-body">{{ license.name }}</td>
    </tr>

    <tr class="detail-table-tr">
      <td class="detail-table-td-head">IDENTIFIER</td>
      <td class="detail-table-td-body">{{ license.identifier }}</td>
    </tr>
    <tr class="detail-table-tr">
      <td class="detail-table-td-head">CODE OPEN</td>
      <td class="detail-table-td-body" v-if="license.sourceopen.length != 0">
        {{ license.sourceopen }}
      </td>
      <td class="detail-table-td-body" v-else>
        <span class="material-icons"> close </span>
      </td>
      <!-- <td class="detail-table-td-body">
        {{ license.sourceopen }}
        <span class="material-icons"> done </span>
      </td> -->
    </tr>
    <tr class="detail-table-tr">
      <td class="detail-table-td-head">URL</td>
      <td class="detail-table-td-body">{{ license.url }}</td>
    </tr>
  </table>
</template>
<script>
import '@/assets/css/OSS/OSSDetailTable.css';
export default {
  name: 'OSSDetailLicenseTable',
  props: {
    license: Object,
  },
};
</script>
<style scoped src="@/assets/css/OSS/OSSDetailLicenseTable.css"></style>
