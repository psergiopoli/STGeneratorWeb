package br.com.stgenerator.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.stgenerator.service.PersonService;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {

	@Autowired
	PersonService ps;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return super.attemptAuthentication(request, response);
	}

}