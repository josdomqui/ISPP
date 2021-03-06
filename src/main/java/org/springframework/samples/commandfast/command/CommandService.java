/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.commandfast.command;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all commandfast controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class CommandService {
	
	//Necesario para el uso de logs.
	private static final Logger log = LoggerFactory.getLogger(CommandService.class);

	private CommandRepository commandRepository;

	@Autowired
	public CommandService(CommandRepository commandRepository) {
		this.commandRepository = commandRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Command> findCommands() throws DataAccessException {
		log.info("Buscando todas las comandas existentes");
		return commandRepository.findCommands();
	}
	
	@Transactional(readOnly = true)
	public Optional<Command> findIdCommands(Integer id) throws DataAccessException {
		log.info("Buscando todas las comandas existentes");
		return commandRepository.findById(id);
	}
	
	@Transactional
	public void saveCommand(Command command) throws DataAccessException {
		log.info("Guardando la comanda en la BD");
		commandRepository.save(command);
		log.info("Comanda guardada correctamente");	
	}
	
	@Transactional(readOnly = true)
	public Collection<Command> findCommandsOfARestaurant(Integer id) throws DataAccessException {
		log.info("Buscando todas las comandas existentes de un restaurante");
		return commandRepository.findCommandsOfARestaurant(id);
	}


}
