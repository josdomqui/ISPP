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
package org.springframework.samples.commandfast.line;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class LineService {
	
	//Necesario para el uso de logs.
	private static final Logger log = LoggerFactory.getLogger(LineService.class);

	private LineRepository lineRepository;

	@Autowired
	public LineService(LineRepository lineRepository) {
		this.lineRepository = lineRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Line> findlines() throws DataAccessException {
		log.info("Buscando todas las comandas existentes");
		return lineRepository.findLines();
	}
	
	@Transactional(readOnly = true)
	public Collection<Line> findLineByCommandId(int id) throws DataAccessException {
		log.info("Buscando todas las comandas existentes");
		return lineRepository.findByCommandId(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Line> findLineById(int id) throws DataAccessException {
		log.info("Buscando todas las lineas para el plato");
		return lineRepository.findByLineId(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Line> findLineCoById(int id, int id1) throws DataAccessException {
		log.info("Buscando todas las lineas para el plato");
		return lineRepository.findByLineCoId(id, id1);
	}
	
	@Transactional
	public void saveline(Line line) throws DataAccessException {
		log.info("Guardando la comanda en la BD");
		Optional<Line> line2 = findLineCoById(line.getPlate().getId(), line.getCommand().getId());
		if(line2.isPresent()){
			line2.get().setQuantity(line.getQuantity());
			lineRepository.save(line2.get());
		}else{
			lineRepository.save(line);
		}
		
		log.info("Comanda guardada correctamente");
	
	}


}
