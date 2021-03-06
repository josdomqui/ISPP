package org.springframework.samples.commandfast.web;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
class WelcomeControllerTests {
	
	@Autowired
	protected WelcomeController commandController;
	
    @Autowired  
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(commandController).isNotNull();
    }


    @WithMockUser
    @Test
    void testDeberiaMostrarBienvenida() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(model().attributeExists("listaRestaurante"))
        .andExpect(view().name("welcome"));

    }

    @WithMockUser
    @Test
    void testDeberiaMostrarTerminos() throws Exception{
        mockMvc.perform(get("/terms")).andExpect(status().isOk()).andExpect(view().name("terms-conditions"));

    }
    //TODO
    
    @WithMockUser
    @Test
    void testDeberiaMostrarRestaurantePorUbicacion() throws Exception{
        mockMvc.perform(post("/").param("city", "Sevilla").with(csrf())).andExpect(status().isOk()).andExpect(model().attributeExists("listaRestaurante")).andExpect(view().name("restaurantes/listado"));

    }
    
    @WithMockUser
    @Test
    void testNoDeberiaMostrarRestaurantePorUbicacionAtributoVacio() throws Exception{
        mockMvc.perform(post("/").param("city", "").with(csrf())).andExpect(status().isOk()).andExpect(model().attributeExists("listaRestaurante")).andExpect(view().name("restaurantes/listado"));

    }
    
    @WithMockUser
    @Test
    void testNoDeberiaMostrarRestaurantePorUbicacion() throws Exception{
        mockMvc.perform(post("/").with(csrf())).andExpect(status().isOk()).andExpect(view().name("welcome"));

    }
    //TODO -> Es imposible tomar uno de los caminos de showRestautanteUbication

}