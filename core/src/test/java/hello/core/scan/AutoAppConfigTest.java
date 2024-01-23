package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
//        MemberService memberService = ac.getBean("memberService", MemberService.class);  // AppConfig 꺼
//        MemberService memberService = ac.getBean("memberServiceImpl", MemberService.class);  //AutoAppConfig 꺼
        /* AutoAppConfig.class @ComponentScan -> AppConfig.class 도 등록
         *
         * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
         * No qualifying bean of type 'hello.core.member.MemberServiceImpl' available: expected single matching bean but found 2:
         * memberServiceImpl,memberService
         * */

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
//        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);   //(OrderServiceImpl) ac.getBean(OrderService.class);
        MemberRepository memberRepository = orderService.getMemberRepository();   /** Children can't be cast to the parent **/

        System.out.println("memberRepository = " + memberRepository);
    }


}
