package br.com.spring.back.coffeSnugPlace.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundDetails;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundException;
import br.com.spring.back.coffeSnugPlace.exception.UserUnauthorizedDetails;
import br.com.spring.back.coffeSnugPlace.exception.UserUnauthorizedException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(PeopleNotFoundException.class)
	public ResponseEntity<?> handlePeopleNotFoundException(PeopleNotFoundException pnfException){
		PeopleNotFoundDetails pnfDetails = PeopleNotFoundDetails.Builder
		.newBuilder()
		.timestamp(new Date().getTime())
		.status(HttpStatus.NOT_FOUND.value())
		.title("Usuário não encontrado!")
		.detail(pnfException.getMessage())
		.developerMessage(pnfException.getClass().getName())
		.build();
		
		return new ResponseEntity<>(pnfDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserUnauthorizedException.class)
	public ResponseEntity<?> handlePeopleUnauthorized(UserUnauthorizedException iaException){
		UserUnauthorizedDetails iaDetails = UserUnauthorizedDetails.Builder
		.newBuilder()
		.timestamp(new Date().getTime())
		.status(HttpStatus.UNAUTHORIZED.value())
		.title("Usuário não tem autorização!")
		.detail(iaException.getMessage())
		.developerMessage(iaException.getClass().getName())
		.build();
		
		return new ResponseEntity<>(iaDetails,HttpStatus.NOT_FOUND);
	}
}
