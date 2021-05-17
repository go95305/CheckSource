<template>
	<!-- 매핑되지 않은 오픈소스 -->
	<div>
    <div class="opensource-title-div">
      <p class="opensource-title">연동되지 않은 오픈소스</p>
      <router-link :to="{name:'AddOpenSource', query:this.$route.query}" class="opensource-add-button"><span class="material-icons"> add </span> 오픈소스 추가 </router-link> 
    </div>
    <table class="opensource-table">
      <th class="opensource-table-th">
        <td>아티팩트ID</td>
        <td>그룹ID</td>
        <td>위치</td>
        <td>버전</td>
        <td class="opensource-table-unmapped-add"></td>
      </th>
      <tr class="opensource-table-tr" v-for="(item, index) in unmappedList"
			:key="`${index}_mapped`" @click="GoAddOpensource(item)">
        <td>{{ item.artifactId }}</td>
        <td>{{ item.groupId }}</td>
        <td>{{ item.path }}</td>
        <td>{{ item.version }}</td>
        <td class="opensource-table-unmapped-add"><span class="material-icons"> add </span></td>
      </tr>
    </table>
    <div v-if="unMappedLoading">
      <loading></loading>
    </div>
    <div v-else-if="unmappedList.length == 0">
      <NoResult></NoResult>
    </div>
    <div class="opensource-pagination">
		  <pagination-remote name="unmapped" :currentPage="page" :lastPage="totalPage" @changePage="ChangePage"></pagination-remote>
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
      gitType: this.$route.query.gitType,
      page:1,
      size:5,
      totalPage:3,
      unmappedList: [],
      unMappedLoading:false,
    }
  },
  created() {
     if(this.$route.query.unmappedPage){
          this.page = Number(this.$route.query.unmappedPage);
     };
    this.GetList();
  },
  watch:{
    $route:{
      deep:true,
      handler(){
        if(this.$route.query.unmappedPage && this.page != this.$route.query.unmappedPage){
          this.page = Number(this.$route.query.unmappedPage);
          this.GetList();
        }else if(!this.$route.query.unmappedPage){
          this.page = 1;
           this.GetList();
        }
      }
    },
  },
  methods: {
    GetList: function () {
      this.unMappedLoading = true;
      this.unmappedList = [];
			verifyApi.readVerifiedUnmappedOpenSourceList(this.page,this.gitType,this.projectId,this.size).then((response) => {
				if (response.data) {
          this.unMappedLoading = false;
					this.unmappedList = response.data.unmappedList;
          console.log(this.unmappedList);
          this.totalPage = response.data.totalPages;
				}
			}).catch(() => {
        this.unMappedLoading = false;
      });
		},
		GoAddOpensource:function(opensource) {
      console.log(opensource.artifactId);
      let unmappedOpensource = {artifactId:opensource.artifactId, groupId:opensource.groupId, version:opensource.version};
			this.$router.push({
				name: "AddOpenSource",
        params: {unmappedOpensource:unmappedOpensource},
			});
		},
    ChangePage:function(page){
      let newQuery = Object.assign({}, this.$route.query);
      newQuery.unmappedPage = page;
      this.$router.push({ 
				query: newQuery,
			})
    },
  },
};
</script>