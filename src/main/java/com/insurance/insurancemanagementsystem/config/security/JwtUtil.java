package com.insurance.insurancemanagementsystem.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "438C45F7519CB51BC13D772A12878ABCDEF";

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 10000 * 60 * 60))
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();


    }

    private boolean validateToken(String token, UserDetails userDetails) {

        return extractUsername(token).equals(userDetails.getUsername());

    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
}
