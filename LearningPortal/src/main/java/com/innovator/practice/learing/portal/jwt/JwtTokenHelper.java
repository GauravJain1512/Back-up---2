package com.innovator.practice.learing.portal.jwt;



import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.innovator.practice.learing.portal.exception.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenHelper {
	
	@Value("${app.jwt.secret}")
	private String jwtSecretKey;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private Long jwtExpirationInMs;
	
	
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
		
		return token;
	}
	
	public String getUsernameFromJwt(String token)
	{
		Claims claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
			return true;
		}catch (SignatureException e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
		}catch (MalformedJwtException e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		}catch (ExpiredJwtException e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		}catch (UnsupportedJwtException e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		}catch (IllegalArgumentException e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		}
	}
}
