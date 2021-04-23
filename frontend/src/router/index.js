import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login/Login";
import DashBoard from "@/views/DashBoard/DashBoard";
import Index from "@/views/Main/Index";
import AfterLogin from "@/components/Login/AfterLogin";
import MyProject from "@/views/MyProject/MyProject";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/afterLogin/:employeeNumber",
    name: "AfterLogin",
    component: AfterLogin,
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
        children:[
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
        ]
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  linkActiveClass: 'active',
  routes,
});

export default router;
