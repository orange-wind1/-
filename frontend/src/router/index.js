import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

// 定义路由规则
const routes = [
  {
    path: '/',
    redirect: '/login' // 访问根目录时，自动重定向到登录页
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

// 创建路由实例
const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router