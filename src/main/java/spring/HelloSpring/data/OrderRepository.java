package spring.HelloSpring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import spring.HelloSpring.order.Order;

import java.math.BigDecimal;

public class OrderRepository {
    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save (Order order) {
        // EntityManager 객체 생성
        EntityManager em = emf.createEntityManager();
        // transaction 시작
        em.getTransaction().begin();

        // em.persist => 영속화 하기
        em.persist(order);

        // em에 넣은 엔티티 커밋
        em.getTransaction().commit();
        em.close();
    }
}
