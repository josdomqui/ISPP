package org.springframework.samples.commandfast.restaurantes;



import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.commandfast.restaurantes.RestauranteType;
import org.springframework.samples.commandfast.restaurantes.Restaurante;
public interface RestauranteRepository extends Repository<Restaurante, Integer> {
	
	void save(Restaurante restaurant) throws DataAccessException;

	@Query("SELECT restaurant FROM Restaurante restaurant WHERE restaurant.id =:id")
	public Optional<Restaurante> findById(@Param("id") int id);

	public List<Restaurante> findAll();

	@Query("SELECT restaurant FROM Restaurante restaurant WHERE restaurant.type =:type")
	public List<Restaurante> findByType(@Param("type") RestauranteType type);

	
	@Query("SELECT restaurant FROM Restaurante restaurant WHERE restaurant.city =:city")
	public List<Restaurante> findByCity(@Param("city") String city);
	

	void deleteById(int id) throws DataAccessException;

}

