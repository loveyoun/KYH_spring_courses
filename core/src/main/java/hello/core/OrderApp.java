package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

//        2)
//        Appconfig appconfig = new Appconfig();
//
//        MemberService memberService = appconfig.memberService();
//        OrderService orderService = appconfig.orderService();

//        1)
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);  // 회원가입

        Order order = orderService.createOrder(memberId, "itemA", 10000); // 장바구니

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }

}
