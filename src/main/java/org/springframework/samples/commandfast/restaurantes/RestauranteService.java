package org.springframework.samples.commandfast.restaurantes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.restaurants.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all commandfast controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
*/
@Service
public class RestauranteService {

	private RestauranteRepository restauranteRepository;	
	


	@Autowired
	public RestauranteService(RestauranteRepository restauranteRepository) {
		this.restauranteRepository = restauranteRepository;
	}	



	
	@Transactional
	public Collection<Restaurant> findAllRestaurants() throws DataAccessException{
		return restauranteRepository.findAll();
	}

}