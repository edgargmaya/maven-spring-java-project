package com.edgar.escuela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import com.edgar.escuela.security.configuration.CustomEntryPoint;
import com.edgar.escuela.security.configuration.CustomSuccessHandler;
import com.edgar.escuela.security.configuration.CustomcustomFailureHandler;
import com.edgar.escuela.security.configuration.JsonAuthenticationFilter;

@SpringBootApplication
@EnableWebSecurity
public class CurrencyCalculationService1Application extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomEntryPoint customEntryPoint;
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomcustomFailureHandler customFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("user").password("{noop}password").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.exceptionHandling()
			.authenticationEntryPoint(customEntryPoint);
					
		http.cors()
			.and()
			.csrf().disable()
			.authorizeRequests().antMatchers("/v1/api/**").authenticated();
		
		http.formLogin();
		
		/* We can specify a custom Authentication Filter */
		http.addFilterAt( customAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class );
		
		/*
		http.formLogin() -> This instruction creates the default org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter Filter.
			.usernameParameter("user") -> We can specify the user attribute for the user in the POST form field
            .passwordParameter("password") -> We can specify the password attribute for the password in the POST form field
            .loginPage("/login") -> We can stablish the default URL for the login form
            .successHandler(customSuccessHandler) -> And define the behavior for a success login
            .failureHandler(customFailureHandler); -> Or define the behavior for an failure login
        */
		
	}
	
	private JsonAuthenticationFilter customAuthenticationFilter() throws Exception {
		JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
		filter.setAuthenticationFailureHandler(customFailureHandler);
		filter.setAuthenticationSuccessHandler(customSuccessHandler);
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/v1/login");
        return filter;
    }
	
	@Bean
	HeaderHttpSessionIdResolver sessionStrategy() {
		return new HeaderHttpSessionIdResolver("X-Auth-Token");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyCalculationService1Application.class, args);
	}
}
