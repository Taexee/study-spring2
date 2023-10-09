package hello.core.singleton;

public class SingletonService {
    // static 영역에 instance를 미리 하나 생성
    private static final SingletonService instance = new SingletonService();

    // getInstance() 메서드를 통해서만 조회 가능, 항상 같은 인스턴스 반환
    public static SingletonService getInstance() {
        return instance;
    }

    // 다른 곳에서 SingletonService 임의로 생성 가능
    // private 생성자 이용해 외부에서 임의로 new 키워드 사용하지 못하게 막음
    // 딱 1개의 객체 인스턴스만 사용 가능
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
