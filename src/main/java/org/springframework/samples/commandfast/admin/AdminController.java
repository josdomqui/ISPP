package org.springframework.samples.commandfast.admin;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.payments.Payment;
import org.springframework.samples.commandfast.payments.PaymentService;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RestauranteService restauranteService;

	private static final String STRING_LISTA_TIPOS = "listaTipos";
	private static final String FORM_EDIT_RESTAURANTS = "restaurantes/editarRestauranteForm";

	@GetMapping(value = "/admin/paymentPanel")
	public String payments(Map<String, Object> model) {

		List<Payment> payments = paymentService.getAllPayments();
		model.put("payments", payments);
		return "admin/payments";
	}

	@GetMapping("/admin/editarRestaurante/{restauranteId}")
	public String editarRestaurante(ModelMap model, @PathVariable("restauranteId") Integer id) {

		ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
		Restaurante restaurante = restauranteService.findRestaurantById(id).get();
		model.put("error", false);
		model.put("restaurante", restaurante);
		model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
		return FORM_EDIT_RESTAURANTS;
	}

	@PostMapping(value = "/admin/editarRestaurante/{restauranteId}")
	public String editarRestaurante(@Valid Restaurante restaurante, BindingResult result, ModelMap model,
			HttpServletRequest request, @PathVariable("restauranteId") Integer id) {
		Restaurante restaurant = restauranteService.findRestaurantById(id).get();
		if (result.hasErrors()) {
			Boolean error = null;
			if (restaurant.getType().isEmpty()) {
				error = true;

			} else {
				error = false;
			}
			model.put("error_tipos", error);
			ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
			model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
			return FORM_EDIT_RESTAURANTS;
		}
		
		if (restaurant.getType().isEmpty()) {
			ArrayList<RestauranteType> listaTipoRestaurantes = new ArrayList<>(EnumSet.allOf(RestauranteType.class));
			model.put("restaurant", restaurant);
			model.put("error_tipos", true);
			model.put(STRING_LISTA_TIPOS, listaTipoRestaurantes);
			return FORM_EDIT_RESTAURANTS;
		} else {

			List<RestauranteType> aux = new ArrayList<>();

			String[] tipos = request.getParameter("type").split(",");

			for (String s : tipos) {
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
