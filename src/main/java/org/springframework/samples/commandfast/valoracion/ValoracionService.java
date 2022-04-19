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
package org.springframework.samples.commandfast.valoracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
import org.springframework.samples.commandfast.restaurantes.RestauranteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all commandfast controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ValoracionService {

	private ValoracionRepository valoracionRepository;
	
	private RestauranteService restauranteService;

	@Autowired
	public ValoracionService(ValoracionRepository valoracionRepository, RestauranteService restauranteService) {
		this.valoracionRepository = valoracionRepository;
		this.restauranteService = restauranteService;
	}

	@Transactional
	public void saveValoracion(Valoracion valoracion) throws DataAccessException {
		valoracionRepository.save(valoracion);
	}
	
	@Transactional
	public void actualizarPuntuacionMedia(Restaurante restaurante) {
		
		Double valoracionTotal = 0.0;
		for(Valoracion v : restaurante.getValoraciones()) {
			valoracionTotal += v.getPuntuacion();
		}
		Integer numTotalValoraciones = restaurante.getValoraciones().size();
		Double valoracionMedia = (valoracionTotal / numTotalValoraciones);
		restaurante.setValoracionMedia(valoracionMedia);
		restauranteService.saveRestaurant(restaurante);
	}

}
