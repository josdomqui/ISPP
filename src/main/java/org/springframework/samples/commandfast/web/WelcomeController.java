package org.springframework.samples.commandfast.web;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {

	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {

		return "welcome";
	}

	
	@PostMapping(value =  "/" )
	public String showRestautanteUbication( HttpServletRequest request,Map<String, Object> model) {
		String type = "Selecciona una opción";
		if(request.getParameter("city")!=null) {
		String place = request.getParameter("city");
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaTipos", listaTipoRestaurantes);
		List<Restaurante> lrestaurantes= new ArrayList<>();
		List<Restaurante> lres= new ArrayList<>();

		if(place.isEmpty()){
			lrestaurantes = restauranteService.findAllRestaurants();
		}else{
			lrestaurantes = restauranteService.findByCity(place.toUpperCase());
			System.out.println(lrestaurantes);
		}
		if (!(type.equals("Selecciona una opción"))) {
			for(Restaurante r: restauranteService.findAllRestaurants()){
				if(r.getType().contains(RestauranteType.valueOf(type))){
					lres.add(r);
				}
			}
			lrestaurantes.retainAll(lres);
		}
		model.put("listaRestaurante", lrestaurantes);
		model.put("place", place);
		return "restaurantes/listado";
		}else {
			model.put("message", "Por favor activa tu ubicación");
			return "welcome";
		}
	}
}
