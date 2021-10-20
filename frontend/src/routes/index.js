import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "/login",
      component: () => import("../components/Login.vue")
    },
    {
      path: "/receitas",
      name: "/receitas",
      component: () => import("../components/Receitas.vue")
    },
    {
      path: "/cadastrousuario",
      name: "/cadastroUsuario",
      component: () => import("../components/CadastroUsuario.vue")
    },
    {
        path: "/forgotpassword",
        name: "/forgotPassword",
        component: () => import("../components/ForgotPassword.vue")
    },
    {
        path: "/createrecipe",
        name: "/createrecipe",
        component: () => import("../components/CreateRecipe.vue")
    }
  ]
});