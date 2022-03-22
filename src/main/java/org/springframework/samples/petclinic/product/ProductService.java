package org.springframework.samples.petclinic.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.product.Product;
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
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}	


	public Collection<Product> findAll(){
        return productRepository.findAll();
    }
	
	public List<String> getAllNames(){
		List<String> names = new ArrayList<>();
		for(Product products : findAll()){
		  names.add(products.getName());
		}
		return names;
	  }


	public void save(@Valid Product product) throws DataAccessException {
		productRepository.save(product);
	}




}