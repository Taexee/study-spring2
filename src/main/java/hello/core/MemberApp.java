package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    // psvm 단축
    public static void main(String[] args) {
        
        MemberService memberService = new MemberServiceImpl();
        
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
