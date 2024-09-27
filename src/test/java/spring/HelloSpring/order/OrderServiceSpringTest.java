package spring.HelloSpring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.HelloSpring.OrderConfig;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired // spring extension에 의해서 컨테이너 초기화 후 자동으로 빈 주입
    OrderService orderService;

    @Test
    void createOrder() {
        var order = orderService.createOrder("0100", BigDecimal.TEN);

        assertThat(order.getId()).isGreaterThan(0);
    }

}
