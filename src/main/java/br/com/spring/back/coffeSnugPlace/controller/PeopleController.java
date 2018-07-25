package br.com.spring.back.coffeSnugPlace.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.spring.back.coffeSnugPlace.Enum.Profession;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundException;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNullException;
import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.service.PeopleService;

@RestController
@RequestMapping("/peoples")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;
	
	@GetMapping("/")
	public ResponseEntity<Page<People>> getPeopleAll(Pageable pageable){
		return new ResponseEntity<Page<People>>(peopleService.getAll(pageable), HttpStatus.OK);
	}
		
	@GetMapping("/profession/{profession}")
	public  ResponseEntity<List<People>> getProfessionals(@PathVariable(value = "profession") Profession profession){
		return new ResponseEntity<List<People>>(peopleService.getPeopleByProfession(profession), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<People> getPeopleById(@PathVariable(value = "id") long id) throws PeopleNotFoundException{
		return new ResponseEntity<People>(peopleService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<People> createPeople(People people){
		return new ResponseEntity<People>(peopleService.save(people), HttpStatus.CREATED);
	}
	 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePeople(@Valid @PathVariable long id){
		peopleService.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<People> updatePeople(@Valid @PathVariable long id, @RequestBody People people) throws PeopleNullException, PeopleNotFoundException{
		People peopleUpdate = peopleService.getById(id);
		peopleUpdate.setLogin(people.getLogin());
		peopleUpdate.setName(people.getName());
		peopleUpdate.setPassword(people.getPassword());
		peopleUpdate.setProfession(people.getProfession());
		return new ResponseEntity<People>(peopleService.update(peopleUpdate), HttpStatus.OK);
	}
	
}
