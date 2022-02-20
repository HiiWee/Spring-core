package hello.core.member.repository.memory;

import hello.core.member.entity.Member;
import hello.core.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {
    
    // HashMap은 동시성 이슈가 존재함 : ConcurrentHashMap 사용을 추천
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
