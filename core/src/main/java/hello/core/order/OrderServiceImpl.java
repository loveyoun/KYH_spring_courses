package hello.core.order;

/**
 * 프론트와 직접 통신하는 서버
 * 클라이언트가 되어 DB, 할인 도메인과 통신한다.
 **/

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // @Autowired
    private final MemberRepository memberRepository;
    // @Autowired
    private final DiscountPolicy rateDiscountPolicy;
    /* DiscountPolicyConfig 때문에 naming `discountPolicy` 에러
     *
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'orderServiceImpl' defined in file
     * [D:\Spring Study\core\out\production\classes\hello\core\order\OrderServiceImpl.class]:
     * Unsatisfied dependency expressed through constructor parameter 1;
     *
     * nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No qualifying bean of type 'hello.core.discount.DiscountPolicy' available:
     * expected at least 1 bean which qualifies as autowired candidate. Dependency annotations: {}
     * */

//    1)
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

//    2) AppConfig + 생성자
//    private final DiscountPolicy discountPolicy;

//    3) Setter 주입
//    @Autowired
//    // field 주입과 다르게, field 에 final 해줄 수 없다.
//    // MemberRepository 를 스프링빈으로 생성 미리 안 해도 주입이 된다. new() 해도 된다는 뜻. 생성자 주입과 비슷.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//        System.out.println("OrderServiceImpl.setMemberRepository memberRepository = " + memberRepository);
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//        System.out.println("OrderServiceImpl.setDiscountPolicy discountPolicy = " + discountPolicy);
//    }

//    @Autowired private final DiscountPolicy rateDiscountPolicy  // == 생성자 파라미터 DiscountPolicy rateDiscountPolicy

    /*
    @Autowired   // 스프링 컨테이너 : new OrderServiceImpl -> new OrderServiceImpl(memberRepository, discountPolicy);
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    /* UnsatisfiedDependencyException -> NoUniqueBeanDefinitionException
    // DiscountPolicy discountPolicy -> DiscountPolicy rateDiscountPolicy
    // @Qualifier("mainDiscountPolicy") -> @MainDiscountPolicy

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        System.out.println("OrderServiceImpl.OrderServiceImpl memberRepository = " + memberRepository);
        System.out.println("OrderServiceImpl.OrderServiceImpl discountPolicy = " + discountPolicy);

    }   // == @RequiredArgsConstructor. final 붙은 것들로 생성자 만들어줌
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 단일 책임 원칙(SRP) : 할인 도메인은 할인만, 주문 도메인은 주문만.
        int discountPrice = rateDiscountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // 테스트. Not Recommended.
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
