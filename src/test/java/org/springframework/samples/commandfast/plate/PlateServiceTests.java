package org.springframework.samples.commandfast.plate;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PlateServiceTests {

	@Autowired
	protected PlateService plateService;
	
	@Test
	void shouldFindPlates() {
		Collection<Plate> plates = this.plateService.findAllPlates().stream().collect(Collectors.toList());
		Boolean result = plates.contains(plateService.findPlateById(1));
		assertThat(plates).isNotEmpty();
		assertThat(result).isTrue();
	}
	
	
	
	@Test
    void shouldFindPlateById() {
        Plate plate = this.plateService.findPlateById(1);
        assertThat(plate.getId()).isEqualTo(1);
        assertThat(plate.getName()).isEqualTo("Huevos Lartisan");
        assertThat(plate.getCategory()).isEqualTo("Media-Racion");
        assertThat(plate.getCost()).isEqualTo(11);
        assertThat(plate.getImage()).isEqualTo("/resources/images/Huevos Lartisan.jpg");

    }
	
	@Test
	void shouldSavePlate() {
		Plate plate = new Plate();
		plate.setCategory("Media-Racion");
		plate.setCost(10.25);
		plate.setLines(null);
		plate.setName("Solomillo");
		plate.setImage(null);
		assertThat(this.plateService.findAllPlates()).hasSize(10);
		
	}
	
	
	
	
	
	
}
