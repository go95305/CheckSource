import Vue from "vue";
import VueRouter from "vue-router";
import store from "../store";
// login
const Login = () => import("@/views/Login/Login");
const AfterLogin = () => import("@/components/Login/AfterLogin");
const BeforeLogin = () => import("@/components/Login/BeforeLogin");
// main
const Index = () => import("@/views/Main/Index");

const DashBoard = () => import("@/views/DashBoard/DashBoard");

const MyProject = () => import("@/views/MyProject/MyProject");
const MyProjectMain = () => import("@/views/MyProject/MyProjectMain");
const MyProjectStatus = () => import("@/views/MyProject/MyProjectStatus");
const MyProjectResult = () => import("@/views/MyProject/MyProjectResult");
const MyProjectEdit = () => import("@/views/MyProject/MyProjectEdit");
const MyProjectEditGitLab = () =>
	import("@/views/MyProject/MyProjectEditGitLab");
const MyProjectGitlab = () => import("@/views/MyProject/MyProjectGitlab");
const LicenseList = () => import("@/components/MyProject/LicenseList");

const OSSMain = () => import("@/views/OSS/OSSMain");
const OSSList = () => import("@/views/OSS/OSSList");
const OSSOpenSource = () => import("@/views/OSS/OSSOpenSource");
const OSSLicense = () => import("@/views/OSS/OSSLicense");
const OSSDetail = () => import("@/views/OSS/OSSDetail");
const OSSDetailOpenSource = () => import("@/views/OSS/OSSDetailOpenSource");
const OSSDetailLicense = () => import("@/views/OSS/OSSDetailLicense");
const OSSDetailLicenseContent = () =>
	import("@/views/OSS/OSSDetailLicenseContent");

const MyPage = () => import("@/views/MyPage/MyPage");
const MyPageProfile = () => import("@/views/MyPage/MyPageProfile");
const MyPageSCM = () => import("@/views/MyPage/MyPageSCM");
const MyPageGitLab = () => import("@/views/MyPage/MyPageGitLab");
const OpensourceList = () => import("@/components/MyProject/OpensourceList");
const AddComponent = () => import("@/components/MyProject/AddComponent");
const OpensourceMain = () => import("@/views/MyProject/OpensourceMain");
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
								// component: DashBoard,
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
					{
						path: "detail",
						name: "OSSDetail",
						component: OSSDetail,
						children: [
							{
								path: "",
								redirect: "opensource",
							},
							{
								path: "opensource",
								name: "OSSDetailOpenSource",
								component: OSSDetailOpenSource,
							},
							{
								path: "license",
								name: "OSSDetailLicense",
								component: OSSDetailLicense,
								children: [
									{
										path: "",
										redirect: "content",
									},
									{
										path: "content",
										name: "OSSDetailLicenseContent",
										component: OSSDetailLicenseContent,
									},
								],
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
	routes,
});

router.beforeEach((to, from, next) => {
	//로그인 여부 확인
	if (to.path != "/") {
		if (!store.getters.getAccessToken) {
			next("/");
		}
	}
	next();
});

export default router;
