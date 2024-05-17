package com.akai.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    // 有效期为
    public static final Long JWT_TTL = 60 * 60 * 1000L; // 60 * 60 *1000  一个小时
    // 设置明文密钥
    public static final String JWT_KEY = "loveshiho";

    // UUID作为 JWT唯一ID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttl, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        long nowMillis = System.currentTimeMillis();
        if (ttl == null) ttl = JWT_TTL;
        Date ex = new Date(ttl + nowMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(ex)
                .signWith(signatureAlgorithm, JWT_KEY);
    }
    /*生成Jwt*/
    public static String createJwt(String subject, Long ttl) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttl, getUUID());
        return jwtBuilder.compact();
    }
    public static String createJwt(String subject, Long ttl, String id) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttl, id);
        return jwtBuilder.compact();
    }
    /*解析Jwt*/
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
