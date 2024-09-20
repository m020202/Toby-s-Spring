package spring.HelloSpring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.HelloSpring.data.OrderRepository;
import spring.HelloSpring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        System.out.println(order);

        try {
            Order order2 = new Order("100", BigDecimal.ONE);
            repository.save(order2);
        } catch (ConstraintViolationException e) {
            System.out.println("주문번호 충돌을 복구하는 작업");
        }
    }
}
