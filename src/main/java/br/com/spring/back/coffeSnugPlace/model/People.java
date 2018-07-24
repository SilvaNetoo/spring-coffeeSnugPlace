package br.com.spring.back.coffeSnugPlace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.spring.back.coffeSnugPlace.Enum.Profession;

@Entity
public class People {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 100)
	private String name;
	
	@NotNull
	private String login;
	
	@NotNull
	private String password;
	
	@NotNull
	private Profession profession;

	public People(@NotNull @Size(max = 100) String name, @NotNull String login, @NotNull String password,
			@NotNull Profession profession) {
		super();
		this.name = name;
		this.login = login;
		this.password = password;
		this.profession = profession;
	}

	public People() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profession getProfession() {
		return profession;
	}
	
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	
	
}
