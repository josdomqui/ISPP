package org.springframework.samples.commandfast.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test class for {@link CrashController}
 *
 * @author Colin But
 */
//Waiting https://github.com/spring-projects/spring-boot/issues/5574

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CrashControllerTests {

	@Autowired
	private CrashController crashController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testTriggerException() throws Exception {
		mockMvc.perform(get("/oups")).andExpect(view().name("exception")).andExpect(status().isOk());
	}

}
