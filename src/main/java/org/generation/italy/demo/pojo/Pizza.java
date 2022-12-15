package org.generation.italy.demo.pojo;

import java.util.List;

import org.generation.italy.demo.interf.PriceableInt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Entity
@Table
public class Pizza implements PriceableInt{
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
	
	@ManyToOne
	@JoinColumn(name="id_promozione", nullable = true)
	private Promozione promozione;
	
	@ManyToMany()
	private List<Ingrediente> ingrediente;
	
	
	
	public Pizza() { }
	
	public Pizza(String nome, String descrizione, int prezzo, Promozione promozione) {
		setPrezzo(prezzo);
		setNome(nome);
		setDescrizione(descrizione);
		setPromozione(promozione);
	}
	
	public Pizza(String nome, String descrizione, int prezzo, Promozione promozione, List<Ingrediente> ingrediente) {
		setPrezzo(prezzo);
		setNome(nome);
		setDescrizione(descrizione);
		setPromozione(promozione);
		setIngrediente(ingrediente);
	}
	
	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
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
	public Promozione getPromozione() {
		return promozione;
	}
	public void setPromozione(Promozione promozione) {
		this.promozione = promozione;
	}
	public void addIngredients(Ingrediente ingrediente) {
		
		boolean finded = false;
		for (Ingrediente i : getIngrediente()) 
			if (i.getId() == ingrediente.getId())
				finded = true;
		
		if (!finded) 
			getIngrediente().add(ingrediente);
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
