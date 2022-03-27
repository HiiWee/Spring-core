package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
// 테스트가 끝나면 커밋이 아닌 롤백을 해주어 DB에 넣었던 데이터들이 반영되지 않는다.
// 이를통해서 반복해서 테스트 할 수 있게한다.
class MemberServiceIntegrationTest {

    // 테스트 케이스는 다른곳에 사용하지 않기때문에 필드 인젝션도 괜찮음
    // 구현체는 SpringConfig에서 올라올것임
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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
        assertThat(member.getId()).isEqualTo(result.getId());

    }
}