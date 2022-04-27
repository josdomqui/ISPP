/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.commandfast.valoracion;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValoracionController {
   
	private static final String STRING_RESTAURANTE = "restaurante";
	
	private final RestauranteService restauranteService;
	
	private final ValoracionService valoracionService;
	
	@Autowired
	public ValoracionController(RestauranteService restauranteService, ValoracionService valoracionService) { 
		this.restauranteService = restauranteService; 
		this.valoracionService = valoracionService;
	}
	
	@GetMapping(value = { "/restaurante/{id2}/valoraciones" })
	public String showRestautanteValoraciones(Map<String, Object> model, @PathVariable("id2") Integer id) {
		Restaurante restaurante = restauranteService.findRestaurantById(id).get();
		model.put("listaValoraciones", restaurante.getValoraciones());
		model.put(STRING_RESTAURANTE, restaurante);
		return "restaurantes/listadoValoraciones";
	}
	
	@GetMapping(value = "/restaurante/{id2}/valoracion")
	public String initCreationForm(Map<String, Object> model, @PathVariable("id2") Integer id) {
		Restaurante restaurante = restauranteService.findRestaurantById(id).get();
		Valoracion valoracion = new Valoracion();
		valoracion.setRestaurante(restaurante);
		model.put("valoracion", valoracion);
		model.put(STRING_RESTAURANTE, restaurante);
		return "restaurantes/valorarRestauranteForm";
	}

	@PostMapping(value = "/restaurante/{id2}/valoracion")
	public String processCreationForm(@Valid Valoracion valoracion, BindingResult result, @PathVariable("id2") Integer id,ModelMap modelMap) {
		Restaurante restaurante = restauranteService.findRestaurantById(id).get();
		if (result.hasErrors()) {
			modelMap.addAttribute(STRING_RESTAURANTE, restaurante);
			modelMap.addAttribute("valoracion", valoracion);
			return "restaurantes/valorarRestauranteForm";
		}
		else {
			this.valoracionService.saveValoracion(valoracion);
			valoracionService.actualizarPuntuacionMedia(restaurante);
			return "redirect:/welcome";
		}
	}
}
