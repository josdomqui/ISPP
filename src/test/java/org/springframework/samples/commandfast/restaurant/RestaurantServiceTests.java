package org.springframework.samples.commandfast.restaurant;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.samples.commandfast.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.samples.commandfast.user.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class RestaurantServiceTests {

    @Autowired
    private RestauranteService restaurantService;
    EntityManager em;

    
    @Test
    void shouldFindAll(){
    Collection<Restaurante> r = this.restaurantService.findAllRestaurants();
    assertThat(r.isEmpty()).isFalse();
    }

    
    @Test
    void shouldInsertRestaurant(){
    Collection<Restaurante> restaurant=this.restaurantService.findAllRestaurants();
    int found = restaurant.size();

    User user = new User();
    user.setUsername("Carlos");
    user.setPassword("pass1234");
    user.setEnabled(Boolean.TRUE);

    Restaurante r = new Restaurante();
    r.setId(9);
    r.setName("Jose");
    r.setAddress("c viga");
    r.setCity("Granada");
    r.setDescription("descrition_newwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
    r.setTelephone("622423142");
    r.setPhoto("photo");
    r.setCapacity("12");
    r.setSchedule("schedule");
    r.setEmail("email@gmail.com");
    List<RestauranteType> l =  new ArrayList<>();
    l.add(RestauranteType.BAR);
    l.add(RestauranteType.CERVECERIA);
    r.setType(l);
    r.setUser(user);
       
        
        this.restaurantService.save(r);
        
       Collection<Restaurante> restaurants = this.restaurantService.findAllRestaurants();
       assertThat(restaurants.size()).isEqualTo(found+1);

    }

    @Test
    void shouldFindRestaurant(){
    String city = "Sevilla";
    assertThat(this.restaurantService.findRestaurantById(1).get().getCity()).isEqualTo(city);

    }

    @Test
    void shouldFindRestaurantById(){
    assertThat(this.restaurantService.findRestaurantById(1).get().getId()).isEqualTo(1);

    }

    @Test
    void shouldFindMenuByRestaurant(){
    assertThat(this.restaurantService.findMenuByRestaurant(1).isEmpty()).isFalse();
    }


    @Test
    void shouldFindAllMenu(){
    Collection<Product> p = this.restaurantService.findAllMenu();
    assertThat(p.isEmpty()).isFalse();
   }

   @Test
   void shouldFindRestaurants(){
    Restaurante re = this.restaurantService.findRestaurantById(1).get();
    assertThat(this.restaurantService.findAllRestaurants().contains(re)).isTrue();
   }

}
