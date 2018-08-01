package br.com.spring.back.coffeSnugPlace.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spring.back.coffeSnugPlace.model.People;

public class JWTLoginFilter  extends AbstractAuthenticationProcessingFilter {

	protected JWTLoginFilter(String url, AuthenticationManager authManager) {

		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		People credentials = new ObjectMapper().readValue(request.getInputStream(),
				People.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(credentials.getUsername(),
						credentials.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		String args = "";
		
		TokenAuthenticationService.addAuthentication(response, auth.getName(),
				auth.getAuthorities().iterator().next(), args);
		
	}

}
