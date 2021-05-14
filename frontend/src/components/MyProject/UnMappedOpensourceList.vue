<template>
	<!-- 매핑되지 않은 오픈소스 -->
	<div>
    <div class="opensource-title-div">
      <p class="opensource-title">연동되지 않은 오픈소스</p>
      <router-link :to="{name:'AddOpenSource'}" class="opensource-add-button"><span class="material-icons"> add </span> 오픈소스 추가 </router-link> 
    </div>
    <table class="opensource-table">
      <th class="opensource-table-th">
        <td>아티팩트ID</td>
        <td>그룹ID</td>
        <td>위치</td>
        <td>버전</td>
        <td></td>
      </th>
      <tr class="opensource-table-tr" v-for="(item, index) in unmappedList"
			:key="`${index}_mapped`" @click="goOpenSource(item.opensourceId)">
        <td>{{ item.artifactId }}</td>
        <td>{{ item.groupId }}</td>
        <td>{{ item.path }}</td>
        <td>{{ item.version }}</td>
        <td><span class="material-icons"> add </span></td>
      </tr>
    </table>
    <div v-if="loading">
      <loading></loading>
    </div>
    <div v-else-if="unmappedList.length == 0">
      <NoResult></NoResult>
    </div>
    <div class="license-pagination">
		  <pagination-remote  :lastPage="3"></pagination-remote>
    </div>
	</div>
</template>
<script>
import NoResult from "@/components/MyProject/NoResult.vue"
import verifyApi from "@/api/verify.js";
import PaginationRemote from '../Pagination/PaginationRemote.vue';
import Loading from '../Loading/Loading.vue';
export default {
  name: "MappedOpensourceList",
  components: { PaginationRemote, NoResult, Loading },
  data() {
    return {
      projectId: this.$route.query.projectId,
      unmappedList: [],
      loading:false,
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList: function () {
      this.loading = true;
			verifyApi.readVerifiedOpenSourceList(this.projectId).then((response) => {
				if (response.data) {
          this.loading = false;
					this.unmappedList = response.data.unmappedList;
				}
			}).catch(() => {
        this.loading = false;
      });
		},
		goOpenSource: function (id) {
			this.$router.push({
				name: "OSSDetailOpenSource",
				query: { id: id },
			});
		},
  },
};
</script>
