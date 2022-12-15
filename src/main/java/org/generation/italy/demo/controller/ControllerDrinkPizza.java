package org.generation.italy.demo.controller;


import java.util.List;
import java.util.stream.Stream;

import org.generation.italy.demo.interf.PriceableInt;
import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/restaurant")
public class ControllerDrinkPizza {
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PromozioneService promoService;

	@GetMapping("/search")
	public String getSearchDrinkByName(Model model, @RequestParam(name = "q", required = false) String query) {
		
		List<Drink> drinks = query == null 
				? drinkService.findAll()
				: drinkService.findByName(query); 
		List<Pizza> pizze = query == null 
				? pizzaService.findAll()
				: pizzaService.findByName(query); 
		
		

		model.addAttribute("pizze", pizze);
		model.addAttribute("query", query);
		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "pizza-drink-search";
	}
	
	@GetMapping("/items")
	public String getOrderedListByPrice(Model model) {
		
		List<Drink> drinks = drinkService.findAll();
				
		List<Pizza> pizze = pizzaService.findAll();
		
		List<PriceableInt> newList = Stream.concat(drinks.stream(), pizze.stream()).toList();
		
		model.addAttribute("newList", newList);
		
		System.out.println(newList);
		return "priceable";
	}
	
}
