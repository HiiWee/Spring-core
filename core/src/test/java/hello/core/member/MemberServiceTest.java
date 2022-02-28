package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // 테스트가 실행하기전 무조건 실행됨
    // 테스트 실행전에 AppConfig로 서비스 객체를 할당하고 테스트가 돌아간다.
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given : 어떠한 일이 주어졌을때, when : 이렇게 하면, then : 이렇게 된다.
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.
                assertThat(member).isEqualTo(findMember);
    }
}
