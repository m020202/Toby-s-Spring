package spring.HelloSpring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {
    Clock clock;
    @BeforeEach
    void beforeEach() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() {
        // 준비
        testAmount(valueOf(500), valueOf(5000),clock);
        testAmount(valueOf(1000), valueOf(10000),clock);
        testAmount(valueOf(3000), valueOf(30000),clock);
    }

    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        // valid until prepare() 30분 뒤로 설정했는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedUntil = now.plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedUntil);
    }

    private static Payment testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        // 기능 실행
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        // 검증
        // 환율 정보 가져오기
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화 환산 금액 계산 (환율 * 외환금액 == 원화?)
        assertThat(payment.getConvertAmount()).isEqualByComparingTo(convertedAmount);
        return payment;
    }
}