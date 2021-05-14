<template>

	<div class="container">

		<table class="license-table">
      <th class="license-table-th">
        <td>이름</td>
        <td>식별자</td>
        <td>주소</td>
        <td>코드 공개</td>
      </th>
      <tr class="license-table-tr" v-for="(item, index) in mappedList"
			:key="`${index}_mapped`" @click="goLicense(item.licenseId)" :class="{ illegal: item.sourceopen.length > 0 }">
        <td>{{ item.name }}</td>
        <td>{{ item.identifier }}</td>
        <td>{{ item.path }}</td>
        <td v-if="item.sourceopen.length > 0">
					{{ item.sourceopen }}
				</td>
				<td class="col col-3" v-else>
					<span class="material-icons"> close </span>
				</td>
      </tr>
    </table>
    <div class="license-pagination">
    <pagination-remote :lastPage="3"></pagination-remote>
    </div>
	</div>
</template>
<script>
import verifyApi from "@/api/verify.js";
import PaginationRemote from '@/components/Pagination/PaginationRemote.vue';
export default {
  name:"LicenseList",
  components: { PaginationRemote },
	data() {
		return {
			projectId: this.$route.query.projectId,
			list: [],
		};
	},
	created() {
		this.getList();
	},
	methods: {
		getList: function () {
			verifyApi.readVerifiedLicenseList(this.projectId).then((response) => {
				if (response.data) this.list = response.data;
			});
		},
    goLicense: function (id) {
			this.$router.push({
				name: "OSSDetailLicense",
				query: { id: id },
			});
		},
	},
};
</script>
<style scoped src="@/assets/css/MyProject/LicenseList.css"></style>
