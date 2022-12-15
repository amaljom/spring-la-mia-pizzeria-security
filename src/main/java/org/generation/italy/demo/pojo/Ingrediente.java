package org.generation.italy.demo.pojo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table
public class Ingrediente {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	@NotNull
	@Column(length = 128)
	private String nome;
	
	@ManyToMany(mappedBy = "ingrediente")
	private List<Pizza> pizza;
	
	
	
	public Ingrediente() { }
	public Ingrediente(String nome) {
		setNome(nome);
		
	}
	/*
	public Ingrediente(String nome, List<Pizza> pizza) {
		setNome(nome);
		
	}
	*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public List<Pizza> getPizza() {
		return pizza;
	}
	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}
	@Override
	public String toString() {
		return getId() + " - " + getNome();
	}
	
}
