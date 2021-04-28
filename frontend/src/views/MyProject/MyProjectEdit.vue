<template>
    <div>
        <my-project-path :department="'프로젝트'" :project="'Edit'" />
        <h1 id="myproject-edit-title">Edit</h1>
    </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
export default {
    name: "MyProjectEdit",
    components: { MyProjectPath },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectEdit.css"></style>
