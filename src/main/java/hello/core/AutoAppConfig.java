package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core", // 탐색 시작 위치 지정 가능
//        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 위치로 지정 hello.core 패키지부터 탐색
        // 미지정시 @ComponentScan을 붙인 클래스의 패키지를 탐색 위치로 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// ComponentScan -> @Component 애노테이션이 붙은 클래스를 찾아서 알아서 스프링 빈으로 등록해줌
// 예제코드 유지. AppConfig, TestConfig 등 제외하기 위해 Configuration클래스 excludeFilters 사용해 제외
public class AutoAppConfig {
    // 수동으로 빈 등록, 자동으로 빈 등록 충돌 테스트
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 스프링 : 수동 등록 빈이 우선권 -> 수동 빈이 자동 빈을 오버라이딩 해버림, 버그 발생 가능
    // 스프링 부트 : 에러 발생, spring.main.allow-bean-definition-overriding=false 를 기본값으로 설정되어 있음
    //
}

// AppConfig에서 @Bean 으로 직접 설정정보 작성, 의존관계 명시
// @ComponentScan을 사용하게 되면 설정 정보가 없기때문에 의존관계 주입도 클래스 안에서 해결 @Autowired 이용

// 컴포넌트 스캔의 기본 대상
// @Component, @Controller, @Service, @Repository, @Configuration