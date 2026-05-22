package com.campus.backend.utils;

import java.util.UUID;

public class JwtUtils {
    
    // 模拟生成 Token 的基础逻辑
    public static String generateToken(String username) {
        // 目前先用 UUID + username 模拟一个合法的 Token 结构
        String mockSignature = UUID.randomUUID().toString().replace("-", "");
        return "Bearer " + username + "-" + mockSignature;
    }

    // 预留校验 Token 的方法
    public static boolean validateToken(String token) {
        // TODO: 预留下一阶段 Token 校验与拦截器鉴权逻辑
        return token != null && token.startsWith("Bearer ");
    }
}