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

    // 수동 등록 안 함
    // @Bean 으로 설정 정보 명시, 의존관계도 명시 안 함

    /*
    // @Configuration 없을 때 대신 스프링의 의존관계 자동 주입 사용할 수 있다.
    // 스프링 빈에 있는 거 가져오기.
    @Autowired MemberRepository memberRepository;
    @Autowired DiscountPolicy discountPolicy;

    // 수동 등록 추가로 하면, NoUniqueBeanDefinitionException
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
