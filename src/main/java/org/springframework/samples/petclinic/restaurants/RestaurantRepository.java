package org.springframework.samples.petclinic.restaurants;



import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface RestaurantRepository extends Repository<Restaurant, Integer> {
	
	void save(Restaurant restaurant) throws DataAccessException;

	@Query("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.id =:id")
	public Optional<Restaurant> findById(@Param("id") int id);

	public List<Restaurant> findAll();

	@Query("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.type =:type")
	public List<Restaurant> findByType(@Param("type") RestaurantType type);

	
	@Query("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.city =:city")
	public List<Restaurant> findByCity(@Param("city") String city);
	

	void deleteById(int id) throws DataAccessException;

}

