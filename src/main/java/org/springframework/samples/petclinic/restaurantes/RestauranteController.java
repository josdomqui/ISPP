package org.springframework.samples.petclinic.restaurantes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.restaurants.RestaurantType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {
	
	private final RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService){
		this.restauranteService = restauranteService;
	}

    @GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model) {
		ArrayList<RestaurantType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestaurantType.class));
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		model.put("listaTipos", listaTipoRestaurantes);
		return "restaurantes/listado";
	}
	/*
	@PostMapping(value = { "/list" })
	public String showRestautanteListByPlace(Map<String, Object> model) {
		System.out.println("sadasdasdasdas");
		return "restaurantes/listado";
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.ownerService.saveOwner(owner);
	}
		*/	

}
