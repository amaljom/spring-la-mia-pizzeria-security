package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.repo.PromozioneRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromozioneService {
	
	@Autowired
	private PromozioneRepo promoRepo;
	
	public void save(Promozione promozione) {
		
		promoRepo.save(promozione);
	}

	public List<Promozione> findAll() {
		return promoRepo.findAll();
	}
	
	public Optional<Promozione> getPromotionById(int id) {
		return promoRepo.findById(id);
	}
	
	public void deletePromotionById(int id) {
		promoRepo.deleteById(id);
	}
	
	@Transactional
	public List<Promozione> findPizze() {
		
		List<Promozione> promos = promoRepo.findAll();
		
		for (Promozione promo : promos) {
			
			Hibernate.initialize(promo.getPizzas());
		}
		
		return promos;
	}
}
