package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.service.IngredienteService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromozioneService;
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
@RequestMapping("/")
public class MainController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PromozioneService promoService;
	@Autowired
	private IngredienteService ingredienteService;
	
	// List of all elements from db
	@GetMapping
	public String getPize(Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "pizze";
	}
	
	// Create
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Promozione> promozione = promoService.findAll();
		model.addAttribute("promozione", promozione);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza-create";
	}
	
	// Create
	@PostMapping("/pizza/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/";
		}
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	// Delete
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Optional<Pizza> chosenPizza = pizzaService.findPizzaById(id);
		Pizza pizza = chosenPizza.get();
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	// Edit and Update
	@GetMapping("/pizza/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> chosenPizza2 = pizzaService.findPizzaById(id);
		Pizza pizza = chosenPizza2.get();
		
		/*
		List<Promozione> promozione = promoService.findAll();
		model.addAttribute("promozione", promozione);
		*/
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("pizza", pizza);
		return "pizza-update";
	}
	@PostMapping("/pizza/update")
	public String updatePizza(@Valid Pizza pizza,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/pizza/update/" + pizza.getId();
		}
		
		pizzaService.save(pizza);		
		
		return "redirect:/";
	}
	// Search
	@GetMapping("/pizza/search")
	public String getSearchDrinkByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		
		List<Pizza> pizze = query == null 
				? pizzaService.findAll()
				: pizzaService.findByName(query); 

		model.addAttribute("pizze", pizze);
		model.addAttribute("query", query);
		
		return "pizza-search";
	}
}
