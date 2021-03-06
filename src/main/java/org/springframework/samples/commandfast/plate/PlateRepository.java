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

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlateRepository extends CrudRepository<Plate, Integer> {
	
	@Query("SELECT plate FROM Plate plate")
	public Collection<Plate> findAllPlates();
	
	@Query("SELECT plate FROM Plate plate WHERE plate.restaurant.id =:id")
	public Collection<Plate> findPlateByRestaurant(@Param("id") int id);
	
	
	@Query("SELECT plate FROM Plate plate WHERE plate.id =:id")
    public Plate findById(@Param("id") int id);

	
	
    @Modifying
    @Query("DELETE FROM Plate p WHERE p.restaurant.id =:id")
	void deletePlateById(@Param("id") int id);
    
    @Modifying
    @Query("DELETE FROM Plate p WHERE p.id =:id")
	void deletePlateByPlateId(@Param("id") int id);
}
