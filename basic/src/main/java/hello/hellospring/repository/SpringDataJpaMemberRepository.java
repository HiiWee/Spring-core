package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 객체, 식별자(PK) 자료형
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 이렇게 인터페이스로 만들어 놓고 JpaRepository<>를 상속받고 있으면 스프링 데이터 JPA가
    // SpringDataJpaMemberRepository의 구현체를 자동으로 만들고 스프링 빈에 등록한다.
    // 우리는 그걸 가져다 사용하면 된다.

    @Override
    Optional<Member> findByName(String name);
}
