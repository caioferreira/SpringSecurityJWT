package io.redspark.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public WebSecurityConfig() {
		super(true);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService("REDSPARK_SECRET", userDetailsService);
		JWTAuthenticationSuccessHandler athenticationSuccessHandler = new JWTAuthenticationSuccessHandler(tokenAuthenticationService);
		JWTAuthenticationFailureHandler authenticationFailureHandler = new JWTAuthenticationFailureHandler();
		
		http.formLogin().loginProcessingUrl("/login").successHandler(athenticationSuccessHandler).failureHandler(authenticationFailureHandler)
		.and().exceptionHandling().and().anonymous().and().authorizeRequests()
		.antMatchers("/login").permitAll().and()
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}
}
