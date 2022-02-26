package hello.core.member.service.impl;

import hello.core.member.entity.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현체가 하나만 있을경우 관례상 인터페이스명 뒤에 Impl을 붙임
@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // DIP 지켜짐 : 인터페이스에만 의존하고 있음 (AppConfig가 객체 생성, 연결 담당함)
    // OCP 지켜짐 : 다형성 사용하고 클라이언트가 DIP를 지킴, 클라이언트 코드 변경없이 주입 객체 변경가능
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도 : 싱글톤 유지 확인을 위한 getter 생성
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
