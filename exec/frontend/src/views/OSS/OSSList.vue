<template>
	<div>
		<my-project-path :department="'전체목록'"></my-project-path>
		<h1 id="oss-list-title">전체목록</h1>
		<div class="list-button-div">
			<router-link to="/list/opensource">오픈소스</router-link>
			<router-link to="/list/license">라이선스</router-link>
		</div>
		<router-view></router-view>
	</div>
</template>
<script>
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
export default {
	name: "OSSList",
	components: { MyProjectPath },
};
</script>
<style scoped src="@/assets/css/OSS/OSSList.css"></style>
