package hello.core.singleton;

// 싱글턴 방식의 주의점 해결 : stateless한 설계
public class StatelessService {

    // private int price; // 상태를 유지하는 필드.

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // 여기가 문제 : stateful한 설계
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
