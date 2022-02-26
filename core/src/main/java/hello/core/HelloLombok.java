package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok이 자동으로 getters and setters를 자동으로 생성해준다.
@Getter
@Setter
@ToString
// 또한 생성자도 만들어준다.
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hello");


        System.out.println("helloLombok.getName() = " + helloLombok.getName());
        System.out.println("helloLombok = " + helloLombok);
    }
}
