package org.generation.italy.demo.pojo;

import org.generation.italy.demo.interf.PriceableInt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table
public class Drink implements PriceableInt{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty(message = "La pizza deve avere un nome")
	@Column(length = 128)
	private String nome;
	
	@NotNull
	@NotEmpty(message = "aggiungi una descrizione")
	@Lob
	private String descrizione;
	
	@Min(value=1)
	private int prezzo;
	
	public Drink() { }
	public Drink(String nome, String descrizione, int prezzo) {
		setPrezzo(prezzo);
		setNome(nome);
		setDescrizione(descrizione);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public int getPrezzo() {
		return prezzo;
	}
	@Override
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		return getId() + getNome() + getDescrizione() + getPrezzo(); 
	}
}

