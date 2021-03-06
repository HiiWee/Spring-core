package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class SingletonStateServiceTest {

    @Test
    @DisplayName("싱글턴 방식의 주의점 : stateful한 설계")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        // ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);
    
        // ThreadA : A사용자 주문 금액 조회 -> 사용자 A가 주문한 금액이 나와야 하지만 사용자 B가 주문한 금액 20000원이 출력된다.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Test
    @DisplayName("싱글턴 방식의 주의점 해결 : stateless(무상턔)한 설계")
    void statelessServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        // ThreadA : A사용자 10000원 주문
        int userAPrice = statelessService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        int userBPrice = statelessService2.order("userB", 20000);

        // ThreadA : A사용자 주문 금액 조회 -> 공유 필드를 사용하지 않으므로 10000원 정삭 출력됨
        System.out.println("userAPrice = " + userAPrice);

        // UserA와 USerB의 주문 가격이 다름을 검증한다.
        Assertions.assertThat(userAPrice).isNotSameAs(userBPrice);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }


}