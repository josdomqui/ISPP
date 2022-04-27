package org.springframework.samples.commandfast.command;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CommandServiceTests {
	@Autowired
	protected CommandService commandService;
	
	
	@BeforeEach
	void setup() {
		Command comanda = new Command();
		comanda.setLines(null);
		comanda.setMesa(null);
		comanda.setCostumers(4);
		comanda.setPrice(43.1);
		this.commandService.saveCommand(comanda);
	}
	@Test
    void shouldFindCommandById() {
        Command command = this.commandService.findIdCommands(1).get();
        assertThat(command.getCostumers()).isEqualTo(4);
        assertThat(command.getPrice()).isEqualTo(43.1);

    }

	@Test
	void shouldFindCommands() {
		List<Command> commands = this.commandService.findCommands().stream().collect(Collectors.toList());
		Command result = this.commandService.findIdCommands(commands.get(0).getId()).get();
		assertThat(commands).isNotEmpty();
		assertThat(result).isNotNull();
	}
	
	@Test
	void shouldSaveCommands() {
		Command comanda = new Command();
		comanda.setLines(null);
		comanda.setMesa(null);
		comanda.setCostumers(3);
		comanda.setPrice(0.);
		assertThat(this.commandService.findCommands()).hasSize(1);
		this.commandService.saveCommand(comanda);
		assertThat(this.commandService.findCommands()).hasSize(2);
	}

}