package spring.HelloSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.HelloSpring.exrate.CachedExRateProvider;
import spring.HelloSpring.exrate.WebApiExRateProvider;
import spring.HelloSpring.payment.ExRateProvider;
import spring.HelloSpring.payment.ExRateProviderStub;
import spring.HelloSpring.payment.PaymentService;

import java.math.BigDecimal;

@Configuration
@ComponentScan
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }

    @Bean
    public ExRateProvider cachedRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }
}
