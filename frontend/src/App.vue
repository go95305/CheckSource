<template>
	<div id="app">
		<div v-if="loading" class="loading">
			<loading></loading>
		</div>
		<router-view />
	</div>
</template>
<script>
import Loading from "@/components/Loading/Loading";
export default {
	name: "App",
	data() {
		return {
			loading: false,
		};
	},
	components: {
		Loading,
	},
	created() {
		//자동로그인
		const localStorageVuex = localStorage.getItem("vuex");
		if (localStorageVuex) {
			const userToken = JSON.parse(localStorageVuex).accessToken;
			if (userToken != null) {
				//현재 페이지가 로그인 페이지라면 대시보드로 이동.
				if (this.$route.name == "BeforeLogin") this.$router.push("/dashboard");
			}
		}
	},
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;500&display=swap%27");
@import url("https://fonts.googleapis.com/icon?family=Material+Icons");

* {
	margin: 0;
}

#app {
	font-family: "Noto Sans KR";
}

.loading {
	position: absolute;
	display: flex;
	justify-content: center;
	align-items: center;
	left: 0px;
	height: 0px;
	z-index: 10;
	width: 100vw;
	height: 100vh;
	overflow: hidden;
	background-color: rgba(128, 128, 128, 0.466);
}
</style>
