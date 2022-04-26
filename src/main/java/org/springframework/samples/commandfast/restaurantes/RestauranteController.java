package org.springframework.samples.commandfast.restaurantes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.google.zxing.WriterException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.payments.Payment;
import org.springframework.samples.commandfast.payments.PaymentService;
import org.springframework.samples.commandfast.product.Product;
import org.springframework.samples.commandfast.product.ProductService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.samples.commandfast.valoracion.ValoracionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/restaurante")

public class RestauranteController {
	private static final String STRING_EMPTY_CARD = "conTarjetaVacio";
	private static final String STRING_CASH_EMPTY = "conEfectivoVacio";
	private static final String STRING_ANONYMOUS_USER = "anonymousUser";
	private static final String STRING_USER_NAME = "username";
	private static final String STRING_LISTA_TIPOS = "listaTipos";

	private static final String RESTAURANTE_FORM = "restaurantes/createRestaurantForm";
	private static final String CARTA_FORM = "carta/addProduct";
	
	private final RestauranteService restauranteService;
	private final ProductService productService;
	private final UserService userService;
	private final PaymentService paymentService;
	private final CommandService commandService;
	private final ValoracionService valoracionService;
	private final NotificationService notificationService;

	@Value("${STRIPE_PUBLIC_KEY}")
    private String apiPublicKey;

	@Autowired
	public RestauranteController(RestauranteService restauranteService, ProductService productService, UserService userService, PaymentService paymentService, CommandService commandService, ValoracionService valoracionService, NotificationService notificationService) { 
		this.restauranteService = restauranteService; 
		this.productService = productService; 
		this.userService = userService; 
		this.paymentService = paymentService;
		this.commandService = commandService;
		this.valoracionService = valoracionService;
		this.notificationService = notificationService;

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
	public String showRestautanteToSearch(@RequestParam("inputPlace") String place, Map<String, Object> model, @RequestParam("inputState") String type,@RequestParam("inputValor") String valor, HttpServletRequest request) {
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
		if (!(valor.equals("Selecciona una opción"))) {
			if(valor.equals("Menos valorados")){
				lrestaurantes.sort(Comparator.comparing(Restaurante::getValoracionMedia));
				
			}else if(valor.equals("Más valorados")){
				lrestaurantes.sort(Comparator.comparing(Restaurante::getValoracionMedia).reversed());

			
		}}
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
		model.put("valoracion",  String.format("%.2f", restaurante.get().getValoracionMedia()));
		return "restaurantes/detalles";
	}
	
	@GetMapping(value = {"/{id}/detalles/qr"})
	public String showQRGenerator(@PathVariable("id") Integer id, Map<String, Object> model) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		model.put("restaurante", restaurante.get());
		model.put("id_restaurante", restaurante.get().getId());
		return "restaurantes/qr";
	}
	
	@GetMapping(value = {"/{id}/detalles/qr/descargar"})
	public String downloadQR(@PathVariable("id") Integer id, Map<String, Object> model, @RequestParam("numero_mesa") String numero_mesa, HttpServletRequest request, HttpServletResponse response) {
		Optional<Restaurante> restaurante = restauranteService.findRestaurantById(id);
		model.put("restaurante", restaurante.get());
		model.put("id_restaurante", restaurante.get().getId());

        String base= request.getLocalName();
        String url = "";
        if(base.equals("localhost")) {
        	url = "http://localhost:8080/command/new/"+id.toString()+"/"+numero_mesa;
        }else {
        	url = "https://command-fast-app-s3.herokuapp.com/"+id.toString()+"/"+numero_mesa;
        }
        String fileName = "qr.png";
        // Generate and Save Qr Code Image 
        try {
			QRCodeGenerator.generateQRCodeImage(url,250,250,fileName);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        	//download pdf
      		File file = new File(fileName);
      		response.setContentType("application/octet-stream");
      		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName()); 
      		try {
      			ServletOutputStream outputStream = response.getOutputStream();
      			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
      			
      			byte[] buffer = new byte[8192]; //8k buffer
      			int bytesRead = -1;
      			
      			while((bytesRead = inputStream.read(buffer)) != -1) {
      				outputStream.write(buffer, 0, bytesRead);
      			}
      			
      			inputStream.close();
      			outputStream.flush();
      			outputStream.close();
      			
      		} catch (IOException e) {
      			e.printStackTrace();
      		}
        
		return "restaurantes/qr";
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
		model.put("stripePublicKey", apiPublicKey);
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		Restaurante restaurante = new Restaurante();
		model.put("error", false); 
		model.put("restaurante", restaurante);
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
		return RESTAURANTE_FORM;
	}

