package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  // 싱글톤. @ComponentScan 대상 -> AppConfig.class 도 스캔
@ComponentScan(basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// 메인 설정 정보
public class AutoAppConfig {
    // @ComponentScan 하면, 의존관계 주입이 자동으로 안되기 때문에, 각 생성자에 @Autowired 를 해줘야 한다.

    /*
    수동 등록 추가로 하면, NoUniqueBeanDefinitionException

    // 스프링 빈에 있는 거 가져와서 스프링의 의존관계 자동 주입 사용할 수 있다.
    @Autowired MemberRepository memberRepository;
    @Autowired DiscountPolicy discountPolicy;
    @Bean
    OrderService orderService() {
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }
    // or
    @Bean
    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
     */


}
