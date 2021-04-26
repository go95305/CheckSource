import Vue from "vue";
import VueRouter from "vue-router";
// login
import Login from "@/views/Login/Login";
import AfterLogin from "@/components/Login/AfterLogin";
import BeforeLogin from "@/components/Login/BeforeLogin";
// main
import Index from "@/views/Main/Index";

import DashBoard from "@/views/DashBoard/DashBoard";

import MyProject from "@/views/MyProject/MyProject";
import MyProjectMain from "@/views/MyProject/MyProjectMain";
import MyProjectStatus from "@/views/MyProject/MyProjectStatus";
import MyProjectResult from "@/views/MyProject/MyProjectResult";
import LicenseList from "@/components/MyProject/LicenseList";

import MyPage from "@/views/MyPage/MyPage";
import MyPageProfile from "@/views/MyPage/MyPageProfile";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Login",
        component: Login,
        children: [
            {
                path: "afterLogin/:employeeNumber",
                name: "AfterLogin",
                component: AfterLogin,
            },
            {
                path: "",
                name: "BeforeLogin",
                component: BeforeLogin,
            },
        ],
    },
    {
        path: "/",
        name: "Index",
        component: Index,
        children: [
            {
                path: "dashboard",
                name: "DashBoard",
                component: DashBoard,
            },
            {
                path: "myproject",
                name: "MyProject",
                component: MyProject,
                children: [
                    {
                        path: "main",
                        name: "MyProjectMain",
                        component: MyProjectMain,
                        children: [
                            {
                                path: "status",
                                name: "Status",
                                component: MyProjectStatus,
                            },
                            {
                                path: "department",
                                name: "Department",
                                component: DashBoard,
                            },
                            {
                                path: "project",
                                name: "Project",
                                component: DashBoard,
                            },
                        ],
                    },
                    {
                        path: "result",
                        name: "MyProjectResult",
                        component: MyProjectResult,
                        children: [
                            {
                                path: "summary",
                                name: "Summary",
                                component: DashBoard,
                            },
                            {
                                path: "component",
                                name: "Component",
                                component: DashBoard,
                            },
                            {
                                path: "license",
                                name: "License",
                                component: LicenseList,
                            },
                        ],
                    },
                ],
            },
            {
                path: "mypage",
                name: "MyPage",
                component: MyPage,
                children: [
                    {
                        path: "profile",
                        name: "MyPageProfile",
                        component: MyPageProfile,
                    },
                ],
            },
        ],
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    linkActiveClass: "active",
    routes,
});

export default router;
