<template>
  <div class="login-container">
    <h2>校园自习室预约系统 - 登录</h2>
    <input v-model="username" placeholder="请输入用户名" />
    <input v-model="password" type="password" placeholder="请输入密码" />
    <button @click="handleLogin">登录</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const username = ref('');
const password = ref('');

const handleLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/auth/login', {
      username: username.value,
      password: password.value
    });

    if (response.data.code === 200) {
      localStorage.setItem('token', response.data.token);
      alert("登录成功！Token 已保存。");
    } else {
      alert(response.data.message);
    }
  } catch (error) {
    alert("登录请求失败，请检查后端服务是否启动。");
  }
};
</script>

<style scoped>
.login-container { display: flex; flex-direction: column; width: 300px; margin: 100px auto; }
input { margin-bottom: 10px; padding: 8px; }
</style>