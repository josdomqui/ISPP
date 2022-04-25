package org.springframework.samples.commandfast.restaurantes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.product.Product;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all commandfast controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
*/
@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	
	@Transactional
	public void saveNotification(Notification notification){
		notificationRepository.save(notification);
	}
	
	public List<Notification> findNotificationsByRestaurant(int id) {
		return notificationRepository.findNotificationsByRestaurant(id);
	}

	public Notification findNotificationById(int id_notification) {
		return notificationRepository.findById(id_notification);
	}

}