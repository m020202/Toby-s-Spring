package spring.HelloSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import spring.HelloSpring.data.JpaOrderRepository;
import spring.HelloSpring.order.OrderRepository;
import spring.HelloSpring.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }

    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepository();
    }
}
