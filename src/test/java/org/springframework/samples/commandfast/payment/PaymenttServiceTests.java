package org.springframework.samples.commandfast.payment;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.payments.Payment;
import org.springframework.samples.commandfast.payments.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PaymenttServiceTests {

    @Autowired
    private PaymentService paymentService;

    
    @Test
    void shouldFindAllPayments(){
    Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);
    
    Payment pa = new Payment();
    pa.setAmount(25.0);
    pa.setCreditCard(Boolean.FALSE);
    pa.setDate(LocalDate.now());
    pa.setId(1);
    pa.setPayHere(Boolean.TRUE);
    pa.setTable(m);
    this.paymentService.savePayment(pa);
    List<Payment> p = this.paymentService.getAllPayments();

    assertThat(p.isEmpty()).isFalse();
    }

    @Test
    void shouldFindPaymentsById(){
        Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);
    
    Payment pa = new Payment();
    pa.setAmount(25.0);
    pa.setCreditCard(Boolean.FALSE);
    pa.setDate(LocalDate.now());
    pa.setId(1);
    pa.setPayHere(Boolean.TRUE);
    pa.setTable(m);
    this.paymentService.savePayment(pa);
    assertThat(this.paymentService.getPaymentById(pa.getId()).get().getId()).isEqualTo(pa.getId());
    }

    @Test
    void shouldGeneratePayment(){
    assertThat(this.paymentService.generateRecipt(2.5)).isNotEmpty();
    }
}