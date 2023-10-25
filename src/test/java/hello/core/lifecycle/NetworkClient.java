package hello.core.lifecycle;

public class NetworkClient {
    // 접속해야될 서버의 url
    private String url;

    // 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");

    }

    // 외부에서 set
    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출 메서드
    public void connect() {
        System.out.println("connect : " + url);
    }

    // 연결했을때 call해서 메세지 전달
    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료
    public void disconnect() {
        System.out.println("close : " + url);
    }
}
