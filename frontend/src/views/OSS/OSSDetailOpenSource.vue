<template>
	<div>
		<my-project-path
			:department="'전체목록'"
			:project="opensource.name"
			rootPath="/list/opensource"
		/>
		<h1 class="oss-detail-title">{{ opensource.name }}</h1>

		<div class="oss-detail-table">
			<router-link
				v-if="opensource.userName != '관리자'"
				class="oss-detail-edit-routerlink"
				:to="{ name: 'EditOpenSource', params: { editOpensource: opensource } }"
				><span class="material-icons">edit</span>수정하기</router-link
			>
			<OSSDetailOpenSourceTable
				:opensource="opensource"
			></OSSDetailOpenSourceTable>
		</div>
		<h3 class="oss-detail-info-title">Dependency</h3>
		<div>
			<OSSDetailDependencyTable
				:opensourceList="opensource"
			></OSSDetailDependencyTable>
		</div>
	</div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import OSSDetailOpenSourceTable from "@/components/OSS/OSSDetailOpenSourceTable.vue";
import OSSDetailDependencyTable from "@/components/OSS/OSSDetailDependencyTable.vue";
import opensourceApi from "@/api/opensource.js";

export default {
	name: "OSSDetailOpenSource",
	components: {
		MyProjectPath,
		OSSDetailOpenSourceTable,
		OSSDetailDependencyTable,
	},
	data() {
		return {
			opensourceId: this.$route.query.id,
			opensource: {
				id: "",
				// name: '오픈소스 이름',
				name: "",
				copyright: "",
				url: "",
				version: "",
				groupId: "",
				artifactId: "",
				packageType: "",
        userName:"관리자",
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
					this.opensource = response.data;
				})
				.catch();
		},
	},
};
</script>
