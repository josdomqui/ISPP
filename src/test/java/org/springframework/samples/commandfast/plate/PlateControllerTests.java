package org.springframework.samples.commandfast.plate;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.commandfast.restaurantes.Notification;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlateControllerTests {
	
	@Autowired
	protected PlateController plateController;
	
    @Autowired  
    private MockMvc mockMvc;
    
    @Test
    void contextLoads() throws Exception {
        assertThat(plateController).isNotNull();
    }
    
    @BeforeEach
	void setup() {
		Notification notificacion = new Notification();
		notificacion.setId(1);
		notificacion.setAtendido(0);
		notificacion.setNumeroMesa(1);
	}
    
    @WithMockUser
    @Test
    void testDeberiaMostrarTodosLosPlatos() throws Exception{
        mockMvc.perform(get("/carta/{id_comanda}/{id_restaurante}",1,1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("platos"))
        .andExpect(model().attributeExists("lines"))
        .andExpect(model().attributeExists("id_commanda"))
        .andExpect(view().name("carta/plateList"));
    }
    
    @WithMockUser
    @Test
    void testDeberiaDevolverFormularioEditarLinea() throws Exception{
        mockMvc.perform(get("/carta/{id_comanda}/{id_restaurante}/edit",1,1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("lines"))
        .andExpect(model().attributeExists("platos"))
        .andExpect(model().attributeExists("id_commanda"))
        .andExpect(model().attributeExists("id_restaurante"))
        .andExpect(view().name("carta/plateList"));
    }
    
    @WithMockUser
    @Test
    void testDeberiaEditarLinea() throws Exception{
        mockMvc.perform(get("/carta/{id_comanda}/{id_restaurante}/edit",1,1)).andExpect(status().isOk())
        .andExpect(view().name("carta/plateList"));
    }
}