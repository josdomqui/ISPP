package org.springframework.samples.commandfast.admin;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTests {
	
	@Autowired
	protected AdminController commandController;
	
    @Autowired  
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(commandController).isNotNull();
    }


    @WithMockUser(username = "adminRandom", authorities = {"admin"})
    @Test
    void testDeberiaMostrarPanelAdministracion() throws Exception{
        mockMvc.perform(get("/admin/paymentPanel")).andExpect(status().isOk()).andExpect(model().attributeExists("payments"))
        .andExpect(view().name("admin/payments"));

    }
    
    @WithMockUser
    @Test
    void testNoDeberiaMostrarPanelAdministracion() throws Exception{
        mockMvc.perform(get("/admin/paymentPanel")).andExpect(status().isForbidden());
    }
    
    @WithMockUser(username = "adminRandom", authorities = {"admin"})
    @Test
    void testDeberiaMostrarFormularioEdicionRestaurante() throws Exception{
        mockMvc.perform(get("/admin/editarRestaurante/{restauranteId}",1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("error"))
        .andExpect(model().attributeExists("restaurante"))
        .andExpect(model().attributeExists("listaTipos"))
        .andExpect(view().name("restaurantes/editarRestauranteForm"));
    }

    

}