package br.com.spring.back.coffeSnugPlace.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.spring.back.coffeSnugPlace.Enum.State;
import br.com.spring.back.coffeSnugPlace.Enum.TypeCoffee;

@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
    private TypeCoffee typeCoffee;
    
	private String accompaniment;
	
	@NotNull
    private int amount;
	
	@NotNull
    private State state;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "people_id", nullable = false)
	@JsonIgnore
	private People people;
	
	public Request() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccompaniment() {
		return accompaniment;
	}

	public void setAccompaniment(String accompaniment) {
		this.accompaniment = accompaniment;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public TypeCoffee getTypeCoffee() {
		return typeCoffee;
	}

	public void setTypeCoffee(TypeCoffee typeCoffee) {
		this.typeCoffee = typeCoffee;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
    
    
}
