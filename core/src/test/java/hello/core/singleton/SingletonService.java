package hello.core.singleton;

public class SingletonService {

    // static : 클래스 레벨. 딱 하나만 존재.
    private static final SingletonService instance = new SingletonService();

    private SingletonService() {

    }


    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
