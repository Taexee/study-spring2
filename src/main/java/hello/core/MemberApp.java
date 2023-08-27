package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    // psvm 단축
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        // 직접 appconfig통해서 찾아옴 -> 스프링 이용해서 컨테이너로 관리하는 방법

        // 스프링 사용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링 컨테이너로 appcing를 매개변수로 넣어주면 빈을 컨테이너에 등록하고 관리해줌
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // join 테스트
        // cmd+option+v
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        
        // findMember 테스트
        Member findMember = memberService.findMember(1L);
        // soutv
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
