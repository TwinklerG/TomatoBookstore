import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("@/views/HomeView.vue"),
    },
    {
      path: "/admin",
      name: "admin",
      component: () => import("@/views/AdminView.vue"),
    },
  ],
});

router.beforeEach((to, _, next) => {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");
  if (token !== null) {
    next();
  } else {
    if (to.path === "/") {
      next();
    } else {
      next("/");
    }
  }
});

export default router;
