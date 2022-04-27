package org.springframework.samples.commandfast.restaurant;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.restaurantes.Notification;
import org.springframework.samples.commandfast.restaurantes.NotificationService;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestauranteControllerTests {
	
	@Autowired
	protected RestauranteController restauranteController;
	
	@Autowired
	protected NotificationService notificationService;
	
	@Autowired
	protected RestauranteService restaurantService;
	
    @Autowired  
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(restauranteController).isNotNull();
    }
    
    @BeforeEach
	void setup() {
    	Restaurante rest = restaurantService.findRestaurantById(1).get();
		Notification notificacion = new Notification();
		notificacion.setId(1);
		notificacion.setAtendido(0);
		notificacion.setNumeroMesa(1);
		notificacion.setRestaurant(rest);
		
		Command comanda = new Command();
		comanda.setCostumers(2);
		comanda.setId(2);
		
		Mesa mesa = new Mesa();
		mesa.setCostumer(2);
		mesa.setNumber(2);
		comanda.setMesa(mesa);
		comanda.setRestaurante(rest);
		
		this.notificationService.saveNotification(notificacion);
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
    
    @WithMockUser(username = "anonymousUser", authorities = {"anonymousUser"})
    @Test
    void testDeberiaListadoRestauranteBuscadoUbicacionTipoYValor() throws Exception{
        mockMvc.perform(get("/restaurante/list/search").param("inputPlace", "Sevilla").param("inputState", "DOS_TENEDORES").param("inputValor", "Más valorados")).andExpect(status().isOk()).andExpect(view().name("restaurantes/listado"));
    }
    
    @WithMockUser(username = "anonymousUser", authorities = {"anonymousUser"})
    @Test
    void testDeberiaListadoRestauranteBuscadoUbicacionTipoYValorVacios() throws Exception{
        mockMvc.perform(get("/restaurante/list/search").param("inputPlace", "").param("inputState", "Selecciona una opción").param("inputValor", "Menos valorados")).andExpect(status().isOk()).andExpect(view().name("restaurantes/listado"));
    }
    
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
    void testDownloadQR() throws Exception{
        mockMvc.perform(get("/restaurante/{id}/detalles/qr/descargar", 1).param("numero_mesa", "1")).andExpect(status().isOk()).andExpect(model().attributeExists("restaurante")).andExpect(model().attributeExists("id_restaurante")).andExpect(view().name("restaurantes/qr"));
    }
    
    @WithMockUser
    @Test
    void testNotifyClear() throws Exception{
        mockMvc.perform(get("/restaurante/notify/clear/{id_notification}", 1)).andExpect(status().is3xxRedirection());
    }
    
    @WithMockUser
    @Test
    void testInitCreationForm() throws Exception{
        mockMvc.perform(get("/restaurante/{id}/product/new", 1)).andExpect(status().isOk())
        .andExpect(model().attributeExists("plate"))
        .andExpect(model().attributeExists("restaurante_id"))
        .andExpect(view().name("carta/addProduct"));
    }
    
    @WithMockUser
    @Test
    void testProcessCreationForm() throws Exception{
        mockMvc.perform(post("/restaurante/{id}/product/new", 1).with(csrf())).andExpect(status().isOk())
        .andExpect(view().name("carta/addProduct"));
    }
    
    @WithMockUser
    @Test
    void testEditarRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/editar")).andExpect(status().isOk()).andExpect(model().attributeExists("error")).andExpect(model().attributeExists("listaTipos")).andExpect(view().name("restaurantes/editarRestauranteForm"));
    }
    
    @WithMockUser
    @Test
    void testNoDeberiaMostarFormularioEditarRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/restaurantes/{id}/edit", 8373)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/"));

    }

    @WithMockUser
    @Test
    void testDeberiaMostarTerminosYServicios() throws Exception{
        mockMvc.perform(get("/terms", 1)).andExpect(status().isOk()).andExpect(view().name("terms-conditions"));

    }

    @WithMockUser
    @Test
    void testDeberiaMostrarFormularioRestaurante() throws Exception{
        mockMvc.perform(get("/restaurante/signup", 1)).andExpect(status().isOk()).andExpect(view().name("restaurantes/createRestaurantForm"));

    }
    

    @WithMockUser
    @Test
    void testNotif() throws Exception{
        mockMvc.perform(get("/restaurante/notify/{id_comanda}/{id_restaurante}",2,1)).andExpect(status().isOk());

    }


    
    @WithMockUser
    @Test
    void testNoDeberiaEliminarProducto() throws Exception{
        mockMvc.perform(delete("/restaurante/3/detalles/carta")).andExpect(status().isForbidden());
    }


    
}

