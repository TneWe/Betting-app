import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/offers",
      name: "offers",
      component: () => import("./components/OfferList")
    }, {
      path: "/bet-slips",
      name: "bet-slips",
      component: () => import("./components/BetSlipList")
    }
  ]
});
