package hello.core.discount.rate;

import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// static import를 통해 메소드 이름만 가지고 사용 가능
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // Test는 성공테스트도 중요하지만 실패테스트도 중요함
    // 성공테스트
    @Test
    // 테스트의 이름 부여
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vio_o() {
        // given : 어떠한 일이 주어졌을때, when : 이렇게 하면, then : 이렇게 된다.

        // given :
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    // 실패 테스트
    @Test
    // 테스트의 이름 부여
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        // given : 어떠한 일이 주어졌을때, when : 이렇게 하면, then : 이렇게 된다.

        // given :
        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}