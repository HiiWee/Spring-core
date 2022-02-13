package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.fix.FixDiscountPolicy;
import hello.core.discount.rate.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션 전체를 설정하고 구성함
// 구현 객체를 생성하고, 연결하는 책임을 가짐
// 이렇게 리펙토링하여 멤버 서비스 역할, 멤버 레포지토리역할, 주문 서비스 역할, 할인 정책 역할이 드러나고
// 구현클래스도 한눈에 들어옴
@Configuration
public class AppConfig {
    
    // 멤버 서비스 역할
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 저장소 역할 : 저장소 변경시 아래 코드만 변경하면 된다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 주문 서비스 역할
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책 역할 : 정책변경시 아래 코드만 변경하면 됨
    @Bean
    public DiscountPolicy discountPolicy() {
        // 정책 변경
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}


