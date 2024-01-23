package hello.core.order;

/**
 * 프론트와 직접 통신하는 서버
 * 클라이언트가 되어 DB, 할인 도메인과 통신한다.
 */

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    /*
    3) field 주입
    @Autowired
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy rateDiscountPolicy;
    /*
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
     */

    /*
    1)
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    2) AppConfig + 생성자
    private final DiscountPolicy discountPolicy;

    4) Setter 주입
    // field 주입과 다르게, field 에 final 해줄 수 없다.
    // MemberRepository 를 스프링 빈으로 생성 미리 안 해도 주입이 된다. new() 해도 된다는 뜻. 생성자 주입과 비슷.
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        System.out.println("OrderServiceImpl.setMemberRepository memberRepository = " + memberRepository);
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        System.out.println("OrderServiceImpl.setDiscountPolicy discountPolicy = " + discountPolicy);
    }

    @Autowired private final DiscountPolicy rateDiscountPolicy  // == 생성자 파라미터 DiscountPolicy rateDiscountPolicy

    5) 생성자 주입. == @RequiredArgsConstructor. final 붙은 것들로 생성자 만듦.
    @Autowired   // 스프링 컨테이너 : OrderServiceImpl 생성 -> new OrderServiceImpl(memberRepository, discountPolicy);
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    // UnsatisfiedDependencyException -> NoUniqueBeanDefinitionException
    // 1. DiscountPolicy discountPolicy -> DiscountPolicy rateDiscountPolicy
    // 2. @Qualifier("mainDiscountPolicy")
    // 3. @MainDiscountPolicy

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        System.out.println("OrderServiceImpl.OrderServiceImpl memberRepository = " + memberRepository);
        System.out.println("OrderServiceImpl.OrderServiceImpl discountPolicy = " + discountPolicy);

    }
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
