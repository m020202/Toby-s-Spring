package spring.HelloSpring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spring.HelloSpring.exrate.ExRateData;

import java.math.BigDecimal;

// 응답으로 받은 JSON 문자열을 파싱하고 필요한 환율 정보를 추출하는 작업
public interface ExRateExtractor {
    BigDecimal extract(String response) throws JsonProcessingException;
}
