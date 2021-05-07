<template>
	<div id="app">
		<router-view />
	</div>
</template>
<script>
export default {
	name: "App",
	created() {
		const userToken = JSON.parse(localStorage.getItem("vuex")).accessToken;
		if (userToken != null) {
			this.$router.push("/dashboard");
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
