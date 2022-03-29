package org.springframework.samples.commandfast.restaurant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.springframework.samples.commandfast.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RestaurantServiceTests {

    @Autowired
    private RestauranteService restaurantService;
    EntityManager em;



    @Test
    void shouldFindAll(){
        Collection<Restaurante> r = this.restaurantService.findAllRestaurants();
       assertThat(r.size()!=0);
    }

    
    @Test
    void shouldInsertRestaurant(){
        Collection<Restaurante> restaurant=this.restaurantService.findAllRestaurants();
        int found = restaurant.size();

        Restaurante r = new Restaurante();
        r.setAddress("c viga");
        r.setCapacity(12);
        r.setCity("Granada");
        r.setDescription("descrition_newwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        //r.setEmail("email@gmail.com");
        r.setName("NuevoNombre");
        r.setPhoto("photo");
        r.setSchedule("schedule");
        r.setTelephone("622423142");
        List<RestauranteType> l =  new ArrayList<>();
        l.add(RestauranteType.BAR);
        l.add(RestauranteType.CERVECERIA);
        r.setType(l);
        this.restaurantService.save(r);
        
        Collection<Restaurante> restaurants = this.restaurantService.findAllRestaurants();
        assertThat(restaurants.size()==(found + 1));

    }

    @Test
    void shouldFindRestaurant(){
        String city = this.restaurantService.findAllRestaurants().get(0).getCity();
        assertThat(this.restaurantService.findByCity(city));

    }

    @Test
    void shouldFindRestaurantById(){
        Integer id = this.restaurantService.findAllRestaurants().get(0).getId();
        assertThat(this.restaurantService.findRestaurantById(id));

    }

    @Test
    void shouldFindMenuByRestaurant(){
        Integer id = this.restaurantService.findAllRestaurants().get(0).getId();
        assertThat(this.restaurantService.findMenuByRestaurant(id));
    }

    // @Test
    // void shouldFindRestaurantByType(){
    //     Restaurante r = this.restaurantService.findAllRestaurants().get(0);
    //     Collection<Restaurante>  c = this.restaurantService.findByType(RestauranteType.DOS_TENEDORES);
    //     assertThat(c.stream().collect(Collectors.toList()).contains(r));
    // }

    @Test
    void shouldFindAllMenu(){
        Collection<Product> p = this.restaurantService.findAllMenu();
       assertThat(p.size()!=0);
    }

   
}
