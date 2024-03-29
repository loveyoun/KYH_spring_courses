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
        /*
         * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
         * No qualifying bean of type 'hello.core.member.MemberServiceImpl' available: expected single matching bean but found 2:
         * memberServiceImpl,memberService (AutoAppConfig.class, AppConfig.class)
         */

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
//        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class); (OrderServiceImpl) ac.getBean(OrderService.class);
        /** Children can't be cast to the parent **/

        MemberRepository memberRepository = orderService.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }


}
