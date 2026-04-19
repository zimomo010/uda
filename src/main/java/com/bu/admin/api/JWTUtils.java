package com.bu.admin.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * JSON Web Token
 * Created by ghostWu on 15/7/1.
 */
@Slf4j
public class JWTUtils {

    private JWTUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String SUBJECT = "user-token";
    private static final byte[] AES_SECRET = {-13, -104, -44, 104, -10, -68, -116, -55, 108, 82, -27, -78, 27, 53, 106, 46};

    public static String sign(Map<String, Object> params) {
        Algorithm algorithm = Algorithm.HMAC256(AES_SECRET); // 使用 HMAC256 算法和密钥 "secret"
        return JWT.create()
                .withSubject(SUBJECT) // 设置主题
                .withPayload(params) // 添加用户名到 Claims
//                .withExpiresAt(new Date(System.currentTimeMillis() + 360000000)) // 设置过期时间（1小时后）
                .sign(algorithm); // 签名

    }


    public static Map<String, Claim> verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(AES_SECRET); // 使用相同的密钥进行验证
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token); // 验证令牌
            return jwt.getClaims(); // 获取用户名
        } catch (Exception exception) {
            // "Invalid token"; // 无效的令牌处理
            log.error("Invalid token");
            return Collections.emptyMap();
        }
    }
}
