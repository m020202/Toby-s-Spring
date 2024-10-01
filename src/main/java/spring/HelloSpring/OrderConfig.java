package spring.HelloSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import spring.HelloSpring.order.OrderRepository;
import spring.HelloSpring.order.OrderService;
import spring.HelloSpring.order.OrderServiceImpl;
import spring.HelloSpring.order.OrderServiceTransactionProxy;

@Configuration
@Import(DataConfig.class)
@ComponentScan
public class OrderConfig {
    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager, OrderRepository orderRepository) {
        return new OrderServiceTransactionProxy(
                new OrderServiceImpl(orderRepository),
                transactionManager
        );
    }

//    @Bean
//    public OrderRepository orderRepository(DataSource dataSource) {
//        return new JdbcOrderRepository(dataSource);
//    }

}
