import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import './assets/css/sta.css'
import './assets/img/sta1.png'
import './assets/img/japanmap1.png'
import './assets/img/point1.jpg'
import './assets/img/star2.png'

// 将自动注册所有组件为全局组件
import dataV from '@jiaminghi/data-view'
Vue.use(dataV)




import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
// import {request} from "axios";
// import VueRouter from "vue-router";

Vue.config.productionTip = false

Vue.use(ElementUI);

// Vue.prototype.axios = request
// Vue.config.productionTip = false


// Vue.use(VueRouter)

new Vue({

  render: h => h(App),
  router
}).$mount('#app')
