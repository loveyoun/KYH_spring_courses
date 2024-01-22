package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    AppConfig appConfig = new AppConfig();  // == @BeforeEach
    MemberService memberService = appConfig.memberService();  // = new MemberServiceImpl();
    OrderService orderService = appConfig.orderService();  // = new OrderServiceImpl();

//    @BeforeEach
//    public void beforeEach() {
//        appConfig = new AppConfig();
//
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
//    }


    @Test
    void createOrder() {
        System.out.println("AppConfig = " + appConfig);
        System.out.println("memberService = " + memberService);
        System.out.println("orderService = " + orderService);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void beforeEachTest() {
        System.out.println("AppConfig = " + appConfig);
        System.out.println("memberService = " + memberService);
        System.out.println("orderService = " + orderService);
    }


//    @Test
//    void fieldInjectionTest(){
//        // Field Injection 시, 순수 Java Test 시 중간 변경이 불가능하다. 일일이 주입해주어야해서. 어차피 Setter 주입 해주어야 한다.
//
//        MemberServiceImpl memberServiceImpl = new MemberServiceImpl();
//        memberServiceImpl.setMemberRepository(new MemoryMemberRepository());
//
//        Long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
//        memberServiceImpl.join(member);   //memberRepository.save(member);
//
//        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
//        orderServiceImpl.setMemberRepository(new MemoryMemberRepository());
//        orderServiceImpl.setDiscountPolicy(new FixDiscountPolicy());
//
//        /* setXxx() 안 하면, NullPointException */
//        Order order = orderServiceImpl.createOrder(memberId, "itemA", 10000);
//        System.out.println("order = " + order);
//    }


}
