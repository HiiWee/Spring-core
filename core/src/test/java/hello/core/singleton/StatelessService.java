package hello.core.singleton;

// 싱글턴 방식의 주의점 해결 : stateless한 설계
public class StatelessService {

    // private int price; // 상태를 유지하는 필드를 제거함 : 싱글톤에선 공유 필드가 되므로

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // 여기가 문제 : stateful한 설계를 지우고 값 자체를 반환
        return price; // 바로 값을 반환하여 stateful한 문제를 해결
    }

    // 사용하지 않는 메소드
//    public int getPrice() {
//        return price;
//    }
}
