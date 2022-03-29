//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.samples.commandfast.mesa.Mesa;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
//class CommandServiceTests {
//	@Autowired
//	protected CommandService commandService;
//
//	@Test
//    void shouldFindCommandById() {
//        Command command = this.commandService.findIdCommands(1).get();
//        assertThat(command.getCostumers()).isEqualTo(4);
//        assertThat(command.getMesa().getId()).isEqualTo(1);
//        assertThat(command.getPrice()).isEqualTo(53.02);
//
//    }
//
//	@Test
//	void shouldFindCommands() {
//		List<Command> commands = (List<Command>) this.commandService.findCommands();
//		Boolean result = commands.contains(commandService.findIdCommands(1).get());
//		assertThat(commands.isEmpty()).isFalse();
//		assertThat(result).isTrue();
//	}
//	
//	@Test
//	void shouldSaveCommands() {
//		Command comanda = new Command();
//		comanda.setLines(null);
//		comanda.setMesa(null);
//		comanda.setCostumers(3);
//		comanda.setPrice(0.);
//		assertThat(this.commandService.findCommands()).hasSize(1);
//		this.commandService.saveCommand(comanda);
//		assertThat(this.commandService.findCommands()).hasSize(2);
//		assertThat(this.commandService.findIdCommands(2).get().getCostumers()).isEqualTo(3);
//	}
//
//}