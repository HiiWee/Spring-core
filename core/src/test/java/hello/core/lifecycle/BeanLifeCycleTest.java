package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // ApplicationContext는 지원하지 않으므로 ConfigurableApplicationContext, AnnotationConfigApplicationContext를 이용해야함
                    // Application <-- ConfigurableApplicationContext <-- AnnotationConfigApplicationContext순으로 상속관계
                    // 직접 ApplicationContext를 가져다 사용할 때 close할 일이 별로없고, 하위까지 인터페이스들을 사용해야 이용 가능함
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
