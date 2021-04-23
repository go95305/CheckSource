<template>
    <div>
        <div id="my-page">
            <h1 id="my-page-title">마이페이지</h1>
            <tab id="my-page-tab" :list="tabList" />
            <router-view />
        </div>
    </div>
</template>
<script>
import Tab from "@/components/Tab/Tab.vue";
export default {
    name: "MyPage",
    components: {
        Tab,
    },
    data() {
        return {
            tabList: [
                {
                    name: "Profile",
                    path: "/mypage/profile",
                },
                {
                    name: "SCM",
                    path: "/mypage/scm",
                },
            ],
        };
    },
};
</script>
<style scoped src="@/assets/css/MyPage/MyPage.css"></style>
