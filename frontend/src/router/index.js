import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login/Login";
import DashBoard from "@/views/DashBoard/DashBoard";
import Index from "@/views/Main/Index";
import AfterLogin from "@/components/Login/AfterLogin";
import BeforeLogin from "@/components/Login/BeforeLogin";

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
    path: "/index",
    name: "Index",
    component: Index,
    children: [
      {
        path: "/index/dashboard",
        name: "DashBoard",
        component: DashBoard,
      },
      {
        path: "/dashboard",
        name: "MyPage",
        component: DashBoard,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
