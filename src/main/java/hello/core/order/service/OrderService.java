package hello.core.order.service;

import hello.core.order.entity.Order;

public interface OrderService {
    /**
     * @return 최종 주문 결과를 반환함(할인 결과를 포함한)
     * 회원 아이디, 상품이름, 상품가격을 넘겨주어 주문을 생성하고
     * 결과값으로 주문결과를 받음(Order)
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
