<template>
    <div>
        <my-project-path
            :department="'전체목록'"
            :project="license.name"
            rootPath="/list/license"
        />
        <h1 class="oss-detail-title">{{ license.name }}</h1>
        <tab :list="tabList"></tab>
        <router-view></router-view>
    </div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import Tab from "@/components/Tab/Tab.vue";
export default {
    name: "OSSDetailLicense",
    components: {
        MyProjectPath,
        Tab,
    },
    data() {
        return {
            id: this.$route.query.id,
            license: {
                id: "",
                name: "라이선스 이름",
            },
            tabList: [
                {
                    name: "Content",
                    path: "/list/detail/license/content",
                },
            ],
        };
    },
};
</script>
