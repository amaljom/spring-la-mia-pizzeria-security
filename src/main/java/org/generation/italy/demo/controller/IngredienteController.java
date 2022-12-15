package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.service.IngredienteService;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingrediente") 
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIngredienti(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingredienti-index";
	}
	
	@GetMapping("/create")
	public String createIngrediente(Model model) {
		
		Ingrediente ing = new Ingrediente();
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		model.addAttribute("ingrediente", ing);
		return "ingrediente-create";
	}
	
	@PostMapping("/create")
	public String storeIngrediente(@Valid Ingrediente ingrediente) {
		
		List<Pizza> pizzas = ingrediente.getPizza();
		for (Pizza pizza : pizzas)
			pizza.getIngrediente().add(ingrediente);
		ingredienteService.save(ingrediente);
		
		return "redirect:/ingrediente";
		
	}
	
	@GetMapping("/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Ingrediente> chosenIngredient = ingredienteService.getIngredientById(id);
		Ingrediente ingrediente = chosenIngredient.get();
		
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente-update";
	}
	@PostMapping("/update")
	public String updatePizza(@Valid Ingrediente ingrediente) {
	
		Optional<Ingrediente> oldIng = ingredienteService.getIngredientById(ingrediente.getId());
		Ingrediente ingrediente2 = oldIng.get();
		
		for (Pizza p : ingrediente2.getPizza()) 
			p.getIngrediente().remove(ingrediente);
		
		for (Pizza p : ingrediente.getPizza())			
			p.getIngrediente().add(ingrediente);
		
		ingredienteService.save(ingrediente);
		return "redirect:/ingrediente";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Optional<Ingrediente> delete = ingredienteService.getIngredientById(id);
		Ingrediente ingrediente2 = delete.get();
		
		List<Pizza> pizzas = ingrediente2.getPizza();
		for (Pizza pizza : pizzas)
			pizza.getIngrediente().remove(ingrediente2);
		
		ingredienteService.deleteIngredieteById(id);
		return "redirect:/ingrediente";
	}
	
}
