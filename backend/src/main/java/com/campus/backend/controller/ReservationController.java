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

    // 2. 取消预约接口（补上这一段）
    @PostMapping("/cancel")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Integer> request) {
        Integer resId = request.get("id");
        Map<String, Object> response = new HashMap<>();
        
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

    // 3. 签到监督接口（本轮王斌新增的核心功能 📍）
    @PostMapping("/check-in")
    public Map<String, Object> checkIn(@RequestBody Map<String, Integer> request) {
        Integer resId = request.get("id");
        Map<String, Object> response = new HashMap<>();
        
        // 循环查找对应的预约记录并修改状态
        for (Map<String, Object> res : mockReservations) {
            if (res.get("id").equals(resId)) {
                if ("预约成功".equals(res.get("status"))) {
                    res.put("status", "使用中 (已签到)");
                    response.put("code", 200);
                    response.put("message", "签到成功，请保持安静学习！");
                    return response;
                } else {
                    response.put("code", 400);
                    response.put("message", "当前状态无法重复签到");
                    return response;
                }
            }
        }
        
        response.put("code", 404);
        response.put("message", "未找到该预约记录");
        return response;
    }
}