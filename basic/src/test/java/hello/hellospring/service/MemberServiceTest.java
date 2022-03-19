package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given : 뭔가가 주어 졌고
        Member member = new Member();
        member.setName("spring");

        // when : 이것을 실행했을때
        Long saveId = memberService.join(member);

        // then : 이러한 결과가 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // try-catch를 대신하여 사용 가능 : 예외를 반환하기 때문에 받아서 예외 메시지를 검증하는 테스트도 할 수 있다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

        // then
    }

    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("hoseok");

        Member member2 = new Member();
        member2.setName("hello");

        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        // given
        Member member = new Member();
        member.setName("hoseok");
        memberService.join(member);

        // when
        Member result = memberService.findOne(member.getId()).get();

        // then
        assertThat(member).isEqualTo(result);

    }
}