package org.springframework.samples.commandfast.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.commandfast.payments.Payment;
import org.springframework.samples.commandfast.payments.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@Autowired
	private  PaymentService paymentService;
	

	@GetMapping(value = "/admin/paymentPanel")
	public String payments(Map<String, Object> model){
		
		
		List<Payment >payments = paymentService.getAllPayments();
		
		model.put("payments", payments);
		return "admin/payments";
		
	}

}
