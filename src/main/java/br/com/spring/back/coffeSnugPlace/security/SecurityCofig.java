package br.com.spring.back.coffeSnugPlace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.spring.back.coffeSnugPlace.service.BCryptPasswordEncoderBean;
import br.com.spring.back.coffeSnugPlace.service.PeopleService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityCofig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PeopleService peopleService;
	@Autowired
	private BCryptPasswordEncoderBean bCryptPasswordEncoderBean;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET ,"/**").permitAll()
			.antMatchers(HttpMethod.POST , "/peoples").permitAll()
			.anyRequest().authenticated().and()
			.addFilterBefore(new JWTLoginFilter("/peoples", authenticationManager()),
					UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(peopleService).passwordEncoder(bCryptPasswordEncoderBean.getBCryptPasswordEnconder());
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// UserUnauthorizedException {
	// try {
	// auth.inMemoryAuthentication()
	// .withUser("neto").password("{noop}123456").roles("USER")
	// .and()
	// .withUser("admin").password("{noop}123456").roles("USER", "ADMIN");
	// } catch (Exception e) {
	// throw new UserUnauthorizedException();
	// }
	// }

	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);

		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addExposedHeader("Authentication");
		source.registerCorsConfiguration("/**", config);
		// source.registerCorsConfiguration("/**", new
		// CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
