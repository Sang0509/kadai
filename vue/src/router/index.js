import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/HomeView.vue'

import Sta from '../views/Sta.vue'
import Text from "@/views/Text.vue"

import List from '../views/AboutView'

import Login from "../views/Login.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'List',
    component: List
  },
  {
    path: '/sta',
    name: 'Sta',
    component: Sta
  },
  {
    path: '/text',
    name: 'Text',
    component: Text
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router


// import Vue from 'vue'
// import App from '../App'
// import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
// import VueRouter from 'vue-router' // 路由 1
// import Home from '../views/Home.vue'
// import Login from '../views/Login'
// import router from '../router' // 路由 2
//
// // 关闭 Vue 的生产提示
// Vue.config.productionTip = false
//
// // 使用插件
// Vue.use(ElementUI)
// Vue.use(VueRouter) // 路由 3
//
// const routes = [
//   {
//     path: '/home',
//     name: 'Home',
//     component: Home
//   },
//   {
//     path: '/login',
//     name: 'Login',
//     component:Login
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     // component: () => import(/ webpackChunkName: "about" / '../views/Login.vue')
//   }
// ]
//
// const router = new VueRouter({
//   mode: 'history',
//   base: process.env.BASE_URL,
//   routes
// })
//
// export default router
//
// // 创建 Vue 实例对象
// new Vue({
//   render: h => h(App),
//   router // 路由 4
// }).$mount('#app')