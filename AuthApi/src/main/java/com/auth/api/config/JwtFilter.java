package com.auth.api.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

	    if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
	        String jwtToken = authorizationHeader.substring(7);
	        try {
		        String username = jwtTokenHelper.extractUsername(jwtToken);
		        
	
		        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailService.loadUserByUsername(username);
	
		            if (jwtTokenHelper.validateToken(jwtToken, userDetails)) {
		                if (isUserRoleAuthorized(userDetails, jwtToken)) {
		                	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
		        			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		        			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		                } else {
		                    // Handle unauthorized access attempt (role mismatch)
		                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		                    return;
		                }
		            }
		        }
	        } catch (IllegalArgumentException e) {
                logger.error("JWT claims string is empty");
            } catch (ExpiredJwtException e) {
                logger.warn("Expired JWT token");
            } catch(SignatureException e){
                logger.error("Invalid JWT signature");
            }catch (MalformedJwtException e) {
            	logger.error("Invalid JWT token");
    		}catch (UnsupportedJwtException e) {
    			logger.error("Unsupported JWT token");
    		}
	    }

	    filterChain.doFilter(request, response);
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean isUserRoleAuthorized(CustomUserDetails userDetails, String jwtToken) {
	    List<String> roles = jwtTokenHelper.extractClaim(jwtToken, claims -> claims.get("roles", List.class));
	    String userRole = userDetails.getAuthorities()
	            .stream()
	            .map(GrantedAuthority::getAuthority)
	            .findFirst()
	            .orElse(null);

	    // Check if the user's role matches the expected role in the token
	    return roles.contains(userRole);
	}

}
