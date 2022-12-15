package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
 import org.generation.italy.demo.pojo.Role;
 import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.DrinkService;
import org.generation.italy.demo.service.IngredienteService;
import org.generation.italy.demo.service.PizzaService;
import org.generation.italy.demo.service.PromozioneService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PromozioneService promozioneService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
			Promozione p1 = new Promozione(LocalDate.parse("2022-01-31"), LocalDate.parse("2022-12-31"), "Nuova Promo");
			Promozione p2 = new Promozione(LocalDate.parse("2023-01-31"), LocalDate.parse("2023-12-31"), "Altra Promo");
			
			promozioneService.save(p1);
			promozioneService.save(p2);
			
			Ingrediente i1 = new Ingrediente("farina");
			Ingrediente i2 = new Ingrediente("salsiccia");
			Ingrediente i3 = new Ingrediente("Panna");
			Ingrediente i4 = new Ingrediente("funghi");
			
			
			
			List<Ingrediente> ing1 = new ArrayList<>();
			ing1.add(i1);
			ing1.add(i2);
			
			List<Ingrediente> ing2 = new ArrayList<>();
			ing2.add(i1);
			ing2.add(i4);
			
			List<Ingrediente> ing3 = new ArrayList<>();
			ing3.add(i2);
			ing3.add(i3);
			
			ingredienteService.save(i1);
			ingredienteService.save(i2);
			ingredienteService.save(i3);
			ingredienteService.save(i4);
			
			
			
			Pizza b1 = new Pizza("rossa", "pomodori", 10, p1, ing1);
			Pizza b2 = new Pizza("bianca", "rosamrino", 5, p1, ing1);
			Pizza b3 = new Pizza("boscaiola", "funghi e salsiccia", 13, p1, ing3);
			Pizza b4 = new Pizza("napoli", "alici", 10, p2, ing2);
			Pizza b5 = new Pizza("samename", "pizza", 10, null, ing2);
			
			pizzaService.save(b1);
			pizzaService.save(b2);
			pizzaService.save(b3);
			pizzaService.save(b4);
			pizzaService.save(b5);
			
			
			// List<Pizza> pizze = pizzaService.findAll();
			
			Drink d1 = new Drink("mojito", "bho", 10);
			Drink d2 = new Drink("Zacapa", "rum", 7);
			Drink d3 = new Drink("rum&cola", "cola", 9);
			Drink d4 = new Drink("fanta", "fanta", 5);
			Drink d5 = new Drink("samename", "drink", 10);
			
			drinkService.save(d1);
			drinkService.save(d2);
			drinkService.save(d3);
			drinkService.save(d4);
			drinkService.save(d5);
			
			
			// List<Drink> drinks = drinkService.findAll();
			
			// test 
			// promozioneService.deletePromotionById(1);
			
			/*
			List<Promozione> promos = promozioneService.findPizze();
			System.out.println("---------------------------");
			System.out.println("Solo le pizze con promozioni");
			for (Promozione p : promos) {
				for (Pizza pizza : p.getPizzas()) {
					// System.err.println("\t" + pizza.getPromozione() + " - " + pizza.getNome());
				}
			}
			 */
			// auth
			
			Role userRole = new Role("USER");
			Role adminRole = new Role("ADMIN");
			
			roleService.save(userRole);
			roleService.save(adminRole);
		
			User userUser = new User("user", "{noop}password", userRole);
			User adminUser = new User("admin", "{noop}password2", adminRole);
			
			Set<Role> userAdminRoles = new HashSet<>();
			userAdminRoles.add(userRole);
			userAdminRoles.add(adminRole);
			User userAdminUser = new User("useradmin", "{noop}password3", userAdminRoles);
			
			userService.save(userUser);
			userService.save(adminUser);
			userService.save(userAdminUser);
			
	}
	
	

}
