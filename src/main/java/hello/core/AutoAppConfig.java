package hello.core;

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

}

// AppConfig에서 @Bean 으로 직접 설정정보 작성, 의존관계 명시
// @ComponentScan을 사용하게 되면 설정 정보가 없기때문에 의존관계 주입도 클래스 안에서 해결 @Autowired 이용

