package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/drink")
public class MainControllerDrinks {


	@Autowired
	private DrinkService drinkService;
	
	// List of all elements from db
	@GetMapping
	public String getPize(Model model) {
		
		List<Drink> drinks = drinkService.findAll();
		model.addAttribute("drinks", drinks);
		
		return "drinks";
	}
	
	// Create
	@GetMapping("/create")
	public String createDrink(Model model) {
		
		Drink drink = new Drink();
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	
	@PostMapping("/create")
	public String storeDrink(@Valid @ModelAttribute("drink") Drink drink, 
		BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/drink/create";
		}
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/update/{id}")
	public String editDrink(@PathVariable int id, Model model) {
		
		Optional<Drink> chosenDrink= drinkService.findDrinkById(id);
		Drink drink = chosenDrink.get();
		model.addAttribute("drink", drink);
		
		return "drink-update";
	}
	@PostMapping("/update")
	public String updateDrink(@Valid Drink drink,
		BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/drink/update/" + drink.getId();
		}
		
		drinkService.save(drink);		
		
		return "redirect:/drink";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable int id) {
		
		Optional<Drink> chosenDrink = drinkService.findDrinkById(id);
		Drink drink = chosenDrink.get();
		
		drinkService.delete(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/search")
	public String getSearchDrinkByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		
		List<Drink> drinks = query == null 
				? drinkService.findAll()
				: drinkService.findByName(query); 

		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "drink-search";
	}
	
}