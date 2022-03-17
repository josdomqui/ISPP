package org.springframework.samples.commandfast.restaurantes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestauranteController {
	
	private final RestauranteService restauranteService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService){
		this.restauranteService = restauranteService;
	}

    @GetMapping(value = { "/restaurante/list" })
	public String showRestautanteList(Map<String, Object> model) {
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		return "restaurantes/listado";
	}
}
