package org.springframework.samples.commandfast.restaurantes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.product.Product;
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

	@Transactional
	public Collection<Restaurante> findAllRestaurants() throws DataAccessException{
		return restauranteRepository.findAll();
	}

	@Autowired
	public RestauranteService(RestauranteRepository restaurantsRepository) {
		this.restauranteRepository = restaurantsRepository;
	}	


	@Transactional(readOnly = true)
	public Optional<Restaurante> findRestaurantById(int id) throws DataAccessException {
		return restauranteRepository.findById(id);
	}
	
	@Transactional
	public void saveRestaurant(Restaurante restaurant) throws DataAccessException {
		//creating owner
		restauranteRepository.save(restaurant);		
	}
	
	
	@Transactional
	public Collection<Product> findAllMenu() throws DataAccessException{
		return restauranteRepository.findAllProduct();
	}

}