/*
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;

/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
public interface RestaurantsRepository extends Repository<Restaurant, Integer> {

	/**
	 * Save an <code>Owner</code> to the data store, either inserting or updating it.
	 * @param owner the <code>Owner</code> to save
	 * @see BaseEntity#isNew
	 */
	/*
	void save(Restaurant owner) throws DataAccessException;

	@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
	public Collection<Restaurant> findByLastName(@Param("lastName") String lastName);


	@Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
	public Restaurant findById(@Param("id") int id);

}
*/
