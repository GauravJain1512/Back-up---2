package com.ecommerce.api.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenHelper {
	
	@Value("${app.jwt.secret.key}")
	private String jwtSecretKey;
	
	@Value("${app.jwt.expiration.time}")
	private Long jwtExpirationTimeInMillis;
	
	public String generateToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		
		claims.put("roles", roles);
		
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+jwtExpirationTimeInMillis))
				.signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
		
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

}






















