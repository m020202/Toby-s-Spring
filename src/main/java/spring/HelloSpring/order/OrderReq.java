package spring.HelloSpring.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}
