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
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface LineRepository extends Repository<Line, Integer> {
	
	void save(Line line) throws DataAccessException;

	@Query("SELECT line FROM Line line WHERE line.command.id =:id")
	public Collection<Line> findByCommandId(@Param("id") int id);
	
	@Query("SELECT line FROM Line line WHERE line.id =:id")
	public Optional<Line> findByLineId(@Param("id") int id);

	@Query("SELECT line FROM Line line WHERE line.plate.id =:id AND line.command.id =:id1")
	public Optional<Line> findByLineCoId(@Param("id") int id, @Param("id1") int id1);
	
	@Query("SELECT line FROM Line line")
	public List<Line> findLines();

}
