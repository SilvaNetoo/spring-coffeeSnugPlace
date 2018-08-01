package br.com.spring.back.coffeSnugPlace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundException;
import br.com.spring.back.coffeSnugPlace.exception.RequestNotFoundException;
import br.com.spring.back.coffeSnugPlace.model.Request;
import br.com.spring.back.coffeSnugPlace.repository.PeopleRepository;
import br.com.spring.back.coffeSnugPlace.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private PeopleRepository peopleRepository;
	

	public Request save(Request request) {
		return requestRepository.save(request);
	}

	public Request update(Request request) throws RequestNotFoundException {
		if (!requestRepository.existsById(request.getId())) {
			throw new RequestNotFoundException();
		}

		return requestRepository.save(request);
	}

	public void delete(long id) throws RequestNotFoundException {
		if (!requestRepository.existsById(id)) {
			throw new RequestNotFoundException();
		}
		requestRepository.deleteById(id);
	}

	public Request getById(long id) throws RequestNotFoundException {
		if (!requestRepository.existsById(id)) {
			throw new RequestNotFoundException();
		}
		return requestRepository.findById(id).get();
	}

	public Page<Request> getAllRequest(Pageable pageable) {
		return requestRepository.findAll(pageable);
	}

	public void searchPeople(long id) throws PeopleNotFoundException {
		if(!peopleRepository.existsById(id)) {
			throw new PeopleNotFoundException(id);
		}
	}
}
