<template>
  <div class="seat-board-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>🎯 2F 核心自习室状态看板</span>
        </div>
      </template>
      
      <div class="legend">
        <div class="legend-item"><span class="block available"></span>可选座位</div>
        <div class="legend-item"><span class="block occupied"></span>已被占用</div>
      </div>

      <div class="seat-matrix">
        <div 
          v-for="seat in seats" 
          :key="seat.id"
          :class="['seat-item', seat.status === 1 ? 'occupied' : 'available']"
        >
          <span class="seat-text">{{ seat.row }}-{{ seat.col }}</span>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

// 响应式座位数据 [cite: 6]
const seats = ref([])

// 从后端拉取模拟的座位矩阵数据
const fetchSeatStatus = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/seats')
    if (response.ok) {
      const data = await response.json()
      seats.value = data
    }
  } catch (error) {
    console.error('获取自习室座位数据失败，请检查后端服务是否启动:', error)
  }
}

onMounted(() => {
  fetchSeatStatus()
})
</script>

<style scoped>
.seat-board-container {
  max-width: 600px;
  margin: 40px auto;
  padding: 0 20px;
}
.card-header {
  font-weight: bold;
  font-size: 18px;
  text-align: center;
}
.legend {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 25px;
}
.legend-item {
  display: flex;
  align-items: center;
  font-size: 14px;
}
.block {
  width: 16px;
  height: 16px;
  border-radius: 4px;
  margin-right: 8px;
}
.seat-matrix {
  display: grid;
  grid-template-columns: repeat(5, 70px);
  gap: 15px;
  justify-content: center;
  padding: 10px;
}
.seat-item {
  width: 70px;
  height: 70px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  color: white;
  font-weight: bold;
  transition: transform 0.2s;
}
.seat-item:hover {
  transform: scale(1.05);
}
.available {
  background-color: #67c23a; /* Element Plus 成功绿 */
}
.occupied {
  background-color: #f56c6c; /* Element Plus 危险红 */
  cursor: not-allowed;
}
.seat-text {
  font-size: 14px;
}
</style>