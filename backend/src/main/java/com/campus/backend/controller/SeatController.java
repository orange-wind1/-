package com.campus.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin // 允许前端跨域请求
@RestController
@RequestMapping("/api")
public class SeatController {

    @GetMapping("/seats")
    public List<Map<String, Object>> getSeats() {
        // 口述逻辑：生成一个 5x5 的模拟自习室矩阵
        List<Map<String, Object>> seats = new ArrayList<>();
        int idCounter = 1;
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                Map<String, Object> seat = new HashMap<>();
                seat.put("id", idCounter++);
                seat.put("row", row);
                seat.put("col", col);
                // 模拟几个座位被占用的状态 (id为偶数视为被占)
                seat.put("status", (idCounter % 2 == 0) ? 1 : 0); 
                seats.add(seat);
            }
        }
        return seats;
    }
    @PostMapping("/seats/reserve")
    public Map<String, Object> reserveSeat(@RequestBody Map<String, Integer> request) {
        Integer seatId = request.get("seatId");
        Map<String, Object> response = new HashMap<>();
        
        // 新的预约逻辑
        if (seatId != null && seatId > 20) {
            response.put("code", 400);
            response.put("message", "很遗憾，该座位已被抢占");
        } else {
            response.put("code", 200);
            response.put("message", "座位 " + seatId + " 预约成功！");
        }
        return response;
    }
}