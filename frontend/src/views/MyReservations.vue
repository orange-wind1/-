<template>
  <div class="reservations-container">
    <h3>📋 我的预约历史</h3>
    <div v-if="list.length === 0" class="empty">暂无活跃预约</div>
    <div v-else class="res-list">
      <div v-for="item in list" :key="item.id" class="res-item">
        <span>🪑 座位号: {{ item.seatId }} 号</span> | 
        <span>⏰ 时间: {{ item.time }}</span> | 
        <span class="status">{{ item.status }}</span>
        <button @click="handleCancel(item.id)" class="cancel-btn">取消预约</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const list = ref([])

// 加载数据
const fetchReservations = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/reservations')
    list.value = await response.json()
  } catch (error) {
    console.error('获取列表失败', error)
  }
}

// 取消预约逻辑
const handleCancel = async (id) => {
  try {
    const response = await fetch('http://localhost:8080/api/reservations/cancel', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id: id })
    })
    const data = await response.json()
    if (data.code === 200) {
      alert(data.message)
      fetchReservations() // 刷新列表
    } else {
      alert(data.message)
    }
  } catch (error) {
    alert('取消失败，网络异常')
  }
}

onMounted(() => {
  fetchReservations()
})
</script>

<style scoped>
.reservations-container { margin-top: 30px; padding: 20px; border-top: 2px dashed #ccc; }
.res-item { background: #f9f9f9; padding: 10px; margin-bottom: 10px; border-radius: 4px; display: flex; gap: 15px; align-items: center; }
.status { color: #409eff; font-weight: bold; }
.cancel-btn { background: #f56c6c; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer; }
.cancel-btn:hover { background: #dd6161; }
</style>