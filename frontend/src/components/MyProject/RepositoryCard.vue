<template>
    <div class="repository-card">
        <p id="repository-name">reporeporeporeporepo</p>
        <div class="back-button-area" :class="{ added: added }">
            <span
                v-if="choiced"
                class="icon back-arrow-icon material-icons"
                @click="SelectedRepoClick"
            >
                arrow_back_ios
            </span>
            <span v-else-if="added" class="icon done-icon material-icons">
                done
            </span>
            <span v-else class="icon material-icons" @click="AddRepoClick">
                add
            </span>
        </div>
        <img
            id="scm-icon"
            src="@/assets/images/github-icon.png"
            alt="gitlab-icon"
        />
    </div>
</template>
<script>
export default {
    name: "RepositoryCard",
    props: {
        index: Number,
        repository: Object,
        choiced: Boolean,
        added: Boolean,
    },
    methods: {
        SelectedRepoClick: function () {
            //선택된 레포지토리 클릭
            this.$emit("selectedRepoClick");
        },
        AddRepoClick: function () {
            this.$emit("addRepoClick");
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/RepositoryCard.css"></style>
