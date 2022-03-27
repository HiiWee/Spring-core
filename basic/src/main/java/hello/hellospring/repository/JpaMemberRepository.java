package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

   private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist는 영구 저장하다와 같은 뜻, 내부적으로 member.setId까지 완료해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // 조회 타입, 식별자 넣어주면 조회가 된다.
        return Optional.ofNullable(member); // 값이 없을수도 있으므로 ofNullable 이용
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

        // JPQL 이라는 쿼리 언어 : 원래는 테이블 대상으로 SQL을 날렸지만, 여기서는 Entity를 대상으로 쿼리를 날리면
        // 이것이 SQL로 번역이 된다. select시 Member 자체를 즉, 객체 자체를 select 하며 조회한다.
    }
}

