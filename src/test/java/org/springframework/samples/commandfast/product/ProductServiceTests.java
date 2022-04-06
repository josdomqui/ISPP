package org.springframework.samples.commandfast.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ProductServiceTests {

	@Autowired
	protected ProductService productService;

	@Test
    void shouldFindProductById() {
        Product product = this.productService.findProductById(1);
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("Ensalada gourmet");
        assertThat(product.getDescription()).isEqualTo("Lechuga Gourmet, Queso De Cabra, Cebolla Caramelizada, Nueces y Pasas");
        assertThat(product.getPrice()).isEqualTo(6.30);
        assertThat(product.getRestaurant().getId()).isEqualTo(1);
    }
	
	@Test
	void shouldSaveProduct() {
		Product product = new Product();
		product.setId(15);
		product.setName("Tarta de queso");
		product.setDescription("Tarta de queso");
		product.setPrice(4.36);
		product.setRestaurant(null);
		
	}

	
	
	
	
	
}
