import Vue from "vue";
import VueRouter from "vue-router";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import UserList from "@/views/UserList";
import Home from "@/views/Home";
import FlightList from "@/views/FlightList";
import BoardingList from "@/views/BoardingList";
import PaymentDetails from "@/views/PaymentDetails"
import Checkout from "../views/Checkout";
import Success from "@/views/Success";
import Failed from "@/views/Failed";
import Report from "@/views/Report";

Vue.use(VueRouter);

const routes = [
  { path: '/',
    name: "Home",
    component: Home },

  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.Fteam
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },
  {
    path: "/flights",
    name: "Flights",
    component: FlightList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },
  {
    path: "/boarding",
    name: "Boarding",
    component: BoardingList,
    props: true,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },

  {
    path :"/payment",
    name: "payment",
    component: PaymentDetails,
    props: true,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },

  {
    path: "/checkout",
    name: "checkout",
    component: Checkout,
    props: true,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },

  {
    path: "/payment/success/:bookingID",
    name: "success",
    component: Success,
    props: true,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },

  {
    path: "/payment/failed",
    name: "failed",
    component: Failed,
    props: true,
    beforeEnter: (to, from, next) => {
      if (store.getters.isEmployee) {
        next();
      } else {
        next({ name: "" });
      }
    },
  },

  {
    path: "/report",
    name: "Report",
    component: Report,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({name: "Home"});
      }
    },
  }

];

const router = new VueRouter({
  routes,
});

export default router;
