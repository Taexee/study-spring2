package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        // 순수한 DI 컨테이너 AppConfig는 요청할 때마다 객체를 생성, 메모리 낭비 심함
        // 해결 방안! 객체를 한 개만 생성하고 공유하도록 설계 = 싱글톤 패턴
    }
}