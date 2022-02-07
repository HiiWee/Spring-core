package hello.core.discount;

import hello.core.member.entity.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상의 할인 금액
     */
    int discount(Member member, int price);

}
