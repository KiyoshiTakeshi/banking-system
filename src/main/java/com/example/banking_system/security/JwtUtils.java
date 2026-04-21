package com.example.banking_system.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component

public class JwtUtils {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    private SecretKey getSignInKey(){
        //Mã hoá plain text thành khoá mã hoá
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Khởi tạo token
    public String generateYoken (UserDetails userDetails){
        return Jwts.builder()
                //Lưu username vào token
                .subject(userDetails.getUsername())
                //Ghi lại thời gian được khởi tạo
                .issuedAt(new Date())
                //Cài dặt thời gian hết hạn của token
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                //đăng nhập bằng secretKey hệ thống - tránh giả key
                .signWith(getSignInKey())
                //build và chạy token
                .compact();
    }

    public String extractUsername (String token) {
        //Bắt đầu quá trình phân tích key
        return Jwts.parser()
                //Xác thực token với secretKey hệ thống
                .verifyWith(getSignInKey())
                //Build trình phân tích key
                .build()
                //Phân tích và xác thực token
                .parseSignedClaims(token)
                //Lấy data lưu trong token
                .getPayload()
                //Lấy userName lưu trong .subject() ở khâu tạo token
                .getSubject();
    }

    public boolean isTokenValid (String token, UserDetails userDetails) {
        try{
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
}
