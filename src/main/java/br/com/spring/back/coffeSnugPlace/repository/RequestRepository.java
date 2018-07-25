package br.com.spring.back.coffeSnugPlace.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.model.Request;

public interface RequestRepository extends PagingAndSortingRepository<Request, Long>{

	List<Request> findByPeople(People people);
	
}
