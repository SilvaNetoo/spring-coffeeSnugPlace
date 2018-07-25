package br.com.spring.back.coffeSnugPlace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeopleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PeopleNotFoundException(long id) {
		super("Usuário com o seguinte id não foi encontrado: " + id);
	}
}
