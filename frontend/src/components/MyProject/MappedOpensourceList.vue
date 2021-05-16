<template>
	<!-- 매핑된 오픈소스 -->
	<div>
    <div class="opensource-title-div">
		<p class="opensource-title">연동된 오픈소스</p>
    </div>
		<table class="opensource-table">
      <th class="opensource-table-th">
        <td>이름</td>
        <td>주소</td>
        <td>위치</td>
        <td>라이선스</td>
        <td>저작권</td>
      </th>
      <tr class="opensource-table-tr" v-for="(item, index) in mappedList"
			:key="`${index}_mapped`" @click="goOpenSource(item.opensourceId)">
        <td>{{ item.name }}</td>
        <td>{{ item.url }}</td>
        <td>{{ item.path }}</td>
        <td  class="opensource-table-td-license"><span v-for="(licenseName, index) in item.licenseNameList"
					:key="`${index}_licenseNameList`">	{{ licenseName }}</span></td>
        <td>{{ item.copyright }}</td>
      </tr>
    </table>
    <div v-if="loading">
      <loading></loading>
    </div>
    <div v-else-if="mappedList.length == 0">
      <NoResult></NoResult>
    </div>
    <div class="opensource-pagination">
		  <pagination-remote  name="mapped" :currentPage="page" :lastPage="totalPage" @changePage="ChangePage"></pagination-remote>
    </div>
	</div>
</template>
<script>
import Loading from '../Loading/Loading.vue';
import NoResult from "@/components/MyProject/NoResult.vue"
import verifyApi from "@/api/verify.js";
import PaginationRemote from '../Pagination/PaginationRemote.vue';
export default {
  name: "MappedOpensourceList",
  components: { PaginationRemote, NoResult, Loading},
  data() {
    return {
      projectId: this.$route.query.projectId,
      gitType: this.$route.query.gitType,
      page:1,
      size:5,
      totalPage:3,
      mappedList: [],
      loading:false,
    }
  },
  created() {
     if(this.$route.query.mappedPage){
          this.page = Number(this.$route.query.mappedPage);
     };
    this.getList();
  },
  watch:{
    $route:{
      deep:true,
      handler(){
        if(this.page != this.$route.query.mappedPage){
          this.page = Number(this.$route.query.mappedPage);
          this.getList();
        }
      }
    },
  },
  methods: { 
    getList: function () {
      this.loading = true;
      this.mappedList = [];
			verifyApi.readVerifiedMappedOpenSourceList(this.page, this.gitType,this.projectId, this.size)
      .then((response) => {
				if (response.data) {
          this.loading = false;
          this.mappedList = response.data.mappedList;
          this.totalPage = response.data.totalPages;
				}
			}).catch(() => {this.loading = false;});
		},
		goOpenSource: function (id) {
			this.$router.push({
				name: "OSSDetailOpenSource",
				query: { id: id },
			});
		},
    ChangePage:function(page){
      let newQuery = Object.assign({}, this.$route.query);
      newQuery.mappedPage = page;
      this.$router.push({ 
				query: newQuery,
			});
    },
  },
};
</script>
