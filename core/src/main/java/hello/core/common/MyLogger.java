package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)   // 가짜로 만드는 것
public class MyLogger {

    private String uuid;
    private String requestURL;
//    private AnnotationConfigApplicationContext ac;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }


    @PostConstruct
    public void init() {
//        System.out.println("웹 전용 스프링 컨테이너 확인 = " + ac);
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean created : " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean closed : " + this);
    }

}