import Vue from "vue";
import VueRouter from "vue-router";
import DashBoard from "../views/DashBoard/DashBoard";
import Index from "../views/Main/Index"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "DashBoard",
    component: DashBoard,
  },
  { path: '/', 
    name:"Index",
    component: Index,
      children: [
        {
          path: '/dashboard',
          name: "DashBoard",
          component: DashBoard
        },
      ]
    }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
