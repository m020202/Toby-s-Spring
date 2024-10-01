package spring.HelloSpring.order;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTransactionProxy implements OrderService{
    // OrderService 인터페이스를 구현한 오브젝트를 사용할 것이다.
    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTransactionProxy(OrderService target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return target.createOrder(no, total);
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status ->
                    target.createOrders(reqs)
        );
    }
}
