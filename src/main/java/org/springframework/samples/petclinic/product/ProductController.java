package org.springframework.samples.petclinic.product;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/productos")
public class ProductController {

	//TODO
	private static final String RESTAURANTE_FORM = "URL_FORM";

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/new")
    public String showGameCreationForm(Map<String, Object> model) {

        Product p = new Product();
        model.put("producto", p);
        

        return "/";
    }

    @PostMapping("/new")
    public String createGame(@ModelAttribute("producto") @Valid Product product, BindingResult result, Map<String, Object> model){
        if(result.hasErrors()) {
            return "/PAGINA DE ERROR";

        }if(productService.getAllNames().contains(product.getName())) {
			result.rejectValue("name", "namex2", "Ya existe un productocon este nombre");
			return "/VUELVE A LA CREACIÓN";

		}else {
		this.productService.save(product);

        return "/SE VA AL LISTADO CON LA CREACOÓN HECHA";
	}
}
    

}

