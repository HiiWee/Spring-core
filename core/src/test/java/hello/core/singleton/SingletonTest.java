package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출 할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출 할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        
        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 실제로 참조값이 다르게 된다. 이렇게 되면 JVM메모리에 계속 객체가 생성되어 올라간다.
        // 여기서 발생하는 문제? : 스프링은 웹 애플리케이션을 주로 만든다. 이의 특징은 고객의 요청이 매우 많다.
        // 따라서 이런식으로 계속 객체를 생성하는 방식은 효율적이지 못하다.

        // memberService1 != memberService2 검증
        assertThat(memberService1).isNotSameAs(memberService2);
    }
}
