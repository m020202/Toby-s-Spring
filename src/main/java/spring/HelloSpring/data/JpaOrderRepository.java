package spring.HelloSpring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import spring.HelloSpring.order.Order;
import spring.HelloSpring.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save (Order order) {
        entityManager.persist(order);
    }
}
