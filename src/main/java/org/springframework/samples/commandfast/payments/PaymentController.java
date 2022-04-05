package org.springframework.samples.commandfast.payments;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${STRIPE_PUBLIC_KEY}")
    private String API_PUBLIC_KEY;
	
	@Autowired
	public PaymentController(UserService userService, AuthoritiesService authoritiesService, MesaService mesaService, CommandService commandService, PaymentService paymentService) {

		this.commandService = commandService;
		this.paymentService = paymentService;
		
	}
	
	@GetMapping(value = "/payment/{id_comanda}")
	public String payOrder(@PathVariable("id_comanda") int id_comanda,  Map<String, Object> model) {
		Optional<Command> command = commandService.findIdCommands(id_comanda);
		this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		model.put("stripePublicKey", API_PUBLIC_KEY);
		model.put("price", command.get().getPrice());
		model.put("id_comanda", id_comanda);
		return "payment/charge";
	}
	
	@GetMapping(value = "/payment/subscription")
	public String subscription(Map<String, Object> model) {
		model.put("stripePublicKey", API_PUBLIC_KEY);
		return "payment/subscription";
	}
	
	@GetMapping(value = "/payment/successPage/{id_comanda}")
	public String paymentSuccessPage(@PathVariable("id_comanda") int id_comanda, Map<String, Object> model){
		model.put("id_comanda", id_comanda);
		return "payment/success";
	}
	
	@GetMapping(value = "/payment/downloadRecipt/{id_comanda}")
	public void downloadRecipt(@PathVariable("id_comanda") int id_comanda, Map<String, Object> model, HttpServletResponse response){
		//get price
		Optional<Command> command = commandService.findIdCommands(id_comanda);
		Double price = command.get().getPrice();
		//generate pdf
		String file_name = this.paymentService.generateRecipt(price);
		System.out.println(file_name);
		//download pdf
		File file = new File(file_name);
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
	}

	
	@GetMapping(value = "/payment/waitPage")
	public String paymentWaitPage(Map<String, Object> model){		
		
		return "payment/wait";
		
	}
	
	@GetMapping(value = "/payment/cash/{id_comanda}")
	public String payHereCash(@PathVariable("id_comanda") int id_commanda,  Map<String, Object> model){
		Optional<Command> command = commandService.findIdCommands(id_commanda);	
		Payment payment = this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		payment.setPayHere(true);
		this.paymentService.savePayment(payment);
		
		return "redirect:/payment/waitPage";
		
	}
	
	@GetMapping(value = "/payment/creditCard/{id_comanda}")
	public String payHerecCard(@PathVariable("id_comanda") int id_commanda,  Map<String, Object> model){
		Optional<Command> command = commandService.findIdCommands(id_commanda);
		
		Payment payment =this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		payment.setCreditCard(true);
		payment.setPayHere(true);
		this.paymentService.savePayment(payment);
		
		return "redirect:/payment/waitPage";
		
	}

}
