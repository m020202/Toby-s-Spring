package spring.HelloSpring;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import spring.HelloSpring.data.OrderRepository;
import spring.HelloSpring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        // transaction begin
        try {
            new TransactionTemplate(transactionManager).execute((TransactionCallback<Order>) status -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.ONE);
                repository.save(order2);

                return null;
            });
        } catch (DataIntegrityViolationException e) {
            System.out.println("주문번호 중복 복구 작업");
        }
        // commit
    }
}
