package org.springframework.samples.commandfast.restaurantes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.samples.commandfast.product.ProductService;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

	//TODO
	private static final String RESTAURANTE_FORM = "restaurantes/createRestaurantForm";

	private final RestauranteService restauranteService;
	private final ProductService productService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService, ProductService productService) {
		this.restauranteService = restauranteService;
		this.productService = productService;
	}

    @GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model) {
		
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		model.put("listaTipos", listaTipoRestaurantes);
		return "restaurantes/listado";
	}

	@GetMapping(value = { "/list/search" })
	public String showRestautanteToSearch(@RequestParam("inputPlace") String place, Map<String, Object> model, @RequestParam("inputState") String type) {
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
		//model.put("listaTipos", ltipos);
		return "restaurantes/listado";
	}

	
	@GetMapping(value = { "/{id}/detalles" })
	public String showRestautanteDetails(@PathVariable("id") Integer id, Map<String, Object> model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		model.put("detallesRestaurante", restaurante.get());
		return "restaurantes/detalles";
	}
	
	
	@GetMapping(value = { "/{id}/detalles/carta" })
	public String showMenuRestaurant(@PathVariable("id") Integer id, Map<String, Object> model) {
		Optional<Restaurante> restauranteMenu = restauranteService.findRestaurantById(id);
		model.put("menu", restauranteMenu.get());
		model.put("products", restauranteService.findMenuByRestaurant(id));
		return "restaurantes/carta";
	}


	@GetMapping("/restaurantes/{id}/edit")
	public String editRestaurante(@PathVariable("id") Integer id, ModelMap model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		if (restaurante.isPresent()) {
			model.addAttribute("restaurante", restaurante.get());
			return RESTAURANTE_FORM;
		} else {
			model.addAttribute("message", "We cannot find the restaurant you tried to edit!");
			return "redirect:/";
		}
	}

	@PostMapping("/restaurantes/{id}/edit")
	public String editRestaurante(@PathVariable("id") Integer id, @Valid Restaurante modifiedRestaurant,
			BindingResult binding, ModelMap model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		if (binding.hasErrors()) {
			return RESTAURANTE_FORM;
		} else {
			BeanUtils.copyProperties(modifiedRestaurant, restaurante.get(), "id");
			restauranteService.saveRestaurant(restaurante.get());
			model.addAttribute("message", "Restaurant updated succesfully!");
			return "redirect:/";
		}
	}

	// REGISTRO RESTAURANTES
	@GetMapping("/signup")
	public String signupRestaurante(ModelMap model) {
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		Restaurante restaurante = new Restaurante();
		model.put("restaurant", restaurante);
		model.put("listaTipos", listaTipoRestaurantes);
		return RESTAURANTE_FORM;
	}

	@PostMapping(value = "/signup")
	public String processCreationForm(@Valid Restaurante restaurant, BindingResult result) {
		if (result.hasErrors()) {
			return RESTAURANTE_FORM;
		}
		else {
			this.restauranteService.save(restaurant);
			return "/";
		}
	}
  
	@GetMapping(value = "/{id}/product/new")
	public String initCreationForm(@PathVariable("id") Integer id, Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		model.put("restaurante_id", id);
		return "carta/addProduct";
	}

	@PostMapping(value = "/{id}/product/new")
	public String processCreationForm(@PathVariable("id") Integer id, @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "carta/addProduct";
		}
		else{
			int id_plato = restauranteService.findAllMenu().size()+1;
			product.setId(id_plato);
			product.setRestaurant(restauranteService.findRestaurantById(id).get());
			this.productService.save(product);
			return "redirect:/restaurante/{id}/detalles/carta";
			}
	}

}
