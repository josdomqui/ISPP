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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.samples.commandfast.product.ProductService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/restaurante")

public class RestauranteController {
	private static final String STRING_ANONYMOUS_USER = "anonymousUser";
	private static final String STRING_USER_NAME = "username";
	private static final String STRING_LISTA_TIPOS = "listaTipos";

	private static final String RESTAURANTE_FORM = "restaurantes/createRestaurantForm";
	private static final String CARTA_FORM = "carta/addProduct";
	
	private final RestauranteService restauranteService;
	private final ProductService productService;
	private final UserService userService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService, ProductService productService, UserService userService) { 
		this.restauranteService = restauranteService; 
		this.productService = productService; 
		this.userService = userService; 
	}

    @GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model, HttpServletRequest request) {
		
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals(STRING_ANONYMOUS_USER)) {
			model.put(STRING_USER_NAME, request.getUserPrincipal().getName());
		}
		return "restaurantes/listado";
	}

	@GetMapping(value = { "/list/search" })
	public String showRestautanteToSearch(@RequestParam("inputPlace") String place, Map<String, Object> model, @RequestParam("inputState") String type, HttpServletRequest request) {
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
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
		model.put("listaRestaurante", lrestaurantes);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals(STRING_ANONYMOUS_USER)) {
			model.put(STRING_USER_NAME, request.getUserPrincipal().getName());
		}
		return "restaurantes/listado";
	}

	
	@GetMapping(value = { "/{id}/detalles" })
	public String showRestautanteDetails(@PathVariable("id") Integer id, Map<String, Object> model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		model.put("detallesRestaurante", restaurante.get());
		return "restaurantes/detalles";
	}
	
	
	@GetMapping(value = { "/{id}/detalles/carta" })
	public String showMenuRestaurant(@PathVariable("id") Integer id, Map<String, Object> model, HttpServletRequest request) {
		Optional<Restaurante> restauranteMenu = restauranteService.findRestaurantById(id);
		model.put("menu", restauranteMenu.get());
		model.put("products", restauranteService.findMenuByRestaurant(id));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals(STRING_ANONYMOUS_USER)) {
			model.put(STRING_USER_NAME, request.getUserPrincipal().getName());
		}
		model.put("restaurante", restauranteService.findRestaurantById(id).get());
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
		model.put("error", false); 
		model.put("restaurant", restaurante);
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
		return RESTAURANTE_FORM;
	}

	@PostMapping(value = "/signup")
	public String processCreationForm(@Valid Restaurante restaurant, BindingResult result, ModelMap model) {
		if (result.hasErrors()) { 
			return RESTAURANTE_FORM; 
		} 
		else { 
			List<String> lista = new ArrayList<>(); 
			userService.findAllUser().forEach(x->lista.add(x.getUsername())); 
			if(lista.contains(restaurant.getUser().getUsername())){ 
				ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class)); 
				Restaurante restaurante = new Restaurante(); 
				model.put("restaurant", restaurante); 
				model.put("error", true); 
				model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes); 
				return RESTAURANTE_FORM; 
			} else { 
				this.restauranteService.save(restaurant); 
				return "redirect:/login"; 
			} 
		}
	}
	
	// Crear/Editar productos
  
	@GetMapping(value = "/{id}/product/new")
	public String initCreationForm(@PathVariable("id") Integer id, Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		model.put("restaurante_id", id);
		return CARTA_FORM;
	}

	@PostMapping(value = "/{id}/product/new")
	public String processCreationForm(@PathVariable("id") Integer id, @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return CARTA_FORM;
		}
		else{
			int idPlato = restauranteService.findAllMenu().size()+1;
			product.setId(idPlato);
			product.setRestaurant(restauranteService.findRestaurantById(id).get());
			this.productService.save(product);
			return "redirect:/restaurante/{id}/detalles/carta";
			}
	}
	
	@GetMapping(value = "/{id_restaurante}/{id}/product/edit")
	public String initUpdateProductForm(@PathVariable("id") int id, @PathVariable("id_restaurante") int idRestaurante, Model model) {
		Product product = this.productService.findProductById(id);
		model.addAttribute(product);
		return CARTA_FORM;
	}

	@PostMapping(value = "/{id_restaurante}/{id}/product/edit")
	public String processUpdateOwnerForm(@Valid Product product, BindingResult result,
			@PathVariable("id") int id, @PathVariable("id_restaurante") int idRestaurante) {
		if (result.hasErrors()) {
			return CARTA_FORM;
		}
		else {
			product.setId(id);
			product.setRestaurant(restauranteService.findRestaurantById(idRestaurante).get());
			this.productService.save(product);
			return "redirect:/restaurante/{id_restaurante}/detalles/carta";
		}
	}


}
