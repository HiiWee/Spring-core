package hello.core.order.service.impl;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.fix.FixDiscountPolicy;
import hello.core.discount.rate.RateDiscountPolicy;
import hello.core.member.entity.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.order.entity.Order;
import hello.core.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

    // 2.회원조회를 위한 필드 선언
    private final MemberRepository memberRepository;
    // 3. 할인적용을 위한 필드 선언
    private DiscountPolicy discountPolicy;

    // DIP 지켜짐 : 인터페이스에만 의존하고 있음 (AppConfig가 객체 생성, 연결 담당함)
    // OCP 지켜짐 : 다형성 사용하고 클라이언트가 DIP를 지킴, 클라이언트 코드 변경없이 주입 객체 변경가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 1. 주문생성(회원id, 상품명, 상품가격 인자로 넘겨줌)
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 2. 회원 조회 (회원 저장소에서 memberId를 통해 Member객체를 받음)
        Member member = memberRepository.findById(memberId);

        // 3. 할인 적용 (할인 정책에서 Member 객체와 상품가격을 넘겨주어 할인가격을 받는다.)
        // OrderService입장에선 할인에 대한것은 관심이 없음 : 할인 관련은 discountPolicy에게 전적으로 맡김 --> 단일 책임 원칙 잘 지켜짐
        int discountPrice = discountPolicy.discount(member, itemPrice);     // Grade를 넘겨도 되지만 Member객체를 넘김 --> 프로젝트 상황따라 유동적으로

        // 4. 주문 결과 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도 : 싱글톤 유지 확인을 위한 getter 생성
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
