package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    @DisplayName("해당 타입의 스프링 빈이 다 필요한 경우 : 클라리언트가 할인의 종류를 선택(fix, rate")
    void findAllBean() {
        // DiscountPolicy의 스프링 빈을 이용하기 위해 AutoAppConfig도 스프링 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        // 임의의 멤버, 가격과 정책코드를 넘겨 실제 할인가를 받아옴
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        // fix정책은 무조건 1000원 할인임을 검증
        assertThat(discountPrice).isEqualTo(1000);
        assertThat(discountService).isInstanceOf(DiscountService.class);

        // rate정책은 10%할인율이 적용됨을 검증
        discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discountPrice).isEqualTo(2000);
    }


    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired  // 컬렉션 객체들도 자동주입이 가능하다. 일반적으로 Map은 key값으로 빈의 이름을, value로 객체를 삽입한다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        // Member객체와 가격, 정책코드를 넘겨주고 Map에서 알맞은 정책 코드를 이용해 실제 할인율을 넘겨주는 로직
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}

