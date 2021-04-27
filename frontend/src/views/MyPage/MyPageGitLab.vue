<template>
    <div id="mypage-gitlab">
        <h2 id="mypage-gitlab-current-title">현재 연동된 계정</h2>
        <p id="mypage-gitlab-current-account">checksource@gitlab.com</p>

        <button id="mypage-gitlab-button" @click="ToggleNewID">
            <i class="material-icons"> swap_horiz </i>
            <span>다른 계정으로 연동하기</span>
        </button>
        <div id="mypage-gitlab-new-id" :class="{ open: openNewID }">
            <label for="mypage-gitlab-id-input">GitLab ID</label>
            <input id="mypage-gitlab-id-input" type="text" />
            <button>연동하기</button>
        </div>
    </div>
</template>
<script>
export default {
    name: "MyPageGitLab",
    data() {
        return {
            openNewID: false,
        };
    },
    methods: {
        ToggleNewID: function () {
            this.openNewID = !this.openNewID;
        },
    },
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageGitLab.css"></style>
