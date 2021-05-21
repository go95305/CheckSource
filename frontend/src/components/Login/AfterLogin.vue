<template>
  <div class="background">
    <div class="login-form-container shadow">
      <div class="login-form-right-side-after">
        <div class="top-logo-wrap"></div>
        <!-- <img id="right-side-logo" src="@/assets/shinhan.png" /> -->
        <h2>반갑습니다.</h2>
        <p>처음 사용하시는 사원은 개인 정보를 입력해주세요.</p>
      </div>
      <div class="login-form-left-side">
        <div class="login-top-wrap"></div>
        <div class="login-input-container">
          <div class="title-name">
            <h3>이름</h3>
          </div>
          <div class="login-input-wrap input-name">
            <input placeholder="성명" type="text" v-model="profile.name" />
          </div>
          <div class="title-name">
            <h3>부서</h3>
          </div>
          <div class="login-input-wrap input-department">
            <label class="custom-select" for="styledSelect1"
              ><select
                id="styledSelect1"
                name="options"
                v-model="profile.depart"
              >
                <option
                  v-for="(value, index) of departList"
                  :key="`${index}_departList`"
                  :value="index + 1"
                >
                  {{ value }}
                </option>
              </select></label
            >
          </div>
          <div class="title-name">
            <h3>직급</h3>
          </div>
          <div class="login-input-wrap input-department">
            <label class="custom-select" for="styledSelect1"
              ><select id="styledSelect1" name="options" v-model="profile.job">
                <option
                  v-for="(value, index) of jobList"
                  :key="`${index}_jobList`"
                  :value="index + 1"
                >
                  {{ value }}
                </option>
              </select></label
            >
          </div>
        </div>
        <div class="login-btn-wrap">
          <button class="login-btn" @click="InsertUserForm">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import Info from "@/api/info.js";

export default {
  name: "AfterLogin",
  data() {
    return {
      profile: {
        name: "",
        depart: 1,
        job: 1,
      },
      jobList: [],
      departList: [],
    };
  },
  computed: {
    ...mapGetters(["getUserId"]),
  },
  created() {
    this.jobList = Info.GetJobList();
    this.departList = Info.GetDepartmentList();
  },
  methods: {
    InsertUserForm: function () {
      console.log(this.profile);
      this.profile.userId = this.getUserId;
      this.$store.dispatch("USERFORM", this.profile);
    },
  },
};
</script>
<style scoped src="../../assets/css/Login/AfterLogin.css"></style>
