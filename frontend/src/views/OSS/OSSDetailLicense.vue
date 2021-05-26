<template>
  <div>
    <my-project-path
      :department="'전체목록'"
      :project="license.name"
      rootPath="/list/license"
    />
    <h1 class="oss-detail-title">{{ license.name }}</h1>
    <div class="oss-detail-table">
      <router-link
        v-if="license.userName != '관리자'"
        class="oss-detail-edit-routerlink"
        :to="{ name: 'EditLicense', params: { editLicense: license } }"
        ><span class="material-icons">edit</span>수정하기</router-link
      >
      <OSSDetailLicenceTable :license="license"></OSSDetailLicenceTable>
    </div>

    <tab class="oss-detail-info-title" :list="tabList"></tab>
    <router-view :informationData="license"></router-view>
  </div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import Tab from "@/components/Tab/Tab.vue";
import OSSDetailLicenceTable from "@/components/OSS/OSSDetailLicenceTable.vue";
import licenseApi from "@/api/license.js";

export default {
  name: "OSSDetailLicense",
  components: {
    MyProjectPath,
    Tab,
    OSSDetailLicenceTable,
  },
  data() {
    return {
      licenseId: this.$route.query.id,
      license: {
        id: "",
        name: "",
        identifier: "",
        sourceopen: "",
        url: "",
        userName: "관리자",
      },
      tabList: [
        {
          name: "라이선스 정보",
          path: "/list/detail/license/information",
        },
        {
          name: "라이선스 전문",
          path: "/list/detail/license/content",
        },
      ],
    };
  },
  created() {
    this.GetLicenseDetail();
  },
  methods: {
    GetLicenseDetail: function () {
      licenseApi
        .getDetailLicense(this.licenseId)
        .then((response) => {
          this.license = response.data;
          console.log(this.license);
        })
        .catch();
    },
  },
};
</script>
<style></style>
