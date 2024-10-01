package spring.HelloSpring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.HelloSpring.order.Order;
import spring.HelloSpring.order.OrderServiceImpl;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderServiceImpl orderServiceImpl = beanFactory.getBean(OrderServiceImpl.class);

        Order order = orderServiceImpl.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }
}
