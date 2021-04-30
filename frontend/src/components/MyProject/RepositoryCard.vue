<template>
    <div class="repository-card">
        <p id="repository-name">{{ repository.name }}</p>
        <div class="back-button-area">
            <span
                v-if="choiced"
                class="back-arrow-icon material-icons"
                @click="SelectedRepoClick"
            >
                arrow_back_ios
            </span>
            <span
                v-else
                class="add-arrow-icon material-icons"
                @click="AddRepoClick"
            >
                add
            </span>
        </div>
        <img
            id="scm-icon"
            src="@/assets/images/gitlab-icon.png"
            alt="gitlab-icon"
        />
    </div>
</template>
<script>
export default {
    name: "RepositoryCard",
    props: {
        repository: Object,
        choiced: Boolean,
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
