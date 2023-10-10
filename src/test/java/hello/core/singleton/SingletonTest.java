package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1.조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // jvm 메모리에 계속 객체가 생성, 웹 애플리케이션에서 고객 요청이 있을때마다 객체가 생성
        // 총 4개의 객체가 생성된다. MemberServiceImpl, MemoryMemberRepository 두개씩

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        // 순수한 DI 컨테이너 AppConfig는 요청할 때마다 객체를 생성, 메모리 낭비 심함
        // 해결 방안! 객체를 한 개만 생성하고 공유하도록 설계 = 싱글톤 패턴
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
//        new SingletonService(); new 생성자 사용 불가능
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인 = 같은 객체 반환 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 동일 객체인지 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);

        // 컨테이너를 이용해 요청될 때마다 객체 생성 없이 이미 만들어진 객체를 공유해서 재사용
        // 스프링 컨테이너 사용하면 싱글톤의 코드, 유연성이 떨어지는 등 단점을 보완할 수 있다.
        // 스프링의 기본 빈 등록 방식은 싱글톤. 빈 스코프통해 요청마다 객체 반환도 가능함
    }
}