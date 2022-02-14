package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// JUnit5 부터는 public 설정 생략 가능
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    // JUnit5 부터는 public 설정 생략 가능
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // iter tab하면 자동 완성됨
        for (String beanDefinitionName : beanDefinitionNames) {
            // 타입 지정 안했기 떄문에 Object타입으로 반환받음
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    // JUnit5 부터는 public 설정 생략 가능
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // iter tab하면 자동 완성됨
        for (String beanDefinitionName : beanDefinitionNames) {
            // 빈 하나하나에 대한 메타데이터 정보를 받아옴
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION : 스프링이 내부 작업을 위해 등록된 빈이 아니라 직접 등록한 애플리케이션 빈을 선별하기 위한 상수혹은 외부라이브러리 등등
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);

            }
        }
    }
}
