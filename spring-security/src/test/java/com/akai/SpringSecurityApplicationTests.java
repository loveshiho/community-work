package com.akai;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// @SpringBootTest
class SpringSecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateJwt() {
        JwtBuilder builder = Jwts.builder()
                .setId("12345")     // 设置唯一id
                .setSubject("hello shiho~")     // 主体内容
                .setIssuedAt(new Date())    // 签约时间
                .signWith(SignatureAlgorithm.HS256, "shiho");   // 设置签名，使用 HS256算法，并设置密钥 secretKey
        // 压缩成 string形式
        String jwt = builder.compact();
        System.out.println(jwt);
        /*eyJhbGciOiJIUzI1NiJ9
        .eyJqdGkiOiIxMjM0NSIsInN1YiI6ImhlbGxvIHNoaWhvfiIsImlhdCI6MTcxNTkxMzEyN30
        .HgP76aJm7ob3acGPn6L2nGjkWVEm_UgXvRMYIHXl6Sw*/
    }

    /*解析token*/
    @Test
    void parserJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6ImhlbGxvIHNoaWhvfiIsImlhdCI6MTcxNTkxNDAwNSwiZXhwIjoxNzE1OTE1MDA0LCJuYW1lIjoic2hpaG8ifQ.d_dyCCIdsIBmhgwxaBOrRCLJvkC-kCS-SX5BWb1q2vQ";

        Claims claims = Jwts
                .parser()
                .setSigningKey("shiho")
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims);
        /*{jti=12345, sub=hello shiho~, iat=1715913127}*/
        /*{jti=12345, sub=hello shiho~, iat=1715914005, exp=1715915004, name=shiho}*/
    }

    @Test
    void testCreateJwtWithExpire() {
        long l = System.currentTimeMillis();
        Date expireTime = new Date(l);
        JwtBuilder builder = Jwts.builder()
                .setId("12345")     // 设置唯一id
                .setSubject("hello shiho~")     // 主体内容
                .setIssuedAt(new Date())    // 签约时间
                .setExpiration(expireTime)  // 设置过期时间，生成后直接过期
                .signWith(SignatureAlgorithm.HS256, "shiho");   // 设置签名，使用 HS256算法，并设置密钥 secretKey
        // 压缩成 string形式
        String jwt = builder.compact();
        System.out.println(jwt);
        /*eyJhbGciOiJIUzI1NiJ9
        .eyJqdGkiOiIxMjM0NSIsInN1YiI6ImhlbGxvIHNoaWhvfiIsImlhdCI6MTcxNTkxMzQ2MywiZXhwIjoxNzE1OTEzNDYzfQ
        .GvUwdCZfI9LnL3DlMtMSJp90nnb-U4hDIPWojMIXw4A*/
    }

    @Test
    void testCreateJwtWithClaims() {
        long l = System.currentTimeMillis() + 1000000L;
        Date expireTime = new Date(l);
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "shiho");
        claims.put("password", "123456");
        JwtBuilder builder = Jwts.builder()
                .setId("12345")     // 设置唯一id
                .setSubject("hello shiho~")     // 主体内容
                .setIssuedAt(new Date())    // 签约时间
                .setExpiration(expireTime)  // 设置过期时间，生成后直接过期
                // .setClaims(claims)
                .claim("name", "shiho")
                .signWith(SignatureAlgorithm.HS256, "shiho");   // 设置签名，使用 HS256算法，并设置密钥 secretKey
        // 压缩成 string形式
        String jwt = builder.compact();
        System.out.println(jwt);
        /*eyJhbGciOiJIUzI1NiJ9
        .eyJwYXNzd29yZCI6IjEyMzQ1NiIsInVzZXJuYW1lIjoic2hpaG8ifQ
        .ZG6jL-zq-7RqROLbwPVEk-u8vN8_aQt5ro75f80I5j8*/
    }
}
