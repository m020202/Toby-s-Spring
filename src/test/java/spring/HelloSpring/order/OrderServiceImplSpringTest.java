package spring.HelloSpring.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.HelloSpring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {
    @Autowired // spring extension에 의해서 컨테이너 초기화 후 자동으로 빈 주입
    OrderServiceImpl orderServiceImpl;
    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
        var order = orderServiceImpl.createOrder("0100", BigDecimal.TEN);

        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TEN),
                new OrderReq("0202", BigDecimal.ONE)
        );

        var orders = orderServiceImpl.createOrders(orderReqs);

        assertThat(orders).hasSize(3);
        orders.forEach(o -> assertThat(o.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() { // 일부러 오류가 나도록 테스트 해보기
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TEN)
        );

        assertThatThrownBy(() -> orderServiceImpl.createOrders(orderReqs)).isInstanceOf(DataIntegrityViolationException.class);
        JdbcClient client = JdbcClient.create(dataSource);
        Long count = client.sql("select count(*) from orders where no = '0300'").query(Long.class).single();
        assertThat(count).isEqualTo(0);
    }
}
