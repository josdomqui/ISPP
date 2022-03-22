package org.springframework.samples.commandfast.restaurantes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
*/
@Service
public class RestauranteService {

	private RestauranteRepository restauranteRepository;	
	


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
	public List<Restaurante> findAllRestaurants() throws DataAccessException{
		return restauranteRepository.findAll();
	}


    public List<Restaurante> findByType(RestauranteType restauranteType) {
        return restauranteRepository.findByType(restauranteType);
    }

	public List<Restaurante> findByCity(String city) {
        return restauranteRepository.findByCity(city);
    }

}