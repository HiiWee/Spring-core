package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// static import
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);

        // 검증하기 : memberService가 MemberServiceImpl이 객체 인스턴스인지 확인하기
        // memberService가 memberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        // 검증하기 : memberService가 MemberServiceImpl이 객체 인스턴스인지 확인하기
        // memberService가 memberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 역할이 아닌 구현에 의존함
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        // 검증하기 : memberService가 MemberServiceImpl이 객체 인스턴스인지 확인하기
        // memberService가 memberServiceImpl의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름을 조회X")
    void findBeaByNameX() {
        // MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        // Java 8의 람다 기능으로 검증하기 org.junit.jupiter.api.Assertions를 이용해야함 (Static import)
        // 의미는 아래의 ac.getBean로직을 실행하면 좌측의 예외가 던져지면 테스트 성공
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
