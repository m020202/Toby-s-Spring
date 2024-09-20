package spring.HelloSpring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import spring.HelloSpring.order.Order;

public class OrderRepository {
    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save (Order order) {
        // EntityManager 객체 생성
        EntityManager em = emf.createEntityManager();
        // transaction 시작
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // em.persist => 영속화 하기
            em.persist(order);
            // 변경사항을 즉시 반영 (트랜잭션 커밋 전에)
            em.flush();
            // em에 넣은 엔티티 커밋
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            if (transaction.isActive()) em.close();
        }
    }
}
