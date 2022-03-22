package org.springframework.samples.commandfast.restaurantes;
import org.springframework.samples.commandfast.restaurants.Restaurant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RestauranteRepository  extends CrudRepository<Restaurant, Integer> {

	@Query("SELECT restaurant FROM Restaurant restaurant")
	public Collection<Restaurant> findAll();


	


}
    

