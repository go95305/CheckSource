<template>
    <div class="repository-card">
        <div v-if="verified" class="verified"></div>
        <p id="repository-name">{{ repository.name }}</p>
        <div
            class="button-area"
            :class="{ checked: checked }"
            @click="CardClick"
        >
            <span v-if="selected" class="icon back-arrow-icon material-icons">
                arrow_back_ios
            </span>
            <span v-else-if="checked" class="icon done-icon material-icons">
                done
            </span>
            <span v-else class="icon material-icons"> add </span>
        </div>
        <img
            v-if="repository.gittype == 1"
            id="scm-icon"
            src="@/assets/images/github-icon.png"
            alt="gitlab-icon"
        />
        <img
            v-else-if="repository.gittype == 2"
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
        //레포지토리 정보
        repository: Object,

        //selected칸에 포함된 카드
        selected: Boolean,

        //형상관리칸에 있는 카드 중 selected된 카드
        checked: {
            type: Boolean,
            default: false,
        },

        //이미 검증된 레포지토리
        verified: {
            type: Boolean,
            default: false,
        },
    },
    methods: {
        CardClick: function () {
            if (this.selected) {
                //선택된 레포지토리 제거
                this.$emit("selectedRepoClick", this.repository.project_id);
            } else if (!this.checked) {
                //레포지토리 추가
                this.$emit("addRepoClick", this.repository);
            }
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/RepositoryCard.css"></style>
