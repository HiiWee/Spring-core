package hello.core.singleton;

class SingletonService {

    // 자기 자신을 내부에 static 선언하면 class 레벨에 올라가므로 1개의 객체만 존재하게 된다.
    private static final SingletonService instance = new SingletonService();

    // 위와 같이 작성하면 JVM이 올라갈때 SingletonService의 static영역에 new를 내부적으로 실행해 자기자신의 객체를 생성하고 instance에 참조를 넣어놓음
    // 그렇게 되면 객체 인스턴스를 하나만 생성해서 instance변수가 참조함


    private SingletonService() {
        // 아래와 같이 main에서 사용자가 new를 이용해 생성할 수 있으므로 생성자를 private으로 만든다.
        // SingletonService singletonService = new SingletonService();
    }

    // getInstance()는 조회할때 사용된다.
    public static SingletonService getInstance() {
        return instance;
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    } 

}

/**
 *
 * 위와같이 하면 완벽한 Singleton이 된다.
 * 자바가 올라가면서 static 영역의 객체를 초기화 하여 1개의 객체만 가지게 되고
 * 인스턴스의 참조를 꺼낼 수 있는 것은 getInstance() 밖에 없고
 * 객체를 따로 생성하는 방법은 아무것도 없다.(생성자가 private 이므로)
 * 정말 잘 설계한 객체는 컴파일오류가 발생하는것이 잘 설계한 객체
 *
 * */
