package hello.core;

import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.entity.Order;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
        // AppConfig를 이용해 어떤 저장소, 할인정책을 사용할지 결정된 서비스 객체를 주입받음
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // AppConfig 버전에서 Spring을 사용하는 버전으로 변경하기
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // 임의로 멤버를 생성하고 가입시켜놓음
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // itemA, 10000원 짜리 상품을 주문을 생성함
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        // 생성하고 결과를 보면 1000원의 할인정책이 적용된것을 볼 수있다.
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
