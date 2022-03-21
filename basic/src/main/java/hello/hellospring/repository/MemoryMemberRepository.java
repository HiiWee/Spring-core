package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 공유자원으로 HashMap사용시 동시성 문제가 있을 수 있음
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // key값을 생성해줌

    @Override
    public Member save(Member member) {
        // 여기선 id값만 세팅하는데 멤버의 이름은 고객이 회원가입할 때 이미 적혀있음
        // 시스템에 저장하기 위한 id값만 넣어주고 저장하면 됨
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optional로 감싸게 되면 null이어도 감싸서 반환해줌 -> 이후 클라이언트에서 처리할 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // loop를 돌리며 member.getName()이 파라미터의 name과 같으면 필터링이 되고 그 중에서 findAny는 하나라도 찾으면 반환된다.
        // 결과가 Optional로 반환됨 (loop를 돌며 하나라도 찾으면 그것을 반환하지만 끝까지 없게되면 Optional에 null이 포함되어 반환된다.)
    }

    @Override
    public List<Member> findAll() {
        // 리스트에 store에 있는 Member객체들을 담아서 반환한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
