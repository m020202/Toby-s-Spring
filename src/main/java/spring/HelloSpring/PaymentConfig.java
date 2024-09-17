package spring.HelloSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import spring.HelloSpring.api.ApiTemplate;
import spring.HelloSpring.api.ErApiExRateExtractor;
import spring.HelloSpring.api.SimpleApiExecutor;
import spring.HelloSpring.exrate.CachedExRateProvider;
import spring.HelloSpring.exrate.RestTemplateExRateProvider;
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
        return new RestTemplateExRateProvider(restTemplate());
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new JdkClientHttpRequestFactory());
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
