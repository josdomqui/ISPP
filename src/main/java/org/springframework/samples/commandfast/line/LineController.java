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
package org.springframework.samples.commandfast.line;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.mesa.MesaService;
import org.springframework.samples.commandfast.plate.Plate;
import org.springframework.samples.commandfast.plate.PlateService;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class LineController {

	private final LineService lineService;
	private final PlateService plateService;
	private final CommandService commandService;
	@Autowired
	public LineController(LineService lineService, PlateService plateService, UserService userService, AuthoritiesService authoritiesService, MesaService mesaService, CommandService commandService) {
		this.lineService = lineService;
		this.plateService = plateService;
		this.commandService = commandService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/carta/{id_comanda}/ticket")
	public String processCreationForm(@PathVariable("id_comanda") int id_commanda,  Map<String, Object> model, RedirectAttributes redirectAttrs) {
		Collection<Line> lineas = this.lineService.findLineByCommandId(id_commanda);
		Collection<Plate> platos = this.plateService.findAllPlates();
		Optional<Command> command = this.commandService.findIdCommands(id_commanda);
		List<Plate> res = new ArrayList<Plate>();
		Double suma = 0.;
		for (Line linea: lineas) {
			for (Plate plato: platos) {
				if(linea.getPlate().getId() == plato.getId() && linea.getQuantity() != 0) {
					res.add(plato);
					for (int i = 0; i < linea.getQuantity(); i++) {
						suma += plato.getCost();
					}
				}
			}
		}
		if(suma == 0.) { //validación para que no se pueda hacer un pedido sin platos
			ModelMap map = new ModelMap();
			//map.addAttribute("message", true);
			redirectAttrs.addFlashAttribute("message", "Por favor, añada platos a su pedido antes de finalizarlo");
			return("redirect:/carta/" + id_commanda);
			//return new ModelAndView("redirect:/carta/" + id_commanda, map);
		}
		command.get().setPrice(suma);
		commandService.saveCommand(command.get());
		
		model.put("comensales", command.get().getCostumers());
		model.put("lista_res", res);
		model.put("id_commanda", id_commanda);
		model.put("lista_linea", lineas);
		model.put("suma", suma);
		Double division = (suma/command.get().getCostumers());
		model.put("division", String.format("%.2f", division));
		return "carta/ticket";
		//return new ModelAndView("carta/ticket", model);
	}

	
	
}
