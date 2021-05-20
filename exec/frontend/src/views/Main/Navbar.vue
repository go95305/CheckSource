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
                        >내 부서</span
                    >
                    <div class="dropdown-content">
                        <router-link to="/project/main/status"
                            >통계</router-link
                        >
                        <router-link to="/project/main/projects"
                            >프로젝트</router-link
                        >
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
                        <router-link to="/list/opensource"
                            >오픈소스</router-link
                        >
                        <router-link to="/list/license">라이선스</router-link>
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
                        <router-link to="/mypage/profile">프로필</router-link>
                        <router-link to="/mypage/scm/gitlab"
                            >형상관리</router-link
                        >
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
