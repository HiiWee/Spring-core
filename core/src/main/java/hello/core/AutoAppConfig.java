package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // Component의 탐색할 위치를 지정할 수 있다. (지정한 패키지를 포함해서 하위 패키지를 모두 탐색)
        basePackages = "hello.core.member",

        // basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
        basePackageClasses = AutoAppConfig.class,

        // @Configuration도 자동으로 등록되므로
        // AppConfig가 자동으로 @Component에 등록되는것을 막기 위해 빼준다. (예제코드를 최대한 남기기 위해)
        // 필터타입은 어노테이션, 클래스는 Configuration을 빼준다.
        // 현재 클래스에도 @Configuration이 붙어있지만 new Annotation....(AutoAppConfig.class)에 넣어주기 때문에 exclude당하지 않음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}