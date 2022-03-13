package org.springframework.samples.petclinic.restaurants;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
*/
@Service
public class RestaurantService {

	private RestaurantRepository restaurantRepository;	
	


	@Autowired
	public RestaurantService(RestaurantRepository restaurantsRepository) {
		this.restaurantRepository = restaurantsRepository;
	}	


	@Transactional(readOnly = true)
	public Optional<Restaurant> findRestaurantById(int id) throws DataAccessException {
		return restaurantRepository.findById(id);
	}
	@Transactional
	public void saveRestaurant(Restaurant restaurant) throws DataAccessException {
		//creating owner
		restaurantRepository.save(restaurant);		
	}
	
	@Transactional
	public List<Restaurant> findAllRestaurants() throws DataAccessException{
		return restaurantRepository.findAll();
	}

}