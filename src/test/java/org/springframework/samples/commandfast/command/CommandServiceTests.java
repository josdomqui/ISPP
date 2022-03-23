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
package org.springframework.samples.commandfast.command;
//
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CommandServiceTests {
	@Autowired
	protected CommandService commandService;

	@Test
    void shouldFindCommandById() {
        Command command = this.commandService.findIdCommands(1).get();
        assertThat(command.getName()).isEqualTo("usuario");
        assertThat(command.getMesa().getId()).isEqualTo(1);
        assertThat(command.getPrice()).isEqualTo(53.02);

    }

	@Test
	void shouldFindCommands() {
		List<Command> commands = (List<Command>) this.commandService.findCommands();
		Boolean result = commands.contains(commandService.findIdCommands(1).get());
		assertThat(commands.isEmpty()).isFalse();
		assertThat(result).isTrue();
	}
	

}