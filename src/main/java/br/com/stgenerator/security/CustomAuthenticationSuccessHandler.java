package br.com.stgenerator.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		saveCookie("STGenerator", "tokenX", response);
	}

	public void saveCookie(String cookieName, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, value);
		// maxAge is one month: 30*24*60*60
		cookie.setMaxAge(2592000);
		cookie.setDomain("localhost");
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
