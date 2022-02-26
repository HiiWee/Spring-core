package hello.core.order.service.impl;

import hello.core.discount.fix.FixDiscountPolicy;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.order.entity.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 테스트를 위한 더미 MemberRepository 객체를 생성한다.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        // 테스트를 위한 임의의 Member를 등록한다.
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        // 생성자 주입을 이용해 OrderServiceImpl 객체를 생성함 여기서 new OrderServiceImp();를 이용하면 컴파일 오류 발생
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        // 검증
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}