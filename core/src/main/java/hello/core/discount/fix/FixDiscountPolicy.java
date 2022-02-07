package hello.core.discount.fix;

import hello.core.discount.DiscountPolicy;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    // 무조건 1000원 할인
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
