package spring.HelloSpring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spring.HelloSpring.api.ApiExecutor;
import spring.HelloSpring.api.SimpleApiExecutor;
import spring.HelloSpring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/";

        return runApiForExRate(url, new SimpleApiExecutor());
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor) {
        // URL을 준비하고 예외처리를 위한 작업
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 응답으로 받은 JSON 문자열을 파싱하고 필요한 환율 정보를 추출하는 작업
    private static BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
