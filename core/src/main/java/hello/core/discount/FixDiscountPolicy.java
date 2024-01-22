package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private final int DC = 1_000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) return DC;
        else return 0;
    }


}
