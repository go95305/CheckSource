<template>
    <nav class="sidenav">
        <profile id="nav-profile" />
        <ul class="nav-ul">
            <li
                class="nav-li"
                @click="MenuClickHandler"
                :class="{ sidenav_choiced: currentPathName == 'dashboard' }"
            >
                <router-link to="/dashboard">대시보드</router-link>
            </li>
            <li class="nav-li">
                <div class="dropdown">
                    <span
                        class="dropbtn"
                        @click="MenuClickHandler"
                        :class="{
                            sidenav_choiced: currentPathName == 'project',
                        }"
                        >프로젝트</span
                    >
                    <div class="dropdown-content">
                        <router-link
                            to="/project/main/status"
                            :class="{
                                sidenav_choiced:
                                    currentThirdPathName == 'status',
                            }"
                            >Status</router-link
                        >
                        <router-link
                            to="/project/main/gitlab"
                            :class="{
                                sidenav_choiced:
                                    currentThirdPathName == 'gitlab',
                            }"
                            >GitLab</router-link
                        >
                        <router-link
                            to="/project/main/github"
                            :class="{
                                sidenav_choiced:
                                    currentThirdPathName == 'github',
                            }"
                            >GitHab</router-link
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
                            sidenav_choiced: currentPathName == 'totallist',
                        }"
                        >전체목록</span
                    >
                    <div class="dropdown-content">
                        <router-link to="/myproject/main/status"
                            >Open Source</router-link
                        >
                        <router-link to="/myproject/main/gitlab"
                            >License</router-link
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
                            sidenav_choiced: currentPathName == 'mypage',
                        }"
                        >마이페이지</span
                    >
                    <div class="dropdown-content">
                        <router-link
                            to="/mypage/profile"
                            :class="{
                                sidenav_choiced:
                                    currentSubPathName == 'profile',
                            }"
                            >Profile</router-link
                        >
                        <router-link
                            to="/mypage/scm/gitlab"
                            :class="{
                                sidenav_choiced: currentSubPathName == 'scm',
                            }"
                            >SCM</router-link
                        >
                    </div>
                </div>
            </li>
        </ul>

        <!-- <router-link
            :class="{ sidenav_choiced: currentPathName == 'myproject' }"
            to="/myproject/main/summary"
            >내 프로젝트</router-link
        >
        <router-link
            :class="{ sidenav_choiced: currentPathName == 'TotalList' }"
            to="/dashboard"
            >전체목록</router-link
        > -->

        <div id="nav-logo">
            <img src="@/assets/shinhan.png" width="150px" alt="nav-logo" />
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
            currentSubPathName: "",
            currentThirdPathName: "",
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
            this.currentSubPathName = path[2];
            this.currentThirdPathName = path[3];
        },
    },
};
</script>

<style scoped src="@/assets/css/Main/Navbar.css"></style>
