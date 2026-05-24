<template>
 feature-seat
  <div class="app-container">
    <SeatBoard />
  </div>
</template>

<script setup>
import SeatBoard from './views/SeatBoard.vue'
</script>

<template>
  <div class="app-container">
    <SeatBoard />
    <MyReservations /> 
  </div>
</template>

<script setup>
import SeatBoard from './views/SeatBoard.vue'
import MyReservations from './views/MyReservations.vue' // 👈 引入新组建
</script>

<style>
body {
  margin: 0;
  background-color: #f5f7fa;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;

  <router-view></router-view>
</template>

<style>
/* 你可以保留一些全局基础样式，或者先留空 */
body {
  margin: 0;
  padding: 0;
  background-color: #f5f5f5;
 develop
}
</style>