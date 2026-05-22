package com.campus.backend.controller;

import com.campus.backend.dto.LoginRequest;
import com.campus.backend.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        // 简单的账号密码硬编码校验 (供后续模块联调使用)
        // TODO:下一阶段需连接 MySQL 数据库进行真实用户表数据校验
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            String token = JwtUtils.generateToken(request.getUsername());
            
            response.put("code", 200);
            response.put("message", "登录成功");
            response.put("token", token);
        } else {
            response.put("code", 401);
            response.put("message", "用户名或密码错误");
        }
        
        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody LoginRequest request) {
        // TODO: 预留用户注册落库逻辑
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "注册接口预留成功：" + request.getUsername());
        return response;
    }
}