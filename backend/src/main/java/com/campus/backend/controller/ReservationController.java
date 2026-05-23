package com.campus.backend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    // Mock 几条初始的预约数据
    private static List<Map<String, Object>> mockReservations = new ArrayList<>();

    static {
        Map<String, Object> res1 = new HashMap<>();
        res1.put("id", 101);
        res1.put("seatId", 5);
        res1.put("time", "2026-05-24 09:00-11:00");
        res1.put("status", "预约成功");
        mockReservations.add(res1);
    }

    // 1. 获取当前用户的预约列表接口
    @GetMapping
    public List<Map<String, Object>> getMyReservations() {
        return mockReservations;
    }

    // 2. 取消预约接口
    @PostMapping("/cancel")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Integer> request) {
        Integer resId = request.get("id");
        Map<String, Object> response = new HashMap<>();
        
        // 模拟删除逻辑：如果匹配到 ID，则移除
        boolean removed = mockReservations.removeIf(res -> res.get("id").equals(resId));
        
        if (removed) {
            response.put("code", 200);
            response.put("message", "预约已成功取消，释放座位");
        } else {
            response.put("code", 404);
            response.put("message", "未找到该预约记录");
        }
        return response;
    }
}