<template>
    <table class="detail-table">
        <tr class="detail-table-tr">
            <td class="detail-table-td-head">NAME</td>
            <td class="detail-table-td-body">이름</td>
        </tr>

        <tr class="detail-table-tr">
            <td class="detail-table-td-head">LICENSE</td>
            <td class="detail-table-td-body">라이선스</td>
        </tr>
        <tr class="detail-table-tr">
            <td class="detail-table-td-head">COPYRIGHT</td>
            <td class="detail-table-td-body">저작권</td>
        </tr>
        <tr class="detail-table-tr">
            <td class="detail-table-td-head">URL</td>
            <td class="detail-table-td-body">
                urlurlurlurlurlurlurlurlurlurlurlurlurlurlurlurlurlurl
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
