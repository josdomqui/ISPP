package org.springframework.samples.commandfast.restaurantes;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface NotificationRepository extends Repository<Notification, Integer> {
	
	void save(Notification notification) throws DataAccessException;

	@Query("SELECT notification FROM Notification notification WHERE restaurant_id =:id")
	public List<Notification> findNotificationsByRestaurant(@Param("id") int id);

	@Query("SELECT notification FROM Notification notification WHERE id =:id")
	public Notification findById(int id);
	
    @Modifying
    @Query("DELETE FROM Notification n WHERE n.restaurant.id =:id")
	void deleteNotificationById(@Param("id") int id);

}

