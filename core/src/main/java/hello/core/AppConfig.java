package hello.core;

import hello.core.discount.fix.FixDiscountPolicy;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;

// 애플리케이션 전체를 설정하고 구성함
// 구현 객체를 생성하고, 연결하는 책임을 가짐
public class AppConfig {

    // memberService를 만들어보자
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}


