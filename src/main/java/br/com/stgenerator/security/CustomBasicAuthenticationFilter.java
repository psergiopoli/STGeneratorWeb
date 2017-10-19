package br.com.stgenerator.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter{

	public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

}
