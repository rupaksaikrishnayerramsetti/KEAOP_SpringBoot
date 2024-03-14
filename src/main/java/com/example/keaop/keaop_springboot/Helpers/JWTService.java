package com.example.keaop.keaop_springboot.Helpers;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
    @Value("${spring.SECRET_KEY}")
    private String jwtSecret;

    @SuppressWarnings("deprecation")
    public String generateToken(String email, String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", userId);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    @SuppressWarnings("deprecation")
    public Map<String, String> decodeToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        Map<String, String> decodedPayload = new HashMap<>();
        decodedPayload.put("email", claims.get("email", String.class));
        decodedPayload.put("userId", claims.get("userId", String.class));
        return decodedPayload;
    }
}
