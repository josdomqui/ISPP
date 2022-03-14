package org.springframework.samples.petclinic.restaurantes;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {
    
    @GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model) {
		return "restaurantes/listado";
	}
}
