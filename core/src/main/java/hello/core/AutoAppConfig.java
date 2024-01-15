package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration   // : @ComponentScan 대상 -> AppConfig.class 도 스캔
@ComponentScan(basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

    // 수동 등록 안 함
    // @Bean 으로 설정 정보 명시, 의존관계도 명시 안 함


//    의존관계 자동 주입 ???
//
//    @Autowired MemberRepository memberRepository;
//    @Autowired DiscountPolicy discountPolicy;
//
//    or
//
//    @Bean   /* 수동 등록 추가로 하면, NoUniqueBeanDefinitionException */
//    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }


    // 수동 등록
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}
