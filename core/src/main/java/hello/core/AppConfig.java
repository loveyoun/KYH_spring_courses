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


@Configuration   // @ComponentScan 대상. Singleton 보장.
public class AppConfig {  // 생성자를 통해 객체 주입

    /* XmlAppContext 사용 시 private 이면 안 된다 */

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");

        // 메소드명으로 역할 드러냄 + 인자 중복 제거.
        return new MemberServiceImpl(memberRepository());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), discountPolicy());

//        return null;  // BeanNotOfRequiredTypeException : OrderService -> null
    }

    @Bean
    public DiscountPolicy discountPolicy() {  // 수동 등록하는 DiscountPolicyConfig 랑 중복 등록됨.
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
