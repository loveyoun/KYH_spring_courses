package hello.hellospring;
//기본적으로 이 하위 패키지를 스프링 컨테이너가 다 뒤져서 스프링 빈으로 등록한다.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
