<template>
  <div>
    <my-project-path
      :department="'전체목록'"
      :project="opensource.name"
      rootPath="/list/opensource"
    />
    <h1 class="oss-detail-title">{{ opensource.name }}</h1>
    <div class="oss-detail-table">
      <OSSDetailOpenSourceTable
        :opensource="opensource"
      ></OSSDetailOpenSourceTable>
    </div>
    <h3 class="oss-detail-info-title">Dependency</h3>
    <div>
      <OSSDetailDependencyTable
        :opensourceList="opensourceList"
      ></OSSDetailDependencyTable>
    </div>
  </div>
</template>
<script>
import MyProjectPath from '@/components/MyProject/MyProjectPath.vue';
import OSSDetailOpenSourceTable from '@/components/OSS/OSSDetailOpenSourceTable.vue';
import OSSDetailDependencyTable from '../../components/OSS/OSSDetailDependencyTable.vue';
import opensourceApi from '@/api/opensource.js';

export default {
  name: 'OSSDetailOpenSource',
  components: {
    MyProjectPath,
    OSSDetailOpenSourceTable,
    OSSDetailDependencyTable,
  },
  data() {
    return {
      opensourceId: this.$route.query.id,
      opensource: {
        id: '',
        // name: '오픈소스 이름',
        name: '',
        copyright: '',
        url: '',
      },
      //   opensourceList: [
      // {
      //     version: "000",
      //     group_id: "groupid",
      //     artifact_id: "artifactID",
      //     packagetype: "packageType",
      // },
      // {
      //     version: "000",
      //     group_id: "groupid",
      //     artifact_id: "artifactID",
      //     packagetype: "packageType",
      // },
      //   ],
      opensourceList: {
        version: '',
        groupId: '',
        artifactId: '',
        packageType: '',
      },
    };
  },
  created() {
    this.GetDetailInfo();
  },
  methods: {
    GetDetailInfo: function () {
      opensourceApi
        .getDetailOpensource(this.opensourceId)
        .then((response) => {
          //   console.log(response);
          this.opensource.list = response.data.licenseList;
          this.opensource = response.data;
          this.opensourceList = response.data;
          //   this.openSourceList = response.data.licenseList;
          console.log(this.opensource.list);
        })
        .catch();
    },
  },
};
</script>
