package org.springframework.samples.commandfast.payments;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.commandfast.line.Line;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public void savePayment(Payment payment) throws DataAccessException {
		paymentRepository.save(payment);
	}
	
	@Transactional
	public void makePayment(Double amount,Mesa table) {
		Payment payment = new Payment();
		
		payment.setAmount(amount);
		
		payment.setTable(table);
		
		payment.setDate(LocalDate.now());
		
		savePayment(payment);
		
		
	}

}
