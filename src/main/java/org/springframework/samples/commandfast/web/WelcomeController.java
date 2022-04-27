package org.springframework.samples.commandfast.web;

import java.util.ArrayList;
import java.util.Comparator;
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

	private static final String STRING_LISTA_RESTAURANTE = "listaRestaurante";
	
	@Autowired
	private RestauranteService restauranteService;	
	
	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {
		List<Restaurante> lrestaurantes;
		lrestaurantes = restauranteService.findAllRestaurants();
		lrestaurantes.sort(Comparator.comparing(Restaurante::getValoracionMedia).reversed());
		List<Restaurante> lres =new ArrayList<>();
		Integer i= 0;
		while(i<3) {
		for(Restaurante l: lrestaurantes) {
			lres.add(l);
			i++;
		}
		}
		model.put(STRING_LISTA_RESTAURANTE, lres);
		return "welcome";
	}
	
	@GetMapping({ "/terms" })
	public String terms(Map<String, Object> model) {
		return "terms-conditions";
	}
	
	@PostMapping(value =  "/" )
	public String showRestautanteUbication( HttpServletRequest request,Map<String, Object> model) {
		String type = "Selecciona una opción";
		if(request.getParameter("city")!=null) {
		String place = request.getParameter("city");
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaTipos", listaTipoRestaurantes);
		List<Restaurante> lrestaurantes;
		List<Restaurante> lres= new ArrayList<>();
		
		if(place.isEmpty()){
			lrestaurantes = restauranteService.findAllRestaurants();
		}else{
			lrestaurantes = restauranteService.findByCity(place.toUpperCase());
		}
		if (!(type.equals("Selecciona una opción"))) {
			for(Restaurante r: restauranteService.findAllRestaurants()){
				if(r.getType().contains(RestauranteType.valueOf(type))){
					lres.add(r);
				}
			}
			lrestaurantes.retainAll(lres);
		}
		model.put(STRING_LISTA_RESTAURANTE, lrestaurantes);
		model.put("place", place);
		return "restaurantes/listado";
		}else {
			List<Restaurante> lrestaurantes;
			lrestaurantes = restauranteService.findAllRestaurants();
			lrestaurantes.sort(Comparator.comparing(Restaurante::getValoracionMedia).reversed());
			List<Restaurante> lres =new ArrayList<>();
			Integer i= 0;
			while(i<3) {
			for(Restaurante l: lrestaurantes) {
				lres.add(l);
				i++;
			}
			}
			model.put(STRING_LISTA_RESTAURANTE, lres);
			model.put("message", "Por favor activa tu ubicación y permita consultarla al navegador.");
			return "welcome";
		}
	}
	
}
		
