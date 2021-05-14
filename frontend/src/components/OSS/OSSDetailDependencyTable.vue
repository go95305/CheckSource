<template>
    <table class="info-table">
        <th class="info-table-th">
            <td class="info-table-td">VERSION</td>
            <td class="info-table-td">GROUP ID</td>
            <td class="info-table-td">ARTIFACT ID</td>
            <td class="info-table-td">PACKAGE MANAGER TYPE</td>
        </th>
        <tr class="info-table-tr">
        <!-- <tr class="info-table-tr" v-for="(value, index) in opensourceList" :key="`${index}_opensourceList`"> -->
            <td class="info-table-td">{{opensourceList.version}}</td>
            <td class="info-table-td">{{opensourceList.groupId}}</td>
            <td class="info-table-td">{{opensourceList.artifactId}}</td>
            <td class="info-table-td">{{opensourceList.packageType}}</td>
        </tr>
        <div class="info-table-no-list" v-if="opensourceList.length == 0">
            <img src="@/assets/images/sol_2.png" alt="no-list-image">
            <span>정보가 없습니다.</span>
            
        </div>
    </table>
</template>
<script>
import "@/assets/css/OSS/OSSDetailInfoTable.css";
export default {
    name: "OSSDetailDependencyTable",
    props: {
        opensourceList: Object,
    },
};
</script>
