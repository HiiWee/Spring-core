package hello.core.autowired;

import hello.core.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)    // 자동 주입할 대상이 없으면 수정자 메소드 자체가 호출 안됨
        // 여기서 사용되는 Member객체는 스프링이 관리하는 빈이 아니다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired                      // 자동 주입할 대상이 없으면 null이 입력된다.
        // 여기서 사용되는 Member객체는 스프링이 관리하는 빈이 아니다.
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired                      // 자동 주입할 대상이 없으면 Optional.empty의 값이 입력된다.
        // 여기서 사용되는 Member객체는 스프링이 관리하는 빈이 아니다.
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