	@PostMapping(value = "/signup")
	public String processCreationForm(@Valid Restaurante restaurant, BindingResult result, ModelMap model) {
		model.put("stripePublicKey", apiPublicKey);
		if (result.hasErrors()) {
			
			if(restaurant.getType().isEmpty()) model.put("error_tipos", true);
			
			else model.put("error_tipos", false);
			
			ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
			model.put("listaTipos", listaTipoRestaurantes);
		}
		if (result.hasErrors()) { 
			return RESTAURANTE_FORM; 
		}
		else {
			List<String> lista = new ArrayList<>(); 
			userService.findAllUser().forEach(x->lista.add(x.getUsername()));
			
			if(lista.contains(restaurant.getUser().getUsername())){
				if(restaurant.getType().isEmpty()) model.put("error_tipos", true);
				else model.put("error_tipos", false);
				
				ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class)); 
				Restaurante restaurante = new Restaurante(); 
				model.put("restaurant", restaurante);
				model.put("error", true);
				model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
				
				return RESTAURANTE_FORM; 
			} else if(restaurant.getType().isEmpty()) {
				ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class)); 
				Restaurante restaurante = new Restaurante();
				model.put("restaurant", restaurante);
				model.put("error_tipos", true);
				model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
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
		if(!principal.equals(STRING_ANONYMOUS_USER)) {
			String username = request.getUserPrincipal().getName();
			
			Restaurante restauranteSesion = restauranteService.findByUsername(username);
			Integer idSesionRestaurante = restauranteSesion.getId();
			model.put("sesionRestaurant", restauranteSesion);
			
			Collection<Command> listaComandas = commandService.findCommandsOfARestaurant(idSesionRestaurante);
			
			if(listaComandas.isEmpty()) {
				model.put(STRING_EMPTY_CARD, true);
				model.put(STRING_CASH_EMPTY, true);
			} else {
				model.put("listaComandas", listaComandas);
				List<Payment>payments = paymentService.getAllPayments();
				model.put("payments", payments);
				
				List<Payment> conTarjeta = new ArrayList<>();
				List<Payment> conEfectivo = new ArrayList<>();
				
				for(Payment pago: payments) {
					for(Command comandaSet: pago.getTable().getCommands()) {
						if(comandaSet.getRestaurante().getId().equals(idSesionRestaurante)) {
							if(pago.getPayHere() && pago.getCreditCard() == false) {
								conEfectivo.add(pago);
								break;
							} else if (pago.getPayHere() && pago.getCreditCard()) {
								conTarjeta.add(pago);
								break;
							}
						}
					}
				}
				if(conTarjeta.isEmpty() && !conEfectivo.isEmpty()) {
					model.put(STRING_EMPTY_CARD, true);
				} else if (conEfectivo.isEmpty() && !conTarjeta.isEmpty()) {
					model.put(STRING_CASH_EMPTY, true);
				} else if(conTarjeta.isEmpty() && conEfectivo.isEmpty()) {
					model.put(STRING_EMPTY_CARD, true);
					model.put(STRING_CASH_EMPTY, true);
				}
				model.put("conTarjeta", conTarjeta);
				model.put("conEfectivo", conEfectivo);
			}
			return "restaurantes/payments";
		} else {
			return "/";
		}
	}
	
	// Notifications
	
	@GetMapping(value = "/notify/clear/{id_notification}")
	public String notifyClear(Map<String, Object> model, @PathVariable("id_notification") Integer id_notification, RedirectAttributes redirectAttrs) {
		// update notification
		Notification notif = new Notification();
		notif = notificationService.findNotificationById(id_notification);
		notif.setAtendido(1);
		try {
			this.notificationService.saveNotification(notif);
			redirectAttrs.addFlashAttribute("message", "Se ha marcado como atendida.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return ("redirect:/restaurante/notifications");
	}
	
	@GetMapping(value = "/notify/{id_comanda}")
	public String notify(Map<String, Object> model, @PathVariable("id_comanda") Integer id_comanda, RedirectAttributes redirectAttrs) {
		Optional<Command> comanda = commandService.findIdCommands(id_comanda);
		// create notification
		Notification notif = new Notification();
		notif.setAtendido(0);
		notif.setNumeroMesa(comanda.get().getMesa().getNumber());
		notif.setRestaurant(comanda.get().getRestaurante());
		System.out.println(notif.toString());
		try {
			this.notificationService.saveNotification(notif);
			redirectAttrs.addFlashAttribute("message", "Se ha notificado al camarero, por favor espere su llegada...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return ("redirect:/carta/"+id_comanda.toString());
	}
	
	// Ver si han solicitado camarero
	@GetMapping(value = "/notifications")
	public String notifications(Map<String, Object> model, HttpServletRequest request){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals(STRING_ANONYMOUS_USER)) {
			String username = request.getUserPrincipal().getName();
			
			Restaurante restauranteSesion = restauranteService.findByUsername(username);
			List<Notification> notificaciones = notificationService.findNotificationsByRestaurant(restauranteSesion.getId());

			model.put("sesionRestaurant", restauranteSesion);
			model.put("notificaciones", notificaciones);
			
			return "restaurantes/notifications";
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
		return CARTA_FORM;
	}

	@PostMapping(value = "/{id}/product/new")
	public String processCreationForm(@PathVariable("id") Integer id, @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return CARTA_FORM;
		}
		else{
			int lastIdx = restauranteService.findAllMenu().stream().collect(Collectors.toList()).size() - 1;
			int idPlato = restauranteService.findAllMenu().stream().collect(Collectors.toList()).get(lastIdx).getId()+1;
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
	
	@GetMapping(value = "/{id_restaurante}/{id}/product/delete")
	public String deleteProduct(@PathVariable("id") int id, @PathVariable("id_restaurante") int id_restaurante, ModelMap model) {
		productService.delete(id);
		model.addAttribute("message","Producto eliminado correctamente.");
		return "redirect:/restaurante/{id_restaurante}/detalles/carta";
	}
	
	@GetMapping(value = "/{id}/delete")
	public String deleteRestaurante(@PathVariable("id") int id, ModelMap model) {
		
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		restauranteService.delete(id);
		model.put("listaRestaurante", restauranteService.findAllRestaurants());
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);

		return "redirect:/restaurante/list";
	}
	
	@GetMapping("/editar")
	public String editarRestaurante(ModelMap model) {
	
		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		Restaurante restaurante = restauranteService.obtenerRestaurante();
		model.put("error", false); 
		model.put("restaurante", restaurante);
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
		return "restaurantes/editarRestauranteForm";
	}
	
	@PostMapping(value = "/editar")
	public String editarRestaurante(@Valid Restaurante restaurante,BindingResult result, ModelMap model,HttpServletRequest request) {
		Restaurante restaurant = restauranteService.obtenerRestaurante();
		if (result.hasErrors()) {
			
			if(restaurant.getType().isEmpty()) model.put("error_tipos", true);
			
			else model.put("error_tipos", false);
			
			ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
			model.put("listaTipos", listaTipoRestaurantes);
			return "restaurantes/editarRestauranteForm"; 
		}
		else {

			
		
			}if(restaurant.getType().isEmpty()) {
				ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class)); 
				model.put("restaurant", restaurant);
				model.put("error_tipos", true);
				model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
				return "restaurantes/editarRestauranteForm";
			} else {
				
				List<RestauranteType> aux = new ArrayList<>();
				
				String[] tipos = request.getParameter("type").split(",");
				 
				for (String s: tipos) {
					aux.add(RestauranteType.valueOf(s));
				}
				
				restaurant.getUser().setPassword(request.getParameter("user.password"));
				restaurant.setName(request.getParameter("name"));
				restaurant.setTelephone(request.getParameter("telephone"));
				restaurant.setEmail(request.getParameter("email"));
				restaurant.setCity(request.getParameter("city"));
				restaurant.setAddress(request.getParameter("address"));
				restaurant.setDescription(request.getParameter("description"));
				restaurant.setCapacity(request.getParameter("capacity"));
				restaurant.setSchedule(request.getParameter("schedule"));
				restaurant.setType(aux);
				
				
				this.restauranteService.save(restaurant); 
				return "redirect:/welcome"; 
			} 
	
	}
	
	
	
	
}

	
	
	
	
	
	
	

	

