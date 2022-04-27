package org.springframework.samples.commandfast.command;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommandControllerTests {
	
	@Autowired
	protected CommandController commandController;
	
    @Autowired  
    private MockMvc mockMvc;
    
    @Test
    void contextLoads() throws Exception {
        assertThat(commandController).isNotNull();
    }

    @WithMockUser
    @Test
    void testDeberiaMostrarFormularioDeComandas() throws Exception{
        mockMvc.perform(get("/command/new")).andExpect(status().isOk())
        .andExpect(model().attributeExists("id_restaurante"))
        .andExpect(model().attributeExists("id_mesa"))
        .andExpect(model().attributeExists("restaurantes"))
        .andExpect(model().attributeExists("mesas"))
        .andExpect(model().attributeExists("command"))
        .andExpect(view().name("command/createCommand"));

    }
    
    @WithMockUser
    @Test
    void testDeberiaMostrarFormularioDeQR() throws Exception{
        mockMvc.perform(get("/command/new/{id_restaurante}/{id_mesa}",1,1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("id_restaurante"))
        .andExpect(model().attributeExists("id_mesa"))
        .andExpect(model().attributeExists("restaurantes"))
        .andExpect(model().attributeExists("mesas"))
        .andExpect(model().attributeExists("command"))
        .andExpect(view().name("command/createCommand"));

    }
    
    @WithMockUser(username = "gomezmartin", authorities = {"restaurant"})
    @Test
    void testDeberiaMostrarTodasLasComandasAtributoListaComandas() throws Exception{
        mockMvc.perform(get("/command/all")).andExpect(status().isOk())
        .andExpect(model().attributeExists("sesionRestaurant"))
        .andExpect(view().name("command/commandOfRestaurant"));

    }
    
    @WithMockUser(username = "tabernasevilla", authorities = {"restaurant"})
    @Test
    void testDeberiaMostrarTodasLasComandasAtributoVacio() throws Exception{
        mockMvc.perform(get("/command/all")).andExpect(status().isOk())
        .andExpect(model().attributeExists("vacio"))
        .andExpect(view().name("command/commandOfRestaurant"));

    }
    
    @WithMockUser
    @Test
    void testNoDeberiaCrearQR() throws Exception{
        mockMvc.perform(post("/command/new/{id_restaurante}/{id_mesa}",1,1).with(csrf())).andExpect(status().isOk())
        .andExpect(model().attributeExists("mesas"))
        .andExpect(model().attributeExists("restaurantes"))
        .andExpect(view().name("command/createCommand"));

    }
    
    @WithMockUser
    @Test
    void testNoDeberiaCrearComanda() throws Exception{
        mockMvc.perform(post("/command/new").with(csrf())).andExpect(status().isOk())
        .andExpect(model().attributeExists("mesas"))
        .andExpect(model().attributeExists("restaurantes"))
        .andExpect(view().name("command/createCommand"));

    }
    
}