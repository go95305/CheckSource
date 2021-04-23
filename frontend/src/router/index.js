import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login/Login";
import DashBoard from "@/views/DashBoard/DashBoard";
import Index from "@/views/Main/Index";
import AfterLogin from "@/components/Login/AfterLogin";
import BeforeLogin from "@/components/Login/BeforeLogin";
import MyProject from "@/views/MyProject/MyProject";
import MyProjectMain from "@/views/MyProject/MyProjectMain";
import MyProjectResult from "@/views/MyProject/MyProjectResult";
import LicenseList from "@/components/MyProject/LicenseList"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "",
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
                path: "summary",
                name: "Summary",
                component: DashBoard,
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
