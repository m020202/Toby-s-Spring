package spring.HelloSpring.order;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String no, BigDecimal total);
    List<Order> createOrders(List<OrderReq> reqs);
}
