package org.springframework.samples.commandfast.users;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.commandfast.user.UserController;
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
class UserControllerTests {
	
	@Autowired
	protected UserController userController;
	
    @Autowired  
    private MockMvc mockMvc;
    
    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @WithMockUser
    @Test
    void testDeberiaMostrarValoracionesRestaurante() throws Exception{
        mockMvc.perform(get("/users/new")).andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name("users/createOwnerForm"));

    }
    
    @WithMockUser
    @Test
    void testDeberiaMostrarValoracionRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/{id2}/valoracion",1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("valoracion"))
        .andExpect(model().attributeExists("restaurante"))
        .andExpect(view().name("restaurantes/valorarRestauranteForm"));

    }
}