package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: A 고객이 10000원 주문
        int userAPrice = statefulService1.order("userAPrice", 10000);
        // ThreadB: B 고객이 20000원 주문
        int userBPrice = statefulService2.order("userBPrice", 20000);

        // ThreadA: A 고객이 주문 금액 조회
//        int price = statefulService1.getPrice();

        // ThreadA: A 고객은 10000원을 예상했지만, 20000원 출력
//        System.out.println("price = " + price);


//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }


    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}