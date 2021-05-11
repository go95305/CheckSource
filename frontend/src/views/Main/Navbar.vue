<template>
	<nav class="sidenav">
		<profile id="nav-profile" />
		<ul class="nav-ul">
			<li class="nav-li" @click="MenuClickHandler">
				<router-link to="/dashboard">대시보드</router-link>
			</li>
			<li class="nav-li">
				<div class="dropdown">
					<span
						class="dropbtn"
						@click="MenuClickHandler"
						:class="{
							'router-link-active': currentPathName == 'project',
						}"
						>프로젝트</span
					>
					<div class="dropdown-content">
						<router-link to="/project/main/status">Status</router-link>
						<router-link to="/project/main/projects">Projects</router-link>
					</div>
				</div>
			</li>
			<li class="nav-li">
				<div class="dropdown">
					<span
						class="dropbtn"
						@click="MenuClickHandler"
						:class="{
							'router-link-active': currentPathName == 'list',
						}"
						>전체목록</span
					>
					<div class="dropdown-content">
						<router-link to="/list/opensource">Open Source</router-link>
						<router-link to="/list/license">License</router-link>
					</div>
				</div>
			</li>
			<li class="nav-li">
				<div class="dropdown">
					<span
						class="dropbtn"
						@click="MenuClickHandler"
						:class="{
							'router-link-active': currentPathName == 'mypage',
						}"
						>마이페이지</span
					>
					<div class="dropdown-content">
						<router-link to="/mypage/profile">Profile</router-link>
						<router-link to="/mypage/scm/gitlab">SCM</router-link>
					</div>
				</div>
			</li>
		</ul>

		<div id="nav-logo">
			<img src="@/assets/shinhan.png" alt="nav-logo" />
		</div>
	</nav>
</template>

<script>
import Profile from "@/components/Main/Profile";
export default {
	name: "Navbar",
	components: { Profile },
	data() {
		return {
			currentPathName: "",
		};
	},
	watch: {
		$route() {
			this.CheckRouter();
		},
	},
	created() {
		this.CheckRouter();
	},
	methods: {
		MenuClickHandler: function (event) {
			if (event.target.classList.contains("choice")) {
				event.target.classList.remove("choice");
			} else {
				let dropbtns = this.$el.getElementsByClassName("choice");
				for (let i = 0; i < dropbtns.length; i++) {
					dropbtns[i].classList.remove("choice");
				}
				event.target.classList.add("choice");
			}
		},
		CheckRouter: function () {
			//경로 이동 시 해당 경로 이름 설정
			const path = this.$route.path.split("/");
			this.currentPathName = path[1];
		},
	},
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
