package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        // 빈 조회
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
//        statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB : B사용자 10000원 주문
//        statefulService2.order("userB", 20000);
        int userBPrice = statefulService1.order("userA", 10000);

        // ThreadA : A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("userA Price = " + userAPrice);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        // 20000원 조회됨
        // statefulService의 price 필드는 공요되는 필드. 특정 클라이언트가 값을 변경하며 오류 발생
        // 한 객체 인스턴스를 공유하는 싱글톤의 경우 무상태 설계가 필요하다
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}