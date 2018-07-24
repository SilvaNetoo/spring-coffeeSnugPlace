package br.com.spring.back.coffeSnugPlace.exception;

public class PeopleNotFoundException extends Exception {

	private static final long serialversionUID = 1L;
	
	public PeopleNotFoundException() {
		super("Usuário não encontrado!");
	}
}
