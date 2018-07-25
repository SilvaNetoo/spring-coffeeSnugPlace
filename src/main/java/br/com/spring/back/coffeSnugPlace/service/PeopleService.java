package br.com.spring.back.coffeSnugPlace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.spring.back.coffeSnugPlace.Enum.Profession;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundException;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNullException;
import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.repository.PeopleRepository;

@Service
public class PeopleService {

	@Autowired 
	private PeopleRepository peopleRepository;
	
	public People save(People people) {
		return peopleRepository.save(people);
	}
	
	public People update(People people) throws PeopleNullException {
		if(people == null) {
			throw new PeopleNullException();
		}
		
		return peopleRepository.save(people);
	}
	
	public void delete(long id) {
		peopleRepository.deleteById(id);
	}
	
	public People getById(long id) throws PeopleNotFoundException {
		if(!peopleRepository.existsById(id)) {
			throw new PeopleNotFoundException(id);
		}
		return peopleRepository.findById(id).get();
	}
	
	
	public Page<People> getAll(Pageable pageable){
		return peopleRepository.findAll(pageable);
	}
	
	public List<People> getPeopleByProfession(Profession profession){
		return peopleRepository.findByProfession(profession);
	}
	
}
