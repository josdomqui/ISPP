package org.springframework.samples.commandfast.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.samples.commandfast.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {

	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {

		return "welcome";
	}
	
	@PostMapping(value = "/")
	public String processCreationForm(HttpServletRequest request) {
		System.out.println("---------------------------------" + request.getParameter("city"));
		return "welcome";
	}
}
