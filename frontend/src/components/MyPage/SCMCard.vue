<template>
    <div>
        <div class="mypage-scm-card-background">
            <div class="mypage-scm-card-div">
                <span>{{ getBaseUrl(account.gitlabId) }}</span>
                <span>{{ account.username }}</span>
                <img
                    class="mypage-scm-card-icon"
                    src="@/assets/images/gitlab-icon.png"
                    alt="gitlab-icon"
                />
            </div>
            <span
                class="mypage-scm-card-close-button material-icons"
                @click="deleteSCMCard"
            >
                close
            </span>
        </div>
    </div>
</template>
<script>
import gitLabApi from "@/api/gitlab.js";
import "@/assets/css/MyPage/SCMCard.css";
export default {
    name: "SCMCard",
    props: {
        account: Object,
    },
    methods: {
        getBaseUrl: function (index) {
            return gitLabApi.getBaseUrl(index - 1);
        },
        deleteSCMCard: function () {
            this.$emit("deleteSCM", this.account.gitlabId);
        },
    },
};
</script>
