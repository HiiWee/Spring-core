package hello.core.singleton;

// 싱글턴 방식의 주의점 : stateful한 설계
public class StatefulService {

    private int price; // 상태를 유지하는 필드.

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제 : stateful한 설계
    }

    public int getPrice() {
        return price;
    }
}
