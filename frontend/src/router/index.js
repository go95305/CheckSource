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
import MyProjectEdit from "@/views/MyProject/MyProjectEdit";
import MyProjectEditGitLab from "@/views/MyProject/MyProjectEditGitLab";
import MyProjectGitlab from "@/views/MyProject/MyProjectGitlab";
import LicenseList from "@/components/MyProject/LicenseList";

import OSSMain from "@/views/OSS/OSSMain";
import OSSList from "@/views/OSS/OSSList";
import OSSOpenSource from "@/views/OSS/OSSOpenSource";
import OSSLicense from "@/views/OSS/OSSLicense";

import MyPage from "@/views/MyPage/MyPage";
import MyPageProfile from "@/views/MyPage/MyPageProfile";
import MyPageSCM from "@/views/MyPage/MyPageSCM";
import MyPageGitLab from "@/views/MyPage/MyPageGitLab";
import OpensourceList from "@/components/MyProject/OpensourceList";
import AddComponent from "@/components/MyProject/AddComponent";
import OpensourceMain from "@/views/MyProject/OpensourceMain";
Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		name: "Login",
		component: Login,
		children: [
			{
				path: "afterLogin",
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
				path: "project",
				name: "Project",
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
								path: "gitlab",
								name: "GitLab",
								component: MyProjectGitlab,
							},
							{
								path: "github",
								name: "GitHub",
								component: DashBoard,
							},
							{
								path: "edit",
								name: "MyProjectEdit",
								component: MyProjectEdit,
								children: [
									{
										path: "",
										redirect: "gitlab",
									},
									{
										path: "gitlab",
										name: "MyProjectEditGitLab",
										component: MyProjectEditGitLab,
										props: true,
									},
								],
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
								path: "license",
								name: "License",
								component: LicenseList,
							},
							{
								path: "opensource",
								name: "ResultOpenSource",
								component: OpensourceMain,
								children: [
									{
										path: "add-opensource",
										name: "AddOpenSource",
										component: AddComponent,
									},
									{
										path: "",
										name: "OpenSourceList",
										component: OpensourceList,
									},
								],
							},
						],
					},
				],
			},
			{
				path: "list",
				name: "OSSMain",
				component: OSSMain,
				children: [
					{
						path: "",
						name: "OSSList",
						component: OSSList,
						children: [
							{
								path: "",
								redirect: "opensource",
							},
							{
								path: "opensource",
								name: "OSSOpenSource",
								component: OSSOpenSource,
							},
							{
								path: "license",
								name: "OSSLicense",
								component: OSSLicense,
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
					{
						path: "scm",
						name: "MyPageSCM",
						component: MyPageSCM,
						children: [
							{
								path: "gitlab",
								name: "MyPageGitLab",
								component: MyPageGitLab,
							},
						],
					},
				],
			},
		],
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	// linkActiveClass: "active",
	routes,
});

// router.beforeEach((to, from, next) => {
// 	if (to.path != "/") {
// 		if (!localStorage.getItem("token")) {
// 			next("/");
// 		}
// 	}
// 	next();
// });

export default router;
