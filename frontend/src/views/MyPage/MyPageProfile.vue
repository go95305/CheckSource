<template>
    <div>
        <my-project-path :department="'마이페이지'" />
        <h1 id="mypage-profile-title">Profile</h1>
        <div id="mypage-profile-container">
            <img
                id="mypage-profile-image"
                src="http://placehold.it/130x130"
                alt="profileImage"
            />
            <form @submit.prevent="ChangeProfile">
                <table>
                    <tbody>
                        <tr>
                            <td class="table-left">이름</td>
                            <td class="table-right">
                                <input
                                    type="text"
                                    v-model="profile.name"
                                    required
                                />
                            </td>
                        </tr>
                        <tr>
                            <td class="table-left">부서</td>
                            <td class="table-right">
                                <input
                                    type="text"
                                    v-model="profile.department"
                                    required
                                />
                            </td>
                        </tr>
                        <tr>
                            <td class="table-left">직급</td>
                            <td class="table-right">
                                <input
                                    type="text"
                                    v-model="profile.rank"
                                    required
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input id="submit-button" type="submit" value="확인" />
            </form>
        </div>
    </div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
export default {
    name: "MyPageProfile",
    components: { MyProjectPath },
    data() {
        return {
            profile: {
                name: "김신한",
                department: "ICT운영부",
                rank: "선임",
            },
        };
    },
    methods: {
        ChangeProfile: function () {
            alert("프로필 수정");
        },
    },
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageProfile.css"></style>
