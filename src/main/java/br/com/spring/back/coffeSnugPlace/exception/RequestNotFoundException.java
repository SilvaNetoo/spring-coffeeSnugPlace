package br.com.spring.back.coffeSnugPlace.exception;

public class RequestNotFoundException extends Exception {

	private static final long serialVersioUID = 1L;
	
	public RequestNotFoundException() {
		super("Pedido não encontrado!");
	}
}
