package spring.HelloSpring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {
    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        // 준비
        testAmount(valueOf(500), valueOf(5000));
        testAmount(valueOf(1000), valueOf(10000));
        testAmount(valueOf(3000), valueOf(30000));

        // 원화 환산 금액 유효시간 계산
//        assertThat(payment1.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static Payment testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
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