package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//@Component
//@Qualifier("mainDiscountPolicy")
//@Primary
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private final int PER = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP)
            return price * PER / 100;
        else return 0;
    }


}
