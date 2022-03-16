package org.springframework.samples.petclinic.restaurantes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
