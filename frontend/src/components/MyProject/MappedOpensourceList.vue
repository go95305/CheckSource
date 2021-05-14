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
        <td v-for="(licenseName, index) in item.licenseNameList"
					:key="`${index}_licenseNameList`">	{{ licenseName }}</td>
        <td>{{ item.copyright }}</td>
      </tr>
    </table>
    <div class="opensource-pagination">
    <pagination-remote :lastPage="3"></pagination-remote>
    </div>
	</div>
</template>
<script>
import verifyApi from "@/api/verify.js";
import PaginationRemote from '../Pagination/PaginationRemote.vue';
export default {
  name: "MappedOpensourceList",
  components: { PaginationRemote },
  data() {
    return {
      projectId: this.$route.query.projectId,
      mappedList: [],
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList: function () {
			verifyApi.readVerifiedOpenSourceList(this.projectId).then((response) => {
				if (response.data) {
          this.mappedList = response.data.mappedList;
					console.log(this.mappedList);
					// this.unmappedList = response.data.unmappedList;
				}
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
