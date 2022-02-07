package hello.core.discount.rate;

import hello.core.discount.DiscountPolicy;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            // 현재 가격의 10% 할인가 적용
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
