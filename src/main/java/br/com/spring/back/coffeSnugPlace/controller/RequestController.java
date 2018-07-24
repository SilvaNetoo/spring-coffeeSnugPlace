package br.com.spring.back.coffeSnugPlace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundException;
import br.com.spring.back.coffeSnugPlace.exception.RequestNotFoundException;
import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.model.Request;
import br.com.spring.back.coffeSnugPlace.service.PeopleService;
import br.com.spring.back.coffeSnugPlace.service.RequestService;

@RestController
@RequestMapping("/peoples/{id}/requests")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private PeopleService peopleService;

	@GetMapping("/")
	public ResponseEntity<List<Request>> getRequestAll(@PathVariable long id) throws PeopleNotFoundException {
		People people = peopleService.getById(id);
		return new ResponseEntity<List<Request>>(requestService.getAllRequest(people), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Request> getRequestById(@PathVariable long id) throws RequestNotFoundException {
		return new ResponseEntity<Request>(requestService.getById(id), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Request> createRequest(@PathVariable long id, @RequestBody Request request) throws PeopleNotFoundException {
		requestService.searchPeople(id);
		People people = peopleService.getById(id);
		request.setPeople(people);
		return new ResponseEntity<Request>(requestService.save(request), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRequestById(@PathVariable long id) throws RequestNotFoundException {
		requestService.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable long id, @RequestBody Request request) throws RequestNotFoundException {
		Request requestUpdate = requestService.getById(id);
		requestUpdate.setTypeCoffee(request.getTypeCoffee());
		requestUpdate.setAccompaniment(request.getAccompaniment());
		requestUpdate.setAmount(request.getAmount());
		requestUpdate.setState(request.getState());
		return new ResponseEntity<Request>(requestService.update(requestUpdate), HttpStatus.OK);
	}

}
