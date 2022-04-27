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
package org.springframework.samples.commandfast.command;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.mesa.MesaService;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class CommandController {

	private final CommandService commandService;
	private final MesaService mesaService;
	private final RestauranteService restauranteService;

	@Autowired
	public CommandController(CommandService commandService,MesaService mesaService, UserService userService, RestauranteService restauranteService, AuthoritiesService authoritiesService) {
		this.commandService = commandService;
		this.mesaService = mesaService;
		this.restauranteService = restauranteService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/command/new")
	public String initCreationForm(Map<String, Object> model) {
		Command command = new Command();
		model.put("id_restaurante", 0);
		model.put("id_mesa", 0);
		model.put("restaurantes", this.restauranteService.findAllRestaurants());
		model.put("mesas", this.mesaService.findAllMesa());
		model.put("command", command);
		return "command/createCommand";
	}
	
	@GetMapping(value = "/command/new/{id_restaurante}/{id_mesa}")
	public String initFromQR(Map<String, Object> model, @PathVariable("id_restaurante") int id_restaurante, @PathVariable("id_mesa") int id_mesa) {
		Command command = new Command();
		model.put("id_restaurante", id_restaurante);
		model.put("id_mesa", id_mesa);
		model.put("restaurantes", this.restauranteService.findAllRestaurants());
		model.put("mesas", this.mesaService.findAllMesa());
		model.put("command", command);
		return "command/createCommand";
	}
	@PostMapping(value = "/command/new")
	public ModelAndView processCreationForm(@Valid Command command, BindingResult result,HttpServletRequest request) {
		if (result.hasErrors()) {
			Map<String, Object> model = result.getModel();
			model.put("mesas", this.mesaService.findAllMesa());
			model.put("restaurantes", this.restauranteService.findAllRestaurants());
			return new ModelAndView("command/createCommand", model);
		} else {
			Restaurante restaurante = restauranteService.findRestaurantById(Integer.parseInt(request.getParameter("restaurante"))).get();
			this.commandService.saveCommand(command);
			Integer idCommand = command.getId();
			return new ModelAndView("redirect:/carta/"+idCommand+"/"+ restaurante.getId(), result.getModel());
		}
	}
	
	@PostMapping(value = "/command/new/{id_restaurante}/{id_mesa}")
	public ModelAndView processCreationFormFromQr(@Valid Command command, BindingResult result, @PathVariable("id_restaurante") int id_restaurante, @PathVariable("id_mesa") int id_mesa) {
		if (result.hasErrors()) {
			Map<String, Object> model = result.getModel();
			model.put("mesas", this.mesaService.findAllMesa());
			model.put("restaurantes", this.restauranteService.findAllRestaurants());
			return new ModelAndView("command/createCommand", model);
		} else {
			command.setRestaurante(restauranteService.findRestaurantById(id_restaurante).get());
			command.setMesa(mesaService.findMesaByNumber(id_mesa));
			this.commandService.saveCommand(command);
			Integer idCommand = command.getId();
			return new ModelAndView("redirect:/carta/"+idCommand, result.getModel());
		}
	}
	
	
	@GetMapping("/command/all")
	public String commandsOfARestaurant(Map<String, Object> model, HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			String username = request.getUserPrincipal().getName();
			Restaurante restauranteSesion = restauranteService.findByUsername(username);
			model.put("sesionRestaurant", restauranteSesion);
			Collection<Command> listaComandas = commandService.findCommandsOfARestaurant(restauranteSesion.getId());
			if(listaComandas.isEmpty()) {
				model.put("vacio", true);
			} else {
				model.put("listaComandas", listaComandas);
			}
			return "command/commandOfRestaurant";
		}
		return "/";
	}
	
	
}
