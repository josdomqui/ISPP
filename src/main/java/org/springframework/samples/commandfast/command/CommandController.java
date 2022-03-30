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


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.mesa.MesaService;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

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

	@Autowired
	public CommandController(CommandService commandService,MesaService mesaService, UserService userService, AuthoritiesService authoritiesService) {
		this.commandService = commandService;
		this.mesaService = mesaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/command/new")
	public String initCreationForm(Map<String, Object> model) {
		Command command = new Command();
		model.put("mesas", this.mesaService.findAllMesa());
		model.put("command", command);
		return "command/createCommand";
	}

	@PostMapping(value = "/command/new")
	public String processCreationForm(@Valid Command comnd, BindingResult result) {
		if (result.hasErrors()) {
			return "command/createCommand";
		} else {
			this.commandService.saveCommand(comnd);
			Integer id_command = comnd.getId();
			return "redirect:/carta/"+id_command;
		}
	}
	
	
}
