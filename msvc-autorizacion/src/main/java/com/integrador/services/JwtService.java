package com.integrador.services;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class JwtService {
//	private static final String SECRET_KEY = "jHPYRYxErDDUdV4L4sLCaHbRA1uvU24Jfm1du5M1hWgXhXhA7RiXFUiExBSn0nhf";
	private static String SECRET_KEY;
	private static final Long TIME_EXPIRATION = 3600000L;

	public JwtService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			keyGen.init(256);
			SECRET_KEY = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getToken(UserDetails usuario) {
		return getToken(new HashMap<>(), usuario);
	}

	private String getToken(Map<String, Object> extraClaims, UserDetails usuario) {
		return Jwts.builder().claims(extraClaims).subject(usuario.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))
				.signWith(getSignatureKey(), Jwts.SIG.HS256).compact();
	}

	private SecretKey getSignatureKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
	}

	public String getClaim(String token, String claim) {
        return switch (claim.toLowerCase()) {
            case "sub" -> getClaim(token, Claims::getSubject);
            case "iat" -> getClaim(token, Claims::getIssuedAt).toString();
            case "iss" -> getClaim(token, Claims::getIssuer);
            case "exp" -> getClaim(token, Claims::getExpiration).toString();
            default -> null;
        };
	}

	public <T> T getClaim(String token, Function<Claims, T> claimsFunction) {
		if (isTokenValid(token))
			return claimsFunction.apply(extractAllClaims(token));
		return null;
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSignatureKey()).build().parseSignedClaims(token).getPayload();
	}

	public boolean isTokenValid(String token) {
		if (token == null)
			return false;
		try {
			extractAllClaims(token);
			return true;
		} catch (Exception e) {
			log.error("Token invalido, error: " + e.getMessage());
			return false;
		}
	}
}