package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @Configuration도 자동으로 등록되므로
        // AppConfig가 자동으로 @Component에 등록되는것을 막기 위해 빼준다. (예제코드를 최대한 남기기 위해)
        // 필터타입은 어노테이션, 클래스는 Configuration을 빼준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}