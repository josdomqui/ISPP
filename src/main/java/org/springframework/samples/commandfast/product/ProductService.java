package org.springframework.samples.commandfast.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
*/
@Service
public class ProductService {

	
	private ProductRepository productRepository;	
	

	@Autowired
	public ProductService(ProductRepository productsRepository) {
		this.productRepository = productsRepository;
	}	


	public void save(Product producto) {
        productRepository.save(producto);
    }



}