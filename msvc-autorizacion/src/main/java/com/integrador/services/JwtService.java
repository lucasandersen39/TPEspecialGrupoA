package com.integrador.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    // Son 60.000 por cada minuto
    private static final Long TIME_EXPIRATION = 360000L;

    public String getToken(final UserDetails usuario) {
        final Map<String, Object> extraClaims = new HashMap<>();

        final List<String> authorities = usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        extraClaims.put("authorities", authorities);
        return buildToken(extraClaims, usuario);
    }

    private String buildToken(final Map<String, Object> extraClaims, final UserDetails usuario) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(usuario.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))
                .signWith(getSignatureKey(), Jwts.SIG.HS512).compact();
    }

    private SecretKey getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String getClaim(final String token, final String claim) {
        return switch (claim.toLowerCase()) {
            case "sub" -> getClaim(token, Claims::getSubject);
            case "iat" -> getClaim(token, Claims::getIssuedAt).toString();
            case "iss" -> getClaim(token, Claims::getIssuer);
            case "exp" -> getClaim(token, Claims::getExpiration).toString();
            default -> null;
        };
    }

    public <T> T getClaim(final String token, final Function<Claims, T> claimsFunction) {
        return isTokenValid(token) ? claimsFunction.apply(extractAllClaims(token)) : null;
    }

    public Claims extractAllClaims(final String token) {
        return Jwts.parser().verifyWith(getSignatureKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValid(final String token) {
        if (token == null) {
            return false;
        }
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            log.error("Token invalido, error: " + e.getMessage());
            return false;
        }
    }
}