package hello.core;

/**
 * My
 * Creat to make sure instance classes for interface DiscountPolicy
 **/

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
//@Configuration   // 설정 정보 긴 한데... Test 를 위해 AutoAppConfig 와 AppConfig 를 위해
//@ComponentScan   // 이건 굳이 안 넣어도 돼. 메인 설정 정보에서 시작하면 됨. 그리고 @SpringBootApplication 안에.
public class DiscountPolicyConfig {

//    @Autowired
//    AppConfig appConfig;  // NoSuchBeanDefinitionException


    @Bean("rateDiscountPolicy")
    public DiscountPolicy discountPolicy() {
        RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();   // 수동 등록이, @Component 보다 우선.
        System.out.println("call DiscountPolicyConfig.rateDiscountPolicy = " + rateDiscountPolicy);

        return rateDiscountPolicy;

//        return appConfig.rateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
        FixDiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
        System.out.println("call DiscountPolicyConfig.fixDiscountPolicy = " + fixDiscountPolicy);

        return fixDiscountPolicy;
    }


}
