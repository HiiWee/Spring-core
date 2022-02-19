package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.repository.MemberRepository;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 인터페이스 타입이 아닌, 구현체를 꺼내는 이유는 테스트 용도로 만든 getMemberRepository()를 이용할 수 있음
        // 구체 타입으로 꺼내는 방식은 옳지 못함
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        // MemberRepository를 getter가 아닌 직접 꺼내서 비교
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 3개다 동일한 객체가 반환됨 --> 즉 싱글톤이 유지된다.
        // 따지고 보면 new MemoryMberRepository 가 3번 실행됐을것 같지만 그렇지 않음
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        /**
         *
         * 실제로 확인해보면 memberRepository의 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다.
         * AppConfig의 자바 코드를 보면 분명히 각 3번의 new MemoryMemberRepository()가 호출해서 다른 인스턴스가 생성되지 않음
         *
         * */
    }
}
