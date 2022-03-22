package org.springframework.samples.commandfast.restaurantes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.commandfast.product.Product;

public interface RestauranteRepository  extends Repository<Restaurante, Integer> {

	void save(Restaurante restaurant) throws DataAccessException;

	@Query("SELECT restaurant FROM Restaurante restaurant")
	public Collection<Restaurante> findAll();

	@Query("SELECT product FROM Product product")
	public Collection<Product> findAllProduct();

	@Query("SELECT restaurant FROM Restaurante restaurant WHERE restaurant.id =:id")
	public Optional<Restaurante> findById(@Param("id") int id);
	

	//public List<Restaurant> findAll();

	void deleteById(int id) throws DataAccessException;
	


}
    

