package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 비즈니스에 가까운 용어를 사용해야 함 (서비스는 비즈니스에 의존적으로 설계)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x

        validateDuplicateMember(member);    // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 검증
     */
    private void validateDuplicateMember(Member member) {
        // result가 null이 아니라면 아래 예외 던져짐 Optional로 감쌋기 때문에 여러 메소드 사용 가능
        // 다만 아래와 같이 여러 로직이 사용되면 메소드로 뽑아내는것이 좋다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 한명의 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
