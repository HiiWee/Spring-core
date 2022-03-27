package hello.hellospring.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA를 사용하기 위해선 엔티티 매핑을 해야한다. ORM
@Entity
public class Member {

    // 고객이 아닌 시스템이 정하는 임의의 ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // @Id는 pk, pk는 db에서 값을 생성하는 것을 아이덴티티 전략이라 한다.
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
