package spring.HelloSpring.order;

import org.aspectj.weaver.ast.Or;
import org.springframework.transaction.PlatformTransactionManager;
import java.math.BigDecimal;
import java.util.List;


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

    public List<Order> createOrders(List<OrderReq> reqs) {
        return null;
    }
}
