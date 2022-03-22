package org.springframework.samples.commandfast.restaurants;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.commandfast.product.Product;
public interface RestaurantRepository extends Repository<Restaurant, Integer> {
	
	void save(Restaurant restaurant) throws DataAccessException;

	@Query("SELECT restaurant FROM Restaurant restaurant")
	public Collection<Restaurant> findAll();

	
	@Query("SELECT product FROM Product product")
	public Collection<Product> findAllProduct();

	@Query("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.id =:id")
	public Optional<Restaurant> findById(@Param("id") int id);
	

	//public List<Restaurant> findAll();

	void deleteById(int id) throws DataAccessException;

	
	
	

}

