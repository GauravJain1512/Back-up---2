package com.authentication.api.config;

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

import com.authentication.api.exception.JwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDeatilsService customUserDeatilsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("JWT");
		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			try {
				String username = jwtTokenHelper.extractUsername(token);
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = customUserDeatilsService.loadUserByUsername(username);
					if(jwtTokenHelper.validateToken(token, userDetails)) {
						if(isUserRoleAuthorized(token, userDetails)) {
							UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
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
			}catch (IllegalArgumentException ex) {
				throw new JwtException("JWT claims string is empty");
			}catch (ExpiredJwtException ex) {
				throw new JwtException("Expired JWT token");
			}catch (SignatureException ex) {
				throw new JwtException("Invalid JWT signature");
			}catch (MalformedJwtException ex) {
				throw new JwtException("Invalid JWT token");
			}catch (UnsupportedJwtException ex) {
				throw new JwtException("Unsupported JWT token");
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isUserRoleAuthorized(String token, UserDetails userDetails) {
		List<String> roles = jwtTokenHelper.extractClaim(token, claims->claims.get("roles", List.class));
		
		String userRole = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null);
		return roles.contains(userRole);
	}

}
