package org.springframework.samples.commandfast.payments;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.mesa.MesaService;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentController {
	

	private final CommandService commandService;
	private final PaymentService paymentService;
	
	@Autowired
	public PaymentController(UserService userService, AuthoritiesService authoritiesService, MesaService mesaService, CommandService commandService, PaymentService paymentService) {

		this.commandService = commandService;
		this.paymentService = paymentService;
	}
	
	@GetMapping(value = "/payment/{id_comanda}")
	public String payOrder(@PathVariable("id_comanda") int id_commanda,  Map<String, Object> model) {
		Optional<Command> command = commandService.findIdCommands(id_commanda);
		this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		
		return "welcome";
		
	}
	
	@GetMapping(value = "/payment/cash/{id_comanda}")
	public String payHereCash(@PathVariable("id_comanda") int id_commanda,  Map<String, Object> model){
		Optional<Command> command = commandService.findIdCommands(id_commanda);	
		this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		
		return "welcome";
		
	}
	
	@GetMapping(value = "/payment/creditCard/{id_comanda}")
	public String payHerecCard(@PathVariable("id_comanda") int id_commanda,  Map<String, Object> model){
		Optional<Command> command = commandService.findIdCommands(id_commanda);
		
		Payment payment =this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		payment.setCreditCard(true);
		this.paymentService.savePayment(payment);
		
		return "welcome";
		
	}

}
