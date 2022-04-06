package org.springframework.samples.commandfast.payment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.samples.commandfast.common.Response



@Controller
public class DemoController {
    // Reading the value from the application.properties file 
    @Value("${STRIPE_PUBLIC_KEY}")
    private String apiPublicKey;

    private DemoService stripeService;

    @Autowired
    public DemoController(DemoService stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping(value="/subscription")
    public String subscriptionPage(Map<String, Object> model) {

        return "welcome";
    }
    
    @GetMapping(value = "/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", apiPublicKey);
        return "payment/charge";
    }
    
    @PostMapping(value = "/charge")
    public String saveCharge(String email, String token, Map<String, Object> model) {
        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
        model.put("message", "Successful payment, your charde id is: "+ chargeId);
        System.out.println("BUENAAAASSSS===============================");
        return "welcome";
        
    }
    
}