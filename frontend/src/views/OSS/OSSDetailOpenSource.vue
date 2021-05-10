<template>
    <div>
        <my-project-path
            :department="'전체목록'"
            :project="opensource.name"
            rootPath="/list/opensource"
        />
        <h1 class="oss-detail-title">{{ opensource.name }}</h1>
        <div class="oss-detail-table">
            <OSSDetailOpenSourceTable
                :opensource="opensource"
            ></OSSDetailOpenSourceTable>
        </div>
        <h3 class="oss-detail-info-title">Dependency</h3>
        <div>
            <OSSDetailDependencyTable
                :opensourceList="opensourceList"
            ></OSSDetailDependencyTable>
        </div>
    </div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import OSSDetailOpenSourceTable from "@/components/OSS/OSSDetailOpenSourceTable.vue";
import OSSDetailDependencyTable from "../../components/OSS/OSSDetailDependencyTable.vue";
export default {
    name: "OSSDetailOpenSource",
    components: {
        MyProjectPath,
        OSSDetailOpenSourceTable,
        OSSDetailDependencyTable,
    },
    data() {
        return {
            id: this.$route.query.id,
            opensource: {
                id: "",
                name: "오픈소스 이름",
            },
            opensourceList: [
                // {
                //     version: "000",
                //     group_id: "groupid",
                //     artifact_id: "artifactID",
                //     packagetype: "packageType",
                // },
                // {
                //     version: "000",
                //     group_id: "groupid",
                //     artifact_id: "artifactID",
                //     packagetype: "packageType",
                // },
            ],
        };
    },
};
</script>
