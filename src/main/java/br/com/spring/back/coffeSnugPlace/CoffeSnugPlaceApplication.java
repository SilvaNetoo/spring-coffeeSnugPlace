package br.com.spring.back.coffeSnugPlace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.spring.back.coffeSnugPlace.Enum.Profession;
import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.service.PeopleService;

@SpringBootApplication
public class CoffeSnugPlaceApplication implements CommandLineRunner {
	
	@Autowired
	PeopleService peopleService;
	
	public static void main(String[] args) {
		SpringApplication.run(CoffeSnugPlaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		People people = new People("Neto", "neto@neto.com", "123456", Profession.COOK);
		peopleService.save(people);
		
	}
}
