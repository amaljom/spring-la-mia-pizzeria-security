package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.repo.IngredienteRepo;
import org.generation.italy.demo.repo.PromozioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepo ingredRepo;
	
	public void save(Ingrediente ingrediente) {
		
		ingredRepo.save(ingrediente);
	}
	
	public List<Ingrediente> findAll() {
		return ingredRepo.findAll();
	}
	
	public Optional<Ingrediente> getIngredientById(int id) {
		return ingredRepo.findById(id);
	}

	public void deleteIngredieteById(int id) {
		ingredRepo.deleteById(id);
	}
}
