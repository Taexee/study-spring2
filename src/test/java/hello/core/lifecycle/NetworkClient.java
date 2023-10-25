package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    // 접속해야될 서버의 url
    private String url;

    // 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
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

    @Override
    public void afterPropertiesSet() throws Exception {
        // 의존관계 주입이 끝나고 호출
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        // 빈이 종료될 때 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
