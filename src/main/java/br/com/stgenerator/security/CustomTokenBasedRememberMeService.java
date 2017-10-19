package br.com.stgenerator.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

public class CustomTokenBasedRememberMeService extends TokenBasedRememberMeServices {

	public CustomTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
	}

	private final String HEADER_SECURITY_TOKEN = "Authentication";

	/**
	 * Locates the Spring Security remember me token in the request and returns
	 * its value.
	 *
	 * @param request
	 *            the submitted request which is to be authenticated
	 * @return the value of the request header (which was originally provided by
	 *         the cookie - API expects it in header)
	 */
	@Override
	protected String extractRememberMeCookie(HttpServletRequest request) {
		String token = request.getHeader(HEADER_SECURITY_TOKEN);
		if ((token == null) || (token.length() == 0)) {
			return null;
		}
		return token;
	}	
	
	@Override
	protected String[] decodeCookie(String cookieValue) throws InvalidCookieException {
		String token[] = new String[1];
		token[0] = cookieValue;
		return token;
		
	}
	
	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens,
			HttpServletRequest request, HttpServletResponse response)
			throws RememberMeAuthenticationException, UsernameNotFoundException {
		return getUserDetailsService().loadUserByUsername(cookieTokens[0]);
	}
}
