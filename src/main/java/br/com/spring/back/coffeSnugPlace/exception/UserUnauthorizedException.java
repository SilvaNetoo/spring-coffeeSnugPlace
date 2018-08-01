package br.com.spring.back.coffeSnugPlace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserUnauthorizedException() {
		super("Usuário não autorizado!");
	}
}
