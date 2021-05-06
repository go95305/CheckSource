<template>
	<div id="path">
		<span>
			<router-link class="root-path" to="/project">프로젝트</router-link>
			<router-link class="root-path" to="/list">전체목록</router-link>
			<router-link class="root-path" to="/mypage">마이페이지</router-link>

			<router-link to="/list/opensource/list">/오픈소스</router-link>
			<router-link to="/list/license/list">/라이선스</router-link>
		</span>
	</div>
</template>
<script>
export default {
	name: "PathComponent",
};
</script>
<style scoped src="@/assets/css/PathComponent/PathComponent.css"></style>
