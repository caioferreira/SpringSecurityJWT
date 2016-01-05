package io.redspark.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private TokenAuthenticationService tokenService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		tokenService.addAuthentication(response, authentication);
	}

	public JWTAuthenticationSuccessHandler(TokenAuthenticationService tokenService) {
		super();
		this.tokenService = tokenService;
	}
	
}
