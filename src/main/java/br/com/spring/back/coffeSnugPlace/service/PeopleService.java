package br.com.spring.back.coffeSnugPlace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.spring.back.coffeSnugPlace.Enum.Profession;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNotFoundException;
import br.com.spring.back.coffeSnugPlace.exception.PeopleNullException;
import br.com.spring.back.coffeSnugPlace.model.People;
import br.com.spring.back.coffeSnugPlace.repository.PeopleRepository;

@Service
public class PeopleService implements UserDetailsService {

	@Autowired
	private PeopleRepository peopleRepository;
	
	@Autowired
	private BCryptPasswordEncoderBean bCryptPasswordEncoderBean;
	
	public People save(People people) {
		people.setPassword(bCryptPasswordEncoderBean.enconderPassword(people.getPassword()));
		return peopleRepository.save(people);
	}

	public People update(People people) throws PeopleNullException {
		if (people == null) {
			throw new PeopleNullException();
		}

		return peopleRepository.save(people);
	}

	public void delete(long id) {
		peopleRepository.deleteById(id);
	}

	public People getById(long id) throws PeopleNotFoundException {
		return Optional.ofNullable(peopleRepository.findById(id).get())
				.orElseThrow(() -> new PeopleNotFoundException(id));
	}

	public Page<People> getAll(Pageable pageable) {
		return peopleRepository.findAll(pageable);
	}

	public List<People> getPeopleByProfession(Profession profession) {
		return peopleRepository.findByProfession(profession);
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return (UserDetails) Optional.ofNullable(peopleRepository.findByName(name)).orElseThrow(
				() -> new UsernameNotFoundException("O seguinte usuário " + name + " não foi encontrado!"));
	}

}
