package br.com.spring.back.coffeSnugPlace.exception;

public class PeopleNullException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public PeopleNullException() {
		super("Objeto é vazio, nulo ou inválido!");
	}
	
}
