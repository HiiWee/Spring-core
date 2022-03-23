package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// DataSource는 스프링이 생성해주므로 자동 DI 해주고, 기존의 MemoryMemberRepository를
// JdbcMemberRepository로 변경만 해주면서 dataSource를 넘겨준다.

// 어떤 코드도 변경하지 않고, JdbcMemberRepository라는 클래스를만들고(인터페이스를 구현해 확장)
// 스프링이 제공하는 설정파일만 변경하였다.

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }

}
