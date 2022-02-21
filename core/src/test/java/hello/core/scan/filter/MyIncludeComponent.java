package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)   // 클래스 / 인터페이스 / 열거 타입(enum)에 Annotation을 붙임
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 타입을 어디까지 보유할지를 설정, 런타임시까지 사용가능(런타임 종료까지 메모리가 살아있다)
@Documented
public @interface MyIncludeComponent {
}
