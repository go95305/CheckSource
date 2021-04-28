<template>
    <div id="mypage-gitlab-container">
        <!-- 현재 연동된 계정 정보 -->
        <div id="mypage-gitlab-current" v-if="isCurrentAccount">
            <span id="mypage-gitlab-current-title">현재 연동된 계정</span>
            <span id="mypage-gitlab-current-account">{{ currentAccount }}</span>
        </div>

        <div
            id="mypage-gitlab-new-account"
            :class="{ open: openNewAccount || !isCurrentAccount }"
        >
            <label
                id="mypage-gitlab-account-label"
                for="mypage-gitlab-account-input"
                >새 계정</label
            >
            <input
                id="mypage-gitlab-account-input"
                type="text"
                v-model="newAccount"
            />
        </div>

        <!-- 관리 버튼 -->
        <div id="mypage-gitlab-buttons">
            <button
                class="mypage-gitlab-button new-button"
                @click="CheckNewAccount"
            >
                <div>
                    <i class="material-icons"> {{ newAccountButtonIcon }} </i>
                    <span>{{ newAccountButtonText }}</span>
                </div>
            </button>
            <button
                v-if="isCurrentAccount"
                class="mypage-gitlab-button close-button"
                @click="CloseAccout"
            >
                <div>
                    <i class="material-icons"> close </i>
                    <span>연동끊기</span>
                </div>
            </button>
        </div>
    </div>
</template>
<script>
export default {
    name: "MyPageGitLab",
    data() {
        return {
            isCurrentAccount: true,
            currentAccount: "checksource@gitlab.com",
            openNewAccount: false,
            newAccount: "",
            newAccountButtonIcon: "swap_horiz",
            newAccountButtonText: "다른 계정으로 연동하기",
        };
    },
    watch: {
        newAccount: function () {
            this.CheckNewAccountButton();
        },
        openNewAccount: function () {
            if (!this.openNewAccount) {
                this.newAccount = "";
            }
        },
    },
    created() {
        this.CheckNewAccountButton();
    },
    methods: {
        CheckNewAccountButton: function () {
            //새 연동 버튼 상태 처리
            if (!this.isCurrentAccount || this.newAccount.length > 0) {
                this.newAccountButtonIcon = "check";
                this.newAccountButtonText = "연동하기";
            } else {
                this.newAccountButtonIcon = "swap_horiz";
                this.newAccountButtonText = "다른 계정으로 연동하기";
            }
        },
        CheckNewAccount: function () {
            //새 계정 입력에 따른 연산
            if (!this.openNewAccount || this.newAccount.length == 0) {
                this.openNewAccount = !this.openNewAccount;
            } else {
                //변경시도
                alert("변경시도");
            }
        },
        CloseAccout: function () {
            //연결끊기 시도
            this.isCurrentAccount = false;
        },
    },
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageGitLab.css"></style>
