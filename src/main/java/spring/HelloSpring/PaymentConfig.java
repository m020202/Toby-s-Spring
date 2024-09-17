package spring.HelloSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.HelloSpring.api.ApiTemplate;
import spring.HelloSpring.exrate.CachedExRateProvider;
import spring.HelloSpring.payment.ExRateProvider;
import spring.HelloSpring.exrate.WebApiExRateProvider;
import spring.HelloSpring.payment.PaymentService;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate();
    }


    @Bean
    public ExRateProvider cachedRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
