<template>
	<div id="app">
		<router-view />
	</div>
</template>
<script>
export default {
	name: "App",
	created() {
		//자동로그인
		const localStorageVuex = localStorage.getItem("vuex");
		if (localStorageVuex) {
			const userToken = JSON.parse(localStorageVuex).accessToken;
			if (userToken != null) {
				//현재 페이지가 로그인 페이지라면 대시보드로 이동.
				if (window.location.pathname == "/") this.$router.push("/dashboard");
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
</style>
