<template>

	<div class="container">
	  <table class="license-table">
      <th class="license-table-th">
        <td>이름</td>
        <td>식별자</td>
        <td>주소</td>
        <td>코드 공개</td>
      </th>
      <tr class="license-table-tr" v-for="(item, index) in list"
			:key="`${index}_mapped`" :class="{illegal: item.sourceopen.length > 0, noComment: item.sourceopen == '명시X'}" @click="GoLicense(item.licenseId)">
        <td>{{ item.name }}</td>
        <td>{{ item.identifier }}</td>
        <td>{{ item.url }}</td>
        <td v-if="item.sourceopen.length > 0">
					{{ item.sourceopen }}
				</td>
				<td class="col col-3" v-else>
					<span class="material-icons"> close </span>
				</td>
      </tr>
    </table>
	<div v-if="loading">
      <loading></loading>
    </div>
	<div v-else-if="list.length == 0">
		<NoResult></NoResult>
	</div>
    <div class="license-pagination">
		<pagination-remote :currentPage="page"
				:lastPage="totalPage"
				@changePage="ChangePage"
        ></pagination-remote>
    </div>
	</div>
</template>
<script>
import Loading from "@/components/Loading/Loading.vue";
import NoResult from "@/components/MyProject/NoResult.vue";
import verifyApi from "@/api/verify.js";
import PaginationRemote from "@/components/Pagination/PaginationRemote.vue";
export default {
  name: "LicenseList",
  components: { PaginationRemote, NoResult, Loading },
  data() {
    return {
      projectId: this.$route.query.projectId,
      gitType: this.$route.query.gitType,
      list: [],
      page:1,
      size: 5,
      totalPage:3,
      loading: false,
    };
  },
  created() {
     if(this.$route.query.page){
          this.page = Number(this.$route.query.page);
     };
    this.GetList();
  },
  watch:{
    $route:{
      deep:true,
      handler(){
        if(this.$route.query.page && this.page != this.$route.query.page){
          
          this.page = Number(this.$route.query.page);
          this.GetList();
        }
        else if(!this.$route.query.page){
          this.page = 1;
          this.GetList();
        }
      }
    },
  },
  methods: {
    GetList: function () {
      this.loading = true;
      verifyApi
        .readVerifiedLicenseList(this.gitType,this.page, this.size,this.projectId)
        .then((response) => {
          if (response.data) {
            this.list = response.data.licenseList; 
            this.totalPage =response.data.totalPages; 
          }
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
        });
    },
    GoLicense: function (id) {
      this.$router.push({
        name: "OSSDetailLicenseInformation",
        query: { id: id },
      });
    },
    ChangePage: function (page) {
      let newQuery = Object.assign({}, this.$route.query);
      newQuery.page = page;
      this.$router.push({ 
				query: newQuery,
			})
		},
  },
};
</script>
<style scoped src="@/assets/css/MyProject/LicenseList.css"></style>
