package br.com.spring.back.coffeSnugPlace.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.spring.back.coffeSnugPlace.Enum.Profession;
import br.com.spring.back.coffeSnugPlace.model.People;

public interface PeopleRepository extends PagingAndSortingRepository<People, Long>{

	List<People> findByProfession(Profession profession);
	
}
