package spring.HelloSpring.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.HelloSpring.TestPaymentConfig;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {
    @Autowired
    PaymentService paymentService;
    @Autowired
    Clock clock;
    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
    void convertedAmount() throws IOException {
        // exRate : 1000
        Payment payment1 = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment1.getExRate()).isEqualByComparingTo(valueOf(1000));
        assertThat(payment1.getConvertAmount()).isEqualByComparingTo(valueOf(10000));

        // exRate : 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertAmount()).isEqualByComparingTo(valueOf(5000));
    }

    @Test
    void validUntil() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        // valid until prepare() 30분 뒤로 설정했는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedUntil = now.plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedUntil);
    }
}