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
package org.springframework.samples.commandfast.mesa;
import java.util.List;
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
public class MesaService {
	
	//Necesario para el uso de logs.
	private static final Logger log = LoggerFactory.getLogger(MesaService.class);
	@Autowired
	private MesaRepository mesaRepository;


	

	
	@Transactional(readOnly = true)
	public Mesa findMesaByNumber(Integer number) throws DataAccessException {
		log.info("Buscando mesa por número");
		return mesaRepository.findMesaByNumber(number);
	}
	
	@Transactional(readOnly = true)
	public List<Mesa> findAllMesa() throws DataAccessException {
		log.info("Buscando todas las mesas");
		return (List<Mesa>) mesaRepository.findAll();
	}

	@Transactional
	public void saveline(Mesa mesa) throws DataAccessException {

			mesaRepository.save(mesa);
		}



}
