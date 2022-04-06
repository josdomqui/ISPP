package org.springframework.samples.commandfast.mesa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class MesaServiceTests {

	@Autowired
	protected MesaService mesaService;
	
	@Test
	void shouldFindMesas() {
		List<Mesa> mesas = (List<Mesa>) this.mesaService.findAllMesa();
		Boolean result = mesas.contains(mesaService.findMesaByNumber(1));
		assertThat(mesas).isEmpty();;
		assertThat(result).isTrue();
	}
	
	
	@Test
    void shouldFindMesaById() {
        Mesa mesa = this.mesaService.findMesaByNumber(1);
        assertThat(mesa.getNumber()).isEqualTo(1);
        assertThat(mesa.getCostumer()).isEqualTo(3);
        assertThat(mesa.getId()).isEqualTo(1);

    }
	
	
	@Test
	void shouldSavMesa() {
		Mesa mesa = new Mesa();
		mesa.setNumber(7);
		mesa.setCostumer(2);
		mesa.setCommands(null);
		assertThat(this.mesaService.findAllMesa()).hasSize(5);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
