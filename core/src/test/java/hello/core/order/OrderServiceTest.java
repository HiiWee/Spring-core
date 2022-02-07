package hello.core.order;

import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.entity.Order;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // 프리미티브 타입은 NULL이 입력이 안되므로 wrapper타입 사용
        Long memberId = 1L;
        // 멤버 임의 생성 및 등록
        Member member =  new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문을 하나 추가하고 할인금액이 1000원이 맞는지 테스트하는 코드
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
