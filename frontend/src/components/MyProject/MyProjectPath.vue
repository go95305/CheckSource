<template>
    <div>
        <span
            id="my-project-title-department"
            :class="MoveEffect"
            @click="GoMyProject"
            >{{ department }}</span
        >
        <span v-if="project.length > 0">
            <span class="color-gray"> / </span>
            <span>{{ project }}</span>
        </span>
    </div>
</template>
<script>
export default {
    name: "MyProjectTitle",
    props: {
        department: String,
        project: {
            type: String,
            default: "",
        },
    },
    computed: {
        MoveEffect: function () {
            return {
                "my-project-move": this.project.length > 0,
                "color-gray": this.project.length > 0,
            };
        },
    },
    methods: {
        GoMyProject: function () {
            if (this.project.length > 0) {
                //프로젝트 명 있을 때
                this.$router.push("/project/main/status");
            }
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/MyProjectPath.css"></style>
