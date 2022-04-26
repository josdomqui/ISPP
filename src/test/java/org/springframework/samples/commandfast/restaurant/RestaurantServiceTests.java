package org.springframework.samples.commandfast.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.samples.commandfast.product.Product;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.samples.commandfast.user.User;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class RestaurantServiceTests {

	@Autowired
	private RestauranteService restaurantService;
	private UserService userService;
	EntityManager em;

	@Test
	void shouldFindAll() {
		Collection<Restaurante> r = this.restaurantService.findAllRestaurants();
		assertThat(r.isEmpty()).isFalse();
	}

	@Ignore
	@Test
	void shouldInsertRestaurant() {
		Collection<Restaurante> restaurant = this.restaurantService.findAllRestaurants();
		int found = restaurant.size();

		User user = new User();
    	user.setUsername("Martin");
    	user.setPassword("pass1234");
    	user.setEnabled(Boolean.TRUE);
    
    	this.userService.saveUser(user);

		Restaurante r = new Restaurante();
		r.setId(9);
		r.setName("Maritn Avecilla");
		r.setAddress("	Avenida la borbolla 3");
		r.setCity("Granada");
		r.setDescription("Establecimiento de bebidas y cafes");
		r.setTelephone("622423142");
		r.setPhoto("/resources/images/bar1.jpg");
		r.setCapacity("12");
		r.setSchedule("Lunes/Viernes 10:00 - 20:00");
		r.setEmail("email@gmail.com");
		r.setValoracionMedia(2.5);
		List<RestauranteType> l = new ArrayList<RestauranteType>();
		l.add(RestauranteType.BAR);
		l.add(RestauranteType.CERVECERIA);
		r.setType(l);
		r.setUser(user);

		this.restaurantService.save(r);

		Collection<Restaurante> restaurants = this.restaurantService.findAllRestaurants();
		assertThat(restaurants.size()).isEqualTo(found + 1);

	}

	@Test
	void shouldFindRestaurant() {
		String city = "Sevilla";
		assertThat(this.restaurantService.findRestaurantById(1).get().getCity()).isEqualTo(city);

	}

	@Test
	void shouldFindRestaurantById() {
		assertThat(this.restaurantService.findRestaurantById(1).get().getId()).isEqualTo(1);

	}

	@Test
	void shouldFindMenuByRestaurant() {
		assertThat(this.restaurantService.findMenuByRestaurant(1).isEmpty()).isFalse();
	}

	@Test
	void shouldFindAllMenu() {
		Collection<Product> p = this.restaurantService.findAllMenu();
		assertThat(p.isEmpty()).isFalse();
	}

	@Test
	void shouldFindRestaurants() {
		Restaurante re = this.restaurantService.findRestaurantById(1).get();
		assertThat(this.restaurantService.findAllRestaurants().contains(re)).isTrue();
	}

	@Test
	void shouldEditRestaurants() {
		Restaurante re = this.restaurantService.findRestaurantById(1).get();
		re.setCity("Nueva York");
		this.restaurantService.save(re);
		assertThat(re.getCity()).isEqualTo("Nueva York");
	}

	@Test
	void shouldDeleteRestaurants() {
		Restaurante re = this.restaurantService.findRestaurantById(1).get();
		this.restaurantService.delete(re.getId());
		assertThat(this.restaurantService.findAllRestaurants().contains(re)).isFalse();
	}

}
