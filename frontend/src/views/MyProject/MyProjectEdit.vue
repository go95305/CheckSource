<template>
    <div>
        <my-project-path :department="'프로젝트'" :project="'Edit'" />
        <h1 id="myproject-edit-title">Edit</h1>
        <article id="myproject-edit-article">
            <section id="myproject-edit-selected-section">
                <div id="myproject-edit-divider"></div>
                <my-project-edit-selected></my-project-edit-selected>
            </section>
            <section id="myproject-edit-tab-section">
                <tab id="myproject-edit-tab" :list="tabList" />
                <router-view id="myproject-edit-routerview"></router-view>
            </section>
        </article>
    </div>
</template>
<script>
import MyProjectEditSelected from "../../components/MyProject/MyProjectEditSelected.vue";
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
import Tab from "../../components/Tab/Tab.vue";
export default {
    name: "MyProjectEdit",
    components: { MyProjectPath, Tab, MyProjectEditSelected },
    data() {
        return {
            tabList: [
                {
                    name: "GitLab",
                    path: "/project/main/edit/gitlab",
                },
            ],
        };
    },
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEdit.css"></style>
