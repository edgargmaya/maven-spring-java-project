package com.edgar.escuela.security.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService  implements UserDetailsService {
	
	@Override
    public UserDetails loadUserByUsername(String username) {
		
		return null;
    }
	
}
