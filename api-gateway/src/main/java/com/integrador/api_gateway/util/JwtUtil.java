package com.integrador.api_gateway.util;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        // Se usa SecretKeySpec para compatibilidad con versiones antiguas de jjwt
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }


    public void validateToken(final String token) {
        // Sintaxis moderna y correcta para jjwt 0.11.0+
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
