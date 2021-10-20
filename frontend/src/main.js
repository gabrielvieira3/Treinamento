import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import locale from "element-ui/lib/locale/lang/pt-br";

import "element-ui/lib/theme-chalk/index.css";
import "./styles/theme/index.css";

import App from "./App.vue";
import router from "./routes";
import VueResource from "vue-resource";

Vue.config.productionTip = false;

Vue.use(ElementUI, { locale });
Vue.use(VueResource);

new Vue({
  router, 
  render: (h) => h(App),
}).$mount("#app");
