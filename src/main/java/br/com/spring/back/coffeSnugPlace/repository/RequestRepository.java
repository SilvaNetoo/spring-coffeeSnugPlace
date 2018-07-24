package br.com.spring.back.coffeSnugPlace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{

	List<Request> findByPeople(People people);
	
}
