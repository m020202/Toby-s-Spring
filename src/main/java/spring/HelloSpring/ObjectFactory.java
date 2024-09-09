package spring.HelloSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {
    @Bean
    public  PaymentService paymentService() {
        return new PaymentService(cachedRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public ExRateProvider cachedRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }
}
