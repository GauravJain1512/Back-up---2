package com.innovator.practice.learing.portal.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.innovator.practice.learing.portal.security.CustomUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = getJwtFromRequest(request);
		if(StringUtils.hasText(jwt) && jwtTokenHelper.validateToken(jwt)) {
			String usernameFromJwt = jwtTokenHelper.getUsernameFromJwt(jwt);
			if(usernameFromJwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(usernameFromJwt);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				
				System.out.println("Username is null or context is not null");
				
			} 
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String header = request.getHeader("JWT");
		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			return token;
		}
		return null;
	}

}
