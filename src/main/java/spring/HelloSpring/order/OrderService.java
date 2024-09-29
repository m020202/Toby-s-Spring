package spring.HelloSpring.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;


public class OrderService {
    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(OrderRepository jpaOrderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = jpaOrderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        this.orderRepository.save(order);
        return order;
    }
}
