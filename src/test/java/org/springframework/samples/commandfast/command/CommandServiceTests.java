///*
// * Copyright 2002-2013 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package org.springframework.samples.commandfast.command;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Integration test of the Service and the Repository layer.
// * <p>
// * ClinicServiceSpringDataJpaTests subclasses benefit from the following
// * services provided by the Spring TestContext Framework:
// * </p>
// * <ul>
// * <li><strong>Spring IoC container caching</strong> which spares us unnecessary
// * set up time between test execution.</li>
// * <li><strong>Dependency Injection</strong> of test fixture instances, meaning
// * that we don't need to perform application context lookups. See the use of
// * {@link Autowired @Autowired} on the <code>{@link
// * PlayerServiceTests#clinicService clinicService}</code> instance variable,
// * which uses autowiring <em>by type</em>.
// * <li><strong>Transaction management</strong>, meaning each test method is
// * executed in its own transaction, which is automatically rolled back by
// * default. Thus, even if tests insert or otherwise change database state, there
// * is no need for a teardown or cleanup script.
// * <li>An {@link org.springframework.context.ApplicationContext
// * ApplicationContext} is also inherited and can be used for explicit bean
// * lookup if necessary.</li>
// * </ul>
// *
// * @author Ken Krebs
// * @author Rod Johnson
// * @author Juergen Hoeller
// * @author Sam Brannen
// * @author Michael Isvy
// * @author Dave Syer
// */
//
//@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
//class CommandServiceTests {
//	@Autowired
//	protected CommandService commandService;
//
////	@Test
////    void shouldFindGamesById() {
////        Command command = this.commandService.findGameById(1);
////        assertThat(game.getName()).isEqualTo("No Usar");
////        assertThat(game.getNumerojugadores()).isEqualTo(2);
////        assertThat(game.getRondas()).isEqualTo(3);
////
////    }
////
////	@Test
////	void shouldFindGames() {
////		Collection<Game> games = this.gameService.findGames();
////		games = this.gameService.findGames();
////		assertThat(games.isEmpty()).isFalse();
////		assertTrue(games.contains(gameService.findGameById(1)));
////	}
//
//	@Test
//	@Transactional
//	public void shouldInsertCommand() {
//		//Collection<Command> commands= this.commandService.findCommands();
//		Mesa mesa = sacar mesas, implica metodo en servicio
//		Command command = new Command();
//		command.setName("Comanda de prueba");
//		command.setPrice(2.3);
//		command.setQuantity(3);
//		command.setMesa(Mesa);
//		command.getPlates() //sacar platos y de cada plato su propiedad cantidad
//
//		assertThat(commandService.findCommands().size()).isEqualTo(0); // Antes de introducirlo
//		this.commandService.saveCommand(command);
//		assertThat(commandService.findCommands().size()).isEqualTo(1); // Después de introducirlo
//	}
//
////	@Test
////	@Transactional
////	void shouldUpdateGame() {
////		Game game = this.gameService.findGameById(1);
////		String oldName = game.getName();
////		String newName = oldName + "X";
////
////		game.setName(newName);
////		this.gameService.saveGame(game);
////
////		// retrieving new name from database
////		game = this.gameService.findGameById(1);
////		assertThat(game.getName()).isEqualTo(newName);
////	}
////
////	@Test
////	@Transactional
////	void shouldTotalGame() {
////		Integer numPartidas = this.gameService.totalPartidas();
////		Game game = new Game();
////		game.setName("Partida3");
////		// game.setTime(2);
////		// game.setGamemode("Torre Infernal");
////		game.setNumerojugadores(2);
////		game.setRondas(3);
////		// game.setPlayer(new HashSet<Player>());
////		this.gameService.saveGame(game);
////		assertThat(this.gameService.totalPartidas()).isEqualTo(numPartidas + 1);
////
////	}
//}