package org.springframework.samples.commandfast.restaurantes;


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
public class RestauranteController {

	//TODO
	private static final String RESTAURANTE_FORM = "URL_FORM";

	private final RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService) {
		this.restauranteService = restauranteService;
	}

	@GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model) {
		
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		model.put("listaTipos", listaTipoRestaurantes);
		return "restaurantes/listado";
	}

	@GetMapping(value = { "/list/search" })
	public String showRestautanteToSearch(@RequestParam("inputPlace") String place, Map<String, Object> model, @RequestParam("inputState") String type) {
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaTipos", listaTipoRestaurantes);
		List<Restaurante> lrestaurantes= new ArrayList<>();
		List<Restaurante> lres= new ArrayList<>();

		if(place.isEmpty()){
			lrestaurantes = restauranteService.findAllRestaurants();
		}else{
			lrestaurantes = restauranteService.findByCity(place.toUpperCase());
		}
		System.out.println("*****************************"+type);
		if (!(type.equals("Selecciona una opción"))) {
			for(Restaurante r: restauranteService.findAllRestaurants()){
				if(r.getType().contains(RestauranteType.valueOf(type))){
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
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		if (restaurante.isPresent()) {
			model.addAttribute("restaurante", restaurante.get());
			return RESTAURANTE_FORM;
		} else {
			model.addAttribute("message", "We cannot find the restaurant you tried to edit!");
			return "redirect:/";
		}
	}

	@PostMapping("/restaurantes/{id}/edit")
	public String editRestaurante(@PathVariable("id") Integer id, @Valid Restaurante modifiedRestaurant,
			BindingResult binding, ModelMap model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		if (binding.hasErrors()) {
			return RESTAURANTE_FORM;
		} else {
			BeanUtils.copyProperties(modifiedRestaurant, restaurante.get(), "id");
			restauranteService.saveRestaurant(restaurante.get());
			model.addAttribute("message", "Restaurant updated succesfully!");
			return "redirect:/";
		}
	}


}