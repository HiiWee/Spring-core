package hello.core.lifecycle;

// 간단한 외부 네트워크에 미리 연결하는 객체 (단순 문자열 출력)
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    // 서비스 시작시 호출할 메소드
    public void connect() {
        System.out.println("connect: " + url);
    }
    
    // 실제 연결이 된 상태에서 call을 하여 메시지를 던짐
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }
    
    // 서비스 종료시 호출(미리 연결을 끊기 위해)
    public void disconnect() {
        System.out.println("close: " + url);
    }
}
