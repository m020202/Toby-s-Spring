package spring.HelloSpring.api;

import java.io.IOException;
import java.net.URI;

// API를 실행하고, 서버로부터 받은 응답을 가져오는 작업
public interface ApiExecutor {
    String execute(URI uri) throws IOException;
}
