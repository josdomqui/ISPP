package org.springframework.samples.commandfast.command;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.commandfast.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = CommandController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfiguration.class), excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = CommandController.class)
class CommandControllerTests {
	
	@Autowired
	protected CommandController commandController;
	
    @Autowired  
    private MockMvc mockMvc;
/*
    @Test
    public void contextLoads() throws Exception {
        assertThat(commandController).isNotNull();
    }

@WithMockUser(username = "clienteRandom", authorities = {"cliente"})
    @Test
    @WithMockUser
    @Test
    public void testDeberiaMostrarFormularioDeInscripcion() throws Exception{
        mockMvc.perform(get("/eventos/nuevo")).andExpect(status().isOk()).andExpect(model().attributeExists("evento"))
        .andExpect(view().name("eventos/editarEvento"));

    }
    
*/
}