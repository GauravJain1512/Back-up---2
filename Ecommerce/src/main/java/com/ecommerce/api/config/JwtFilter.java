package com.ecommerce.api.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.api.exception.JwtException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("JWT");
		
		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			
			try {
				String username = jwtTokenHelper.extractUsername(token);
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
					if(jwtTokenHelper.validateToken(token, userDetails)) {
						if(isUserAuthorized(token , userDetails)) {
							UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
							authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(authenticationToken);
						}else {
							throw new JwtException("User role is not authorized");
						}
					}else {
						throw new JwtException("Invalid token");
					}
				}else {
					throw new JwtException("Invalid username");
				}
			}catch (IllegalArgumentException e) {
				throw new JwtException("Jwt claims is empty");
			}catch (SignatureException e) {
				throw new JwtException("Invalid Jwt signature");
			}catch (MalformedJwtException e) {
				throw new JwtException("Invalid Jwt token");
			}catch (ExpiredJwtException e) {
				throw new JwtException("Expired Jwt token");
			}catch (UnsupportedJwtException e) {
				throw new JwtException("unsupported Jwt token");
			}
		}
		filterChain.doFilter(request, response);

	}



	private boolean isUserAuthorized(String token, UserDetails userDetails) {
		List<String> roles = jwtTokenHelper.extractClaim(token, claims-> claims.get("roles", List.class));
		
		 String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null);
		 
		 return roles.contains(role);
	}

}
