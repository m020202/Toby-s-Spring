package spring.HelloSpring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import spring.HelloSpring.order.Order;

public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save (Order order) {
        entityManager.persist(order);
    }
}
