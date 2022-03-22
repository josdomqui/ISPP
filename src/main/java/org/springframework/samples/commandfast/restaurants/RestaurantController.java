package org.springframework.samples.petclinic.restaurants;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/restaurante")
public class RestaurantController {

	//TODO
	private static final String RESTAURANTE_FORM = "URL_FORM";

	private final RestaurantService restaurantService;

	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model) {
		
		ArrayList<RestaurantType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestaurantType.class));
		model.put("listaRestaurante", restaurantService.findAllRestaurants());
		model.put("listaTipos", listaTipoRestaurantes);
		return "restaurantes/listado";
	}

	@GetMapping(value = { "/list/search" })
	public String showRestautanteToSearch(@RequestParam("inputPlace") String place, Map<String, Object> model, @RequestParam("inputState") String type) {
		//System.out.println("************************************".concat(type));
		ArrayList<RestaurantType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestaurantType.class));
		model.put("listaTipos", listaTipoRestaurantes);
		List<Restaurant> lrestaurantes= new ArrayList<>();
		List<Restaurant> lres= new ArrayList<>();

		if(place.isEmpty()){
			lrestaurantes = restaurantService.findAllRestaurants();
		}else{
			lrestaurantes = restaurantService.findByCity(place.toUpperCase());
		}
		System.out.println("*****************************"+type);
		if (!(type.equals("Selecciona una opción"))) {
			for(Restaurant r: restaurantService.findAllRestaurants()){
				if(r.getType().contains(RestaurantType.valueOf(type))){
					lres.add(r);
				}
			}
			lrestaurantes.retainAll(lres);
		}
		model.put("listaRestaurante", lrestaurantes);
		//model.put("listaTipos", ltipos);
		return "restaurantes/listado";
	}

	@GetMapping("/restaurantes/{id}/edit")
	public String editRestaurante(@PathVariable("id") Integer id, ModelMap model) {
		Optional<Restaurant> restaurante = restaurantService.findRestaurantById(id);
		if (restaurante.isPresent()) {
			model.addAttribute("restaurante", restaurante.get());
			return RESTAURANTE_FORM;
		} else {
			model.addAttribute("message", "We cannot find the restaurant you tried to edit!");
			return "redirect:/";
		}
	}

	@PostMapping("/restaurantes/{id}/edit")
	public String editRestaurante(@PathVariable("id") Integer id, @Valid Restaurant modifiedRestaurant,
			BindingResult binding, ModelMap model) {
		Optional<Restaurant> restaurante = restaurantService.findRestaurantById(id);
		if (binding.hasErrors()) {
			return RESTAURANTE_FORM;
		} else {
			BeanUtils.copyProperties(modifiedRestaurant, restaurante.get(), "id");
			restaurantService.saveRestaurant(restaurante.get());
			model.addAttribute("message", "Restaurant updated succesfully!");
			return "redirect:/";
		}
	}


}
