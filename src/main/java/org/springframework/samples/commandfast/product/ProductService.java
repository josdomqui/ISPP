package org.springframework.samples.commandfast.product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.product.Product;
import org.springframework.samples.commandfast.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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