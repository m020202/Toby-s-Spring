package spring.HelloSpring;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import spring.HelloSpring.data.OrderRepository;
import spring.HelloSpring.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(JpaTransactionManager jpaTransactionManager) {
        return new OrderService(orderRepository(), jpaTransactionManager);
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository();
    }
}
