package org.springframework.samples.petclinic.product;



import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Integer> {
	
	void save(Product product) throws DataAccessException;

	public Collection<Product> findAll();
	
	//public List<Restaurant> findAll();

	void deleteById(int id) throws DataAccessException;

	
	
	

}

