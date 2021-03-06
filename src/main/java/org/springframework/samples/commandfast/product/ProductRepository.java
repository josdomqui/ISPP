package org.springframework.samples.commandfast.product;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends Repository<Product, Integer> {
    void save(Product product) throws DataAccessException;
	
    @Query("SELECT p FROM Product p WHERE p.id =:id")
	public Product findProductById(@Param("id") int id);
    
    @Modifying
    @Query("DELETE FROM Product p WHERE p.restaurant.id =:id")
	void deleteProductById(@Param("id") int id);
    
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id =:id")
	void deleteProductByProductId(@Param("id") int id);
    

}