package org.springframework.samples.commandfast.restaurantes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.payments.Payment;
import org.springframework.samples.commandfast.payments.PaymentService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

	private static final String RESTAURANTE_FORM = "restaurantes/createRestaurantForm";

	private final RestauranteService restauranteService;
	private final ProductService productService;
	private final UserService userService;
	private final PaymentService paymentService;
	private final CommandService commandService;

	@Autowired
	public RestauranteController(RestauranteService restauranteService, ProductService productService, UserService userService, PaymentService paymentService, CommandService commandService) { 
		this.restauranteService = restauranteService; 
		this.productService = productService; 
		this.userService = userService; 
		this.paymentService = paymentService;
		this.commandService = commandService;
	}

    @GetMapping(value = { "/list" })
	public String showRestautanteList(Map<String, Object> model, HttpServletRequest request) {
		
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		model.put("listaTipos", listaTipoRestaurantes);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			model.put("username", request.getUserPrincipal().getName());
		}
		return "restaurantes/listado";
	}

	@GetMapping(value = { "/list/search" })
	public String showRestautanteToSearch(@RequestParam("inputPlace") String place, Map<String, Object> model, @RequestParam("inputState") String type, HttpServletRequest request) {
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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			model.put("username", request.getUserPrincipal().getName());
		}
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
	public String showMenuRestaurant(@PathVariable("id") Integer id, Map<String, Object> model, HttpServletRequest request) {
		Optional<Restaurante> restauranteMenu = restauranteService.findRestaurantById(id);
		model.put("menu", restauranteMenu.get());
		model.put("products", restauranteService.findMenuByRestaurant(id));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			model.put("username", request.getUserPrincipal().getName());
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
		model.put("listaTipos", listaTipoRestaurantes);
		return RESTAURANTE_FORM;
	}

	@PostMapping(value = "/signup")
	public String processCreationForm(@Valid Restaurante restaurant, BindingResult result, ModelMap model) {
		if (result.hasErrors()) { 
			return RESTAURANTE_FORM; 
		} 
		else { 
			List<String> lista = new ArrayList<String>(); 
			userService.findAllUser().forEach(x->lista.add(x.getUsername())); 
			if(lista.contains(restaurant.getUser().getUsername())){ 
				ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class)); 
				Restaurante restaurante = new Restaurante(); 
				model.put("restaurant", restaurante); 
				model.put("error", true); 
				model.put("listaTipos", listaTipoRestaurantes); 
				return RESTAURANTE_FORM; 
			} else { 
				this.restauranteService.save(restaurant); 
				return "/payment/subscription"; 
			} 
		}
	}
	
	//Payment de restaurante
	
	@GetMapping(value = "/paymentPanel")
	public String payments(Map<String, Object> model, HttpServletRequest request){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			String username = request.getUserPrincipal().getName();
			
			Restaurante restauranteSesion = restauranteService.findByUsername(username);
			Integer idSesionRestaurante = restauranteSesion.getId();
			model.put("sesionRestaurant", restauranteSesion);
			
			Collection<Command> listaComandas = commandService.findCommandsOfARestaurant(idSesionRestaurante);
			
			if(listaComandas.isEmpty()) {
				model.put("conTarjetaVacio", true);
				model.put("conEfectivoVacio", true);
			} else {
				model.put("listaComandas", listaComandas);
				List<Payment>payments = paymentService.getAllPayments();
				model.put("payments", payments);
				
				List<Payment> conTarjeta = new ArrayList<Payment>();
				List<Payment> conEfectivo = new ArrayList<Payment>();
				
				for(Payment pago: payments) {
					for(Command comandaSet: pago.getTable().getCommands()) {
						if(comandaSet.getRestaurante().getId() == idSesionRestaurante) {
							if(pago.getPayHere() == true && pago.getCreditCard() == false) {
								conEfectivo.add(pago);
								break;
							} else if (pago.getPayHere() == true && pago.getCreditCard() == true) {
								conTarjeta.add(pago);
								break;
							}
						}
					}
				}
				if(conTarjeta.isEmpty() && !conEfectivo.isEmpty()) {
					model.put("conTarjetaVacio", true);
				} else if (conEfectivo.isEmpty() && !conTarjeta.isEmpty()) {
					model.put("conEfectivoVacio", true);
				} else if(conTarjeta.isEmpty() && conTarjeta.isEmpty()) {
					model.put("conTarjetaVacio", true);
					model.put("conEfectivoVacio", true);
				}
				model.put("conTarjeta", conTarjeta);
				model.put("conEfectivo", conEfectivo);
			}
			return "restaurantes/payments";
		} else {
			return "/";
		}
	}
	
	// Crear/Editar productos
  
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
	
	@GetMapping(value = "/{id_restaurante}/{id}/product/edit")
	public String initUpdateProductForm(@PathVariable("id") int id, @PathVariable("id_restaurante") int id_restaurante, Model model) {
		Product product = this.productService.findProductById(id);
		model.addAttribute(product);
		return "carta/addProduct";
	}

	@PostMapping(value = "/{id_restaurante}/{id}/product/edit")
	public String processUpdateOwnerForm(@Valid Product product, BindingResult result,
			@PathVariable("id") int id, @PathVariable("id_restaurante") int id_restaurante) {
		if (result.hasErrors()) {
			return "carta/addProduct";
		}
		else {
			product.setId(id);
			product.setRestaurant(restauranteService.findRestaurantById(id_restaurante).get());
			this.productService.save(product);
			return "redirect:/restaurante/{id_restaurante}/detalles/carta";
		}
	}


}
