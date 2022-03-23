package org.springframework.samples.commandfast.payments;

import org.springframework.data.repository.CrudRepository;


public interface PaymentRepository extends CrudRepository<Payment,Integer> {
	


}
