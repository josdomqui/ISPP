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
package org.springframework.samples.commandfast.plate;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.command.CommandRepository;
import org.springframework.samples.commandfast.line.LineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Mostly used as a facade for all commandfast controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class PlateService {

    @Autowired
	private PlateRepository plateRepository;

	@Autowired
	private CommandRepository commandRepository;
	 
	@Autowired
	private LineRepository lineRepository;

    @Autowired
    public PlateService(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }
	
	@Transactional(readOnly = true)
	public Collection<Plate> findAllPlates() throws DataAccessException {
		return plateRepository.findAllPlates();
	}
	@Transactional(readOnly = true)
	public Collection<Plate> findPlatesByRestaurant(Integer id) throws DataAccessException {
		return plateRepository.findPlateByRestaurant(id);
	}
	
	
	@Transactional(readOnly = true)
    public Plate findPlateById(int id) throws DataAccessException {
        return plateRepository.findById(id);
    }
	
	@Transactional
    public void savePlate(Plate plate) throws DataAccessException {
        plateRepository.save(plate);

    }
	@Transactional
	public void delete(Integer id) {
		
		List<Integer> aux = this.lineRepository.findByLinePlateId(id);
		for(Integer i:aux) {
			lineRepository.deleteLineById(i);
		}
		this.plateRepository.deletePlateByPlateId(id);
		
	}
}
