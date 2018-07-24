package br.com.spring.back.coffeSnugPlace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.back.coffeSnugPlace.Enum.Profession;
import br.com.spring.back.coffeSnugPlace.model.People;

public interface PeopleRepository extends JpaRepository<People, Long>{

	List<People> findByProfession(Profession profession);
	
}
