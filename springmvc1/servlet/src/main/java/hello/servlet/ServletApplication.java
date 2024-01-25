package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan   // 하위 패키지의 모든 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

    // 스프링 부트가 설정 정보 가져와서 자동으로
//	@Bean
//	ViewResolver internalResourceViewResolver() {
//		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
//	}


}
