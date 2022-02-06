package hello.core.member.service.impl;

import hello.core.member.entity.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.memory.MemoryMemberRepository;
import hello.core.member.service.MemberService;

// 구현체가 하나만 있을경우 관례상 인터페이스명 뒤에 Impl을 붙임
public class MemberServiceImpl implements MemberService {
    
    // OCP, DIP 원칙 무시됨 : 자바 코드의 한계(추상화, 구체화 모두 의존하고 있음)
    // 변경할 때 자바코드가 변경됨
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
