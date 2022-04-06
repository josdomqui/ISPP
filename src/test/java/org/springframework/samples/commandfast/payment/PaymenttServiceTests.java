package org.springframework.samples.commandfast.payment;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
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
    List<Payment> p = this.paymentService.getAllPayments();
    assertThat(p.size()!=0);
    }

    @Test
    void shouldFindPaymentsById(){
    assertThat(this.paymentService.getPaymentById(1));
    }

    @Test
    void shouldGeneratePayment(){
    assertThat(this.paymentService.generateRecipt(2.5));
    }
}