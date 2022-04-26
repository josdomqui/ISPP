package org.springframework.samples.commandfast.restaurant;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.commandfast.configuration.SecurityConfiguration;
import org.springframework.samples.commandfast.restaurantes.RestauranteController;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestauranteControllerTests {
	
	@Autowired
	protected RestauranteController restauranteController;
	
    @Autowired  
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(restauranteController).isNotNull();
    }


    @WithMockUser
    @Test
    void testDeberiaListadoRestauranteAnonimo() throws Exception{
        mockMvc.perform(get("/restaurante/list")).andExpect(status().isOk()).andExpect(model().attributeExists("listaRestaurante")).andExpect(model().attributeExists("listaTipos"))
        .andExpect(view().name("restaurantes/listado"));

    }
    //TODO -> No entra en el segundo camino
    @WithMockUser(username = "anonymousUser", authorities = {"anonymousUser"})
    @Test
    void testDeberiaListadoRestauranteNoAnonimo() throws Exception{
        mockMvc.perform(get("/restaurante/list")).andExpect(status().isOk()).andExpect(model().attributeExists("listaRestaurante")).andExpect(model().attributeExists("listaTipos"))
        .andExpect(view().name("restaurantes/listado"));

    }
    /*
    @WithMockUser(username = "anonymousUser", authorities = {"anonymousUser"})
    @Test
    void testDeberiaListadoRestauranteBuscado() throws Exception{
        mockMvc.perform(get("/restaurante/list/search").param("inputPlace", "Sevilla").param("inputState", "No").param("inputValor", "No")).andExpect(status().isOk()).andExpect(view().name("restaurantes/listado"));

    }
    */
    @WithMockUser
    @Test
    void testDeberiaMostrarDetallesRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/{id}/detalles", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("detallesRestaurante")).andExpect(model().attributeExists("valoracion"))
        .andExpect(view().name("restaurantes/detalles"));

    }
    
    @WithMockUser
    @Test
    void testDeberiaGenerarQR() throws Exception{
        mockMvc.perform(get("/restaurante/{id}/detalles/qr", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("restaurante")).andExpect(model().attributeExists("id_restaurante"))
        .andExpect(view().name("restaurantes/qr"));

    }
    
    @WithMockUser
    @Test
    void testDeberiaMostrarMenuRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/{id}/detalles/carta", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("menu")).andExpect(model().attributeExists("products")).andExpect(model().attributeExists("restaurante"))
        .andExpect(view().name("restaurantes/carta"));

    }
    
    @WithMockUser
    @Test
    void testDeberiaMostarFormularioEditarRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/restaurantes/{id}/edit", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("restaurante")).andExpect(view().name("restaurantes/createRestaurantForm"));

    }
    
    
    
    @WithMockUser
    @Test
    void testNoDeberiaMostarFormularioEditarRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/restaurantes/{id}/edit", 8373)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/"));

    }
   
}