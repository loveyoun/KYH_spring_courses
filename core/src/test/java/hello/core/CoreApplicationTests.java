package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  // 되는 이유 : 매 @Test 마다 새롭게 ApplicationContext 하니까.
class CoreApplicationTests {

//	@Autowired   // 여기서는 가능
//	OrderService orderService;

    @Test
    void contextLoads() {
//        orderService.

        /*
         * java.lang.IllegalStateException: Failed to load ApplicationContext
         * Caused by: org.springframework.beans.factory.support.BeanDefinitionOverrideException:
         *
         * 보통 Bean 문제 때문에 load 가 안된다.
         */
    }


}
