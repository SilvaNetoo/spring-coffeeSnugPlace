package br.com.spring.back.coffeSnugPlace.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	// EXPIRATION_TIME = 3 dias
	static final long EXPIRATION_TIME = 259_200_000;

	static final String SECRET = "MySecret";

	static final String TOKEN_PREFIX = "Bearer";

	static final String HEADER_STRING = "Authorization";

	static final String ROLE_STRING = "Role";

    static void addAuthentication(HttpServletResponse res, String username,
    		GrantedAuthority grantedAuthority, String args) throws IOException {
    	String JWT = Jwts.builder().setSubject(username)
				.claim(ROLE_STRING, grantedAuthority.getAuthority()).claim("args", args)
				.setExpiration(
						new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		res.setHeader("Access-Control-Expose-Headers", HEADER_STRING);
    }

    static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null,
						Collections.emptyList());
			}
		}

		return null;
    }
	
}
