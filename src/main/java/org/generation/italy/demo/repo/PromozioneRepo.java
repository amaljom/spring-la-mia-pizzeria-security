package org.generation.italy.demo.repo;


import org.springframework.stereotype.Repository;
import org.generation.italy.demo.pojo.Promozione;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface  PromozioneRepo extends JpaRepository<Promozione, Integer>{
	
}
