package br.com.stgenerator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.stgenerator.service.PersonService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	private PersonService ps;

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(ps);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {	
    	http.authorizeRequests().anyRequest().authenticated();
    	http.httpBasic().disable();
    	http.csrf().disable();
    	http.formLogin().usernameParameter("username");
    	http.formLogin().passwordParameter("password");
    	http.formLogin().successHandler(new CustomAuthenticationSuccessHandler());
    	http.formLogin().loginProcessingUrl("/login");
    	http.logout().logoutSuccessHandler(new CustomLogoutSuccessHandler());
    	//http.rememberMe().rememberMeServices(tokenBasedRememberMeService());
        //Implementing Token based authentication in this filter
        //final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter();
        http.addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
        http.rememberMe().key("a").userDetailsService(ps);
    }
    
	 @Bean public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception{
		 return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
	 }
    
	 @Bean 
	 public CustomTokenBasedRememberMeService tokenBasedRememberMeService(){
		 CustomTokenBasedRememberMeService service = new CustomTokenBasedRememberMeService("tokenzinho", ps);
		 service.setAlwaysRemember(true);
		 service.setCookieName("a");
		 return service;
	 }
}
