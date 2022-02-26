package hello.core.order.service.impl;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.fix.FixDiscountPolicy;
import hello.core.discount.rate.RateDiscountPolicy;
import hello.core.member.entity.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.order.entity.Order;
import hello.core.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// final이 붙은 필드를 가지고 매개인자가 있는 생성자를 자동으로 만들어준다. 또한 생성자가 1개면 @Autowired도 생략가능
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // 2.회원조회를 위한 필드 선언
    private final MemberRepository memberRepository;
    // 3. 할인적용을 위한 필드 선언
    private final DiscountPolicy discountPolicy;


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
