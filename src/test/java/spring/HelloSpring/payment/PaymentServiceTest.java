package spring.HelloSpring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.HelloSpring.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class PaymentServiceTest {
    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        // 준비
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        // 기능 실행
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        // 환율 정보 가져오기
        Assertions.assertThat(payment.getExRate()).isNotNull();
        // 원화 환산 금액 계산 (환율 * 외환금액 == 원화?)
        Assertions.assertThat(payment.getConvertAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
        // 원화 환산 금액 유효시간 계산
        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        Assertions.assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}