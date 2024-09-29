package spring.HelloSpring.order;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


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
        return new TransactionTemplate(transactionManager).execute(status ->
            reqs.stream().map(o -> createOrder(o.no(), o.total())).toList()
        );
    }
}
