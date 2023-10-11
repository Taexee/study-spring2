package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        // new MemoryMemberRepository()가 3번 호출되었지만 인스턴스 한 개를 같이 공유함 싱글톤 유지

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        // AppConfig 부모 타입 조회, 자식인 AppConfig@CGLIB 조회되는 것

        System.out.println("bean.getClass() = " + bean.getClass());
        // class hello.core.AppConfig$$SpringCGLIB$$0
        // 스프링이 CGLIB이라는 바이트코드 조작 라이브러리를 사용해 AppConfig 클래스를 상속받은 클래스를 생성 후 빈으로 등록
        // 이 클래스가 싱글톤이 보장되도록 해줌
        // @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 빈 반환, 없으면 생성해서 빈으로 등록하고 반환하는 코드가 동적으로 생성 -> 싱글톤 유지되게 해줌


        // @Configuration 없이 @Bean만 적용?
        // 스프링 빈으로 등록되지만 싱글톤 보장 못함
        // 호출될때마다 인스턴스 생성, 주입된 Repository도 모두 생성됨 -> 컨테이너가 관리하지 않음

    }
}