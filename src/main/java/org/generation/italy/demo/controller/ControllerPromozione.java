package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promozione")
public class ControllerPromozione {
	
	@Autowired
	private PromozioneService promoService;
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/index")
	public String getPize(Model model) {
		
		List<Promozione> promos = promoService.findAll();
		model.addAttribute("promos", promos);
		
		return "promozioni";
	}
	
	@GetMapping("/create")
	public String createPromo(Model model) {
		
		Promozione promozione = new Promozione();
		model.addAttribute("promozione", promozione);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "promo-create";
	}
	@PostMapping("/create")
	public String storePromo(@Valid Promozione promozione) {
		
		promoService.save(promozione);
		return "redirect:/promozione/index";
	}
}
