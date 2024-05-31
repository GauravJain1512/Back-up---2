package com.authentication.api.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
//		if(authException.getMessage().contains("Full authentication is required to access this resource")) {
//			response.sendError(HttpServletResponse.SC_FORBIDDEN,"Access Denied!!");
//		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied!!");

	}

}
