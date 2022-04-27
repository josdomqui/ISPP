package org.springframework.samples.commandfast.payment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.mesa.MesaService;
import org.springframework.samples.commandfast.payments.PaymentController;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentControllerTests {

	@Autowired
	protected CommandService commandService;
	
	@Autowired
	protected MesaService mesaService;
	
	@Autowired
	protected PaymentController paymentController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		assertThat(paymentController).isNotNull();
	}
	
	@BeforeEach
	void setup() {
		Mesa mesa = new Mesa();
		mesa.setNumber(1);
		mesa.setCostumer(1);
		Command comanda = new Command();
		comanda.setLines(null);
		comanda.setMesa(null);
		comanda.setCostumers(4);
		comanda.setPrice(43.1);
		comanda.setMesa(mesa);
		this.mesaService.saveline(mesa);
		this.commandService.saveCommand(comanda);
		
		Mesa mesa2 = new Mesa();
		mesa2.setNumber(2);
		mesa2.setCostumer(2);
		Command comanda2 = new Command();
		comanda2.setLines(null);
		comanda2.setMesa(null);
		comanda2.setCostumers(4);
		comanda2.setPrice(43.1);
		comanda2.setMesa(mesa2);
		this.mesaService.saveline(mesa2);
		this.commandService.saveCommand(comanda2);
		
		Mesa mesa3 = new Mesa();
		mesa3.setNumber(3);
		mesa3.setCostumer(2);
		Command comanda3 = new Command();
		comanda3.setLines(null);
		comanda3.setMesa(null);
		comanda3.setCostumers(4);
		comanda3.setPrice(43.1);
		comanda3.setMesa(mesa3);
		this.mesaService.saveline(mesa3);
		this.commandService.saveCommand(comanda3);
		
		Mesa mesa4 = new Mesa();
		mesa4.setNumber(4);
		mesa4.setCostumer(2);
		Command comanda4 = new Command();
		comanda4.setLines(null);
		comanda4.setMesa(null);
		comanda4.setCostumers(4);
		comanda4.setPrice(43.1);
		comanda4.setMesa(mesa4);
		this.mesaService.saveline(mesa4);
		this.commandService.saveCommand(comanda4);
	}
	
	@WithMockUser
	@Test
	void testDeberiaPagarConTarjeta() throws Exception {
		mockMvc.perform(get("/payment/creditCard/{id_comanda}", 2)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/payment/waitPage"));
	}

	@WithMockUser
	@Test
	void testDeberiaMostrarOrdenPago() throws Exception {
		mockMvc.perform(get("/payment/{id_comanda}", 3)).andExpect(status().isOk())
				.andExpect(model().attributeExists("stripePublicKey")).andExpect(model().attributeExists("price"))
				.andExpect(model().attributeExists("id_comanda")).andExpect(view().name("payment/charge"));
	}

	@WithMockUser
	@Test
	void testDeberiaMostrarFormularioSuscripcion() throws Exception {
		mockMvc.perform(get("/payment/subscription")).andExpect(status().isOk())
				.andExpect(model().attributeExists("stripePublicKey")).andExpect(view().name("payment/subscription"));
	}

	@WithMockUser
	@Test
	void testDeberiaDevolverPagoConExito() throws Exception {
		mockMvc.perform(get("/payment/successPage/{id_comanda}", 1)).andExpect(status().isOk())
				.andExpect(model().attributeExists("id_comanda")).andExpect(view().name("payment/success"));
	}

	@WithMockUser
	@Test
	void testDeberiaDescargarRecibo() throws Exception {
		mockMvc.perform(get("/payment/downloadRecipt/{id_comanda}", 1)).andExpect(status().isOk());
	}

	@WithMockUser
	@Test
	void testDeberiaMostrarEsperaPago() throws Exception {
		mockMvc.perform(get("/payment/waitPage")).andExpect(status().isOk()).andExpect(view().name("payment/wait"));
	}
	
	@WithMockUser
	@Test
	void testDeberiaPagarEnCash() throws Exception {
		mockMvc.perform(get("/payment/cash/{id_comanda}", 4)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/payment/waitPage"));
	}
}