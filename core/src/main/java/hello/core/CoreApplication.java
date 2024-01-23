package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

//    @Autowired private static ApplicationContext ac;

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);

        // Web 라이브러리를 사용 안 하는데, AnnotationConfigServletWebServerApplicationContext 를 기대...?
//        MemberApp.main();
//        System.out.println("웹 전용 스프링 컨테이너 확인 = " + ac);
    }


}
