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
package org.springframework.samples.commandfast.plate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.line.LineService;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class PlateController {

	private static final String VIEWS_Plates = "carta/plateList";

	private final PlateService plateService;

	private final LineService lineService;

	@Autowired
	public PlateController(PlateService plateService, LineService lineService, AuthoritiesService authoritiesService) {
		this.plateService = plateService;
		this.lineService = lineService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	@GetMapping(value = "/carta/{id_comanda}")
	public String showAllPlates(@PathVariable("id_comanda") int id_commanda, Map<String, Object> model) {
		Collection<Plate> plate = plateService.findAllPlates();
		model.put("platos", plate);
		List<Line> line = new ArrayList<>();
		model.put("lines", line);
		model.put("id_commanda", id_commanda);
		return VIEWS_Plates;
	}
	
	@PostMapping(value = "/carta/{id_comanda}")
	public String processCreationForm(@PathVariable("id_comanda") int id_commanda, @Valid Line line, BindingResult result) {
		this.lineService.saveline(line);
		return "redirect:/carta/"+id_commanda+"/edit";
	}
	
	@GetMapping(value = "/carta/{id_comanda}/edit")
    public String initUpdateLineForm(@PathVariable("id_comanda") int id_comanda, Map<String, Object> model) {
        Collection<Line> line = this.lineService.findLineByCommandId(id_comanda);
        model.put("lines", line);
		Collection<Plate> plate = plateService.findAllPlates();
		model.put("platos", plate);        
		model.put("id_commanda", id_comanda);
		return VIEWS_Plates;
    }

	
	@PostMapping(value = "/carta/{id_comanda}/edit")
	public String processUForm(@PathVariable("id_comanda") int id_commanda, @Valid Line line, BindingResult result) {
		this.lineService.saveline(line);
		return "redirect:/carta/"+id_commanda+"/edit";
	}
	
}
