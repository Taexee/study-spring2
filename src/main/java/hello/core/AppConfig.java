package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션의 구성정보(설정정보) 설정을 위한 어노테이션
@Configuration
public class AppConfig {
    // 애플리케이션 실제 동작에 필요한 구현 객체를 생성
    // 생성한 객체 인스턴스의 참조를 생성자를 통해 주입
    // 구현 객체는 외부(AppConfig)에 의해서 결정

    /*
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), new FixDiscountPolicy());
    }
    */

    // 역할에 따른 구현을 잘보이게 리팩토링
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // fix에서 rate로 변경
    }
    // 역활과 구현 클래스를 빠르게 파악 가능

    // @Bean 스프링컨테이너에 등록이 됨
}
