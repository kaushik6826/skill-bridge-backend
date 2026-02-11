package com.skillBridge.user.utils;

import com.skillBridge.user.enums.TokenType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;

    private final long accessTokenExpiry;
    private final long refreshTokenExpiry;
    private final long JWT_EXPIRY = 24 * 60 * 60 * 1000; // 1 day
    private final long TEMP_EXPIRY = 15 * 60 * 1000;    // 15 min
    private final String SECRET = "very-secret-key";

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiry}") long accessTokenExpiry,
            @Value("${jwt.refresh-token-expiry}") long refreshTokenExpiry
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    // ===== ACCESS TOKEN =====
    public String generateAccessToken(Long userId, String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("type", "ACCESS")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateToken(Long workerId, TokenType type) {
        long expiry = type == TokenType.TEMP ? TEMP_EXPIRY : JWT_EXPIRY;

        return Jwts.builder()
                .setSubject(workerId.toString())
                .claim("type", type.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    // ===== REFRESH TOKEN =====
    public String generateRefreshToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("type", "REFRESH")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ===== VALIDATION =====
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    // ===== CLAIM EXTRACTION =====
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long extractUserId(String token) {
        Claims claims = extractClaims(token);
        return claims.get("userId", Long.class);
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isRefreshToken(String token) {
        return "REFRESH".equals(extractClaims(token).get("type"));
    }
    
    public String generateMobileWorkerToken(Long workerId, String mobileNo) {

		return Jwts.builder().setSubject(workerId.toString()).claim("workerId", workerId).claim("mobileNo", mobileNo)
				.claim("type", "MOBILE").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiry))
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}
}

