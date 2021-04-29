<template>
  <div>
    <my-project-path :department="'마이페이지'" />
    <h1 id="mypage-profile-title">Profile</h1>
    <div id="mypage-profile-container">
      <div id="mypage-profile-content">
        <section class="mypage-section-image">
          <img
            id="mypage-profile-image"
            src="http://placehold.it/130x130"
            alt="profileImage"
          />
        </section>
        <section class="mypage-section-table">
          <table id="mypage-profile-table">
            <tbody>
              <tr>
                <td class="table-left">이름</td>
                <td class="table-right">
                  <input type="text" v-model="profile.name" required />
                </td>
              </tr>
              <tr>
                <td class="table-left">부서</td>
                <td class="table-right">
                  <select
                    name="department"
                    id="mypage-select"
                    v-model="profile.department"
                  >
                    <option value="1">금융개발부</option>
                    <option value="2">디지털개발부</option>
                    <option value="3">글로벌개발부</option>
                    <option value="4">기관개발부</option>
                    <option value="5">정보개발부</option>
                    <option value="6">ICT운영부</option>
                    <option value="7">ICT기획부</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td class="table-left">직급</td>
                <td class="table-right">
                  <select name="rank" id="mypage-select" v-model="profile.rank">
                    <option value="1">선임</option>
                    <option value="2">수석</option>
                    <option value="3">부장</option>
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
        </section>
      </div>
      <button id="submit-button" @click="ChangeProfile">확인</button>
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
        department: "2",
        rank: "1",
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
