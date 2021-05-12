<template>
    <div id="mypage-gitlab-container">
        <!-- 현재 연동된 계정 정보 -->
        <div v-if="gitlab.length > 0" class="mypage-scm-account-background">
            <h3 class="mypage-gitlab-title">현재 연동된 계정</h3>
            <SCMCard
                v-for="(account, index) in gitlab"
                :key="`${index}_accountList`"
                :account="account"
                @deleteSCM="DeleteAccount"
            ></SCMCard>
        </div>

        <!-- 새로 입력할 계정 정보 -->
        <div id="mypage-gitlab-new-account" :class="{ open: openInputArea }">
            <h3 class="mypage-gitlab-title">새로 연동할 계정 정보</h3>
            <label class="mypage-gitlab-label" for="mypage-gitlab-baseurl"
                >BaseURL</label
            >
            <select
                v-model="newGitLabAccount.gitlabId"
                name="gitlab-baseurl"
                id="mypage-gitlab-baseurl"
                class="mypage-gitlab-input"
            >
                <option
                    v-for="(baseUrl, index) in baseUrlList"
                    :key="`${index}_baseUrlList`"
                    :value="index + 1"
                >
                    {{ baseUrl }}
                </option>
            </select>
            <label class="mypage-gitlab-label" for="mypage-gitlab-account-input"
                >UserName</label
            >
            <input
                id="mypage-gitlab-account-input"
                class="mypage-gitlab-input"
                type="text"
                v-model="newGitLabAccount.username"
            />
        </div>

        <!-- 관리 버튼 -->
        <div id="mypage-gitlab-buttons">
            <button
                v-if="newGitLabAccount.username.length > 0 && openInputArea"
                class="mypage-gitlab-button new-button"
                @click="CreateAccount"
            >
                <div>
                    <i class="material-icons"> check </i>
                    <span>연동하기</span>
                </div></button
            ><button
                v-else-if="
                    newGitLabAccount.username.length == 0 && openInputArea
                "
                class="mypage-gitlab-button new-button"
                @click="OpenAndCloseInputArea"
            >
                <div>
                    <i class="material-icons"> close </i>
                    <span>창 닫기</span>
                </div>
            </button>
            <button
                v-else
                class="mypage-gitlab-button new-button"
                @click="OpenAndCloseInputArea"
            >
                <div>
                    <i class="material-icons"> add </i>
                    <span>계정추가</span>
                </div>
            </button>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import gitLabApi from "@/api/gitlab.js";
import SCMCard from "@/components/MyPage/SCMCard.vue";
export default {
    name: "MyPageGitLab",
    components: { SCMCard },
    data() {
        return {
            openInputArea: false,
            newGitLabAccount: {
                gitlabId: 1,
                username: "",
            },
            gitlab: [],
            baseUrlList: [],
        };
    },
    watch: {
        openInputArea: function () {
            if (!this.openInputArea) {
                //계정 추가 영역 열 때 비우기
                this.newGitLabAccount.username = "";
                this.newGitLabAccount.gitlabId = 0;
            }
        },
        getGitLabList: {
            deep: true,
            handler(newData) {
                this.gitlab = [];
                this.gitlab = newData;
            },
        },
    },
    created() {
        this.baseUrlList = gitLabApi.getBaseUrlList();
        if (this.gitlab.length == 0) {
            this.openInputArea = true;
        }

        this.gitlab = this.getGitLabList;
    },
    methods: {
        OpenAndCloseInputArea: function () {
            //계정 추가 영역 열고 닫기
            this.openInputArea = !this.openInputArea;
        },
        CreateAccount: function () {
            //계정 연동
            gitLabApi
                .createGitLabConnect(this.newGitLabAccount)
                .then((response) => {
                    if (response.data.flag) {
                        alert("계정이 연동되었습니다.");
                        this.$store.commit("CONNECTGITLAB", response.data);
                    } else {
                        alert("존재하지 않는 계정입니다.\n다시 확인해주세요.");
                    }
                })
                .catch(() => {
                    alert("오류가 발생했습니다.");
                });
        },
        DeleteAccount: function (gitlabId) {
            //연결끊기
            gitLabApi
                .deleteGitLabConnect(gitlabId)
                .then(() => {
                    alert("연동이 중지되었습니다.");
                    this.$store.commit("DISCONNECTGITLAB", gitlabId);
                })
                .catch(() => {
                    alert("오류가 발생했습니다.");
                });
        },
    },
    computed: {
        ...mapGetters(["getGitLabList"]),
    },
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageGitLab.css"></style>
