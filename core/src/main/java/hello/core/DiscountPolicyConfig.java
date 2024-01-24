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
public class DiscountPolicyConfig {

    // @Autowired AppConfig appConfig;
    // NoSuchBeanDefinitionException.
    // @ComponentScan 이면 의존관계 자동 주입 가능한데, 이건 먼저 등록되지 않을 수 있으니까.

    @Bean("rateDiscountPolicy")  // key = discountPolicy x, rateDiscountPolicy
    public DiscountPolicy discountPolicy() {
        RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();  // 수동 등록이, @Component 보다 우선.
        System.out.println("call DiscountPolicyConfig.rateDiscountPolicy = " + rateDiscountPolicy);

        return rateDiscountPolicy;
//        return appConfig.rateDiscountPolicy();  // 싱글톤 덕분에. 근데 수동 등록을 뭐더러 2번이나 쓰니?
//        return AppConfig.discountPolicy();  // static -> unreachable statement
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
        FixDiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
        System.out.println("call DiscountPolicyConfig.fixDiscountPolicy = " + fixDiscountPolicy);

        return fixDiscountPolicy;
    }


}
