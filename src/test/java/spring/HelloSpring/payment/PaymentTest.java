package spring.HelloSpring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class PaymentTest {
    @Test
    void createPrepared() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        ExRateProviderStub exRateProviderStub = new ExRateProviderStub(BigDecimal.valueOf(1000));
        BigDecimal exRate = exRateProviderStub.getExRate("USD");
        Payment payment = Payment.createPrepare(
                1L, "USD", BigDecimal.TEN, exRate, LocalDateTime.now(clock)
        );
        Assertions.assertThat(payment.getConvertAmount()).isEqualTo(BigDecimal.valueOf(10000));
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        ExRateProviderStub exRateProviderStub = new ExRateProviderStub(BigDecimal.valueOf(1000));
        BigDecimal exRate = exRateProviderStub.getExRate("USD");
        Payment payment = Payment.createPrepare(
                1L, "USD", BigDecimal.TEN, exRate, LocalDateTime.now(clock)
        );
        Assertions.assertThat(payment.isValid(clock)).isTrue();
        Assertions.assertThat(
                payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES))))
                .isFalse();
    }
}
