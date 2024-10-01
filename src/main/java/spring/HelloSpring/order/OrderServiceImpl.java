package spring.HelloSpring.order;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository jpaOrderRepository) {
        this.orderRepository = jpaOrderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        this.orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return reqs.stream().map(o -> createOrder(o.no(), o.total())).toList();
    }
}
