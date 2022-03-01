package hello.core.discount.fix;

import hello.core.discount.DiscountPolicy;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    // 1000원만 할인 하므로 따로 필드로 선언해서 이용
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        // enum type은 ==을 이용함
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
