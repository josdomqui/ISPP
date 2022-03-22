package org.springframework.samples.commandfast.restaurantes;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		return "restaurantes/listado";
	}

	
	@GetMapping(value = { "/{id}/detalles" })
	public String showRestautanteDetails(@PathVariable("id") Integer id, Map<String, Object> model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		model.put("detallesRestaurante", restaurante.get());
		return "restaurantes/detalles";
	}
	
	
	@GetMapping(value = { "/{id}/detalles/carta" })
	public String showMenuRestaurant(@PathVariable("id") Integer id, Map<String, Object> model) {
		Optional<Restaurante> restauranteMenu = restauranteService.findRestaurantById(id);
		model.put("menu", restauranteMenu.get());
		model.put("products", restauranteService.findAllMenu());
		return "restaurantes/carta";
	}

}
