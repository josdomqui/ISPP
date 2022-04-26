package org.springframework.samples.commandfast.restaurantes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.product.Product;
import org.springframework.samples.commandfast.product.ProductRepository;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

	@Autowired
	private RestauranteRepository restauranteRepository;	
	@Autowired
	private ProductRepository productRepository;
	

	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Transactional
	public List<Restaurante> findAllRestaurants() throws DataAccessException{
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
		restauranteRepository.save(restaurant);		
	}
	
	@Transactional
	public Collection<Product> findAllMenu() throws DataAccessException{
		return restauranteRepository.findAllProduct();
	}
	
	@Transactional
	public Collection<Product> findMenuByRestaurant(Integer restaurantId) throws DataAccessException{
		return restauranteRepository.findProductsByRestaurant(restaurantId);
	}

    public List<Restaurante> findByType(RestauranteType restauranteType) {
        return restauranteRepository.findByType(restauranteType);
    }

	public List<Restaurante> findByCity(String city) {
        return restauranteRepository.findByCity(city);
    }
	
	public Restaurante findByUsername(String username) {
        return restauranteRepository.findByUsername(username);
    }

	public void save(Restaurante restaurante) {
		//Creating restaurant
		restauranteRepository.save(restaurante);
		//Creating user
		userService.saveUser(restaurante.getUser());
		//Creating authorities
    authoritiesService.saveAuthorities(restaurante.getUser().getUsername(), "restaurant");

    }

	@Transactional
	public void delete(Integer id) {
		productRepository.deleteProductById(id);
		restauranteRepository.deleteById(id);
		
	}
	
	@Transactional
	public Restaurante obtenerRestaurante() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		User currentUser=(User)authentication.getPrincipal();
		return findByUsername(currentUser.getUsername());
			
	}


}