package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration   // @ComponentScan 대상. Singleton 을 보장한다.
public class AppConfig {

//    @Autowired DiscountPolicyConfig discountPolicyConfig;   // NoSuchBeanDefinitionException

    // @Bean memberService() -> new MemoryMemberRepository();
    // @Bean orderService() -> new MemoryMemberRepository();
    // 싱글톤이 깨질까?

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository
    //
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService


    //@Configuration 없을 때 대신 스프링의 의존관계 자동 주입 사용할 수 있다.
//    @Autowired MemberRepository memberRepository;

    @Bean
    // 생성자를 통해 객체 주입
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");

        // 메소드명을 통해 역할 드러남. 인자 중복 제거.
        return new MemberServiceImpl(memberRepository());
//        return null;
    }

    @Bean /* XmlAppContext 사용 시 private 이면 안된다 */
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());   //discountPolicy());
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

//        return null;      /* BeanNotOfRequiredTypeException : OrderService -> null */
    }

    @Bean
    public DiscountPolicy discountPolicy() {   /* DiscountPolicyConfig 랑 중복 등록됨 -> excludeFilters */
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

//    /* DiscountPolicyConfig.class 와 중복 */
//    @Bean
//    public DiscountPolicy rateDiscountPolicy() {
//        return new RateDiscountPolicy();
//    }

}
