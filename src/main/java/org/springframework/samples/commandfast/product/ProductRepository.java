package org.springframework.samples.commandfast.product;


import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Integer> {
    void save(Product product) throws DataAccessException;
	

	
	


}