package spring.HelloSpring.data;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import spring.HelloSpring.order.Order;
import spring.HelloSpring.order.OrderRepository;

import javax.sql.DataSource;

@Component
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDb() {
        jdbcClient.sql("""
                create table orders (id bigint not null, no varchar(255), total numeric(38,2), primary key(id));
                create sequence orders_SEQ start with 1 increment by 50;
                """).update();
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class).single();
        order.setId(id);

        jdbcClient.sql("insert into orders (no, total, id) values (?,?,?)")
                .params(order.getNo(), order.getTotal(), order.getId())
                .update();
    }
}
