package org.springframework.samples.commandfast.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all commandfast controllers Also a placeholder
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

	@Transactional
	public Product findProductById(Integer id){
		return productRepository.findProductById(id);
	}

}