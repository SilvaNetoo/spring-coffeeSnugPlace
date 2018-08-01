package br.com.spring.back.coffeSnugPlace.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderBean {
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	public String enconderPassword(String password) {
		return getBCryptPasswordEnconder().encode(password);
	}
	
}
