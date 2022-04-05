package org.springframework.samples.commandfast.users;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsersServiceTests {

    @Autowired
    private UserService userService;
    EntityManager em;

    
    @Test
    void shouldFindAllUser(){
    Iterable<User> u = this.userService.findAllUser();
    List<User> result = 
    StreamSupport.stream(u.spliterator(), false)
    .collect(Collectors.toList());
    assertThat(result.size()!=0);
    }

    @Test
    void shouldFindUser(){
    User user = new User();
    user.setUsername("Carlos");
    user.setPassword("pass1234");
    user.setEnabled(Boolean.TRUE);
    this.userService.saveUser(user);
    
    User result = this.userService.findUser("Carlos").get();
    Iterable<User> u = this.userService.findAllUser();
    List<User> lista = 
    StreamSupport.stream(u.spliterator(), false)
    .collect(Collectors.toList());
    assertThat(lista.contains(u));
    }


}