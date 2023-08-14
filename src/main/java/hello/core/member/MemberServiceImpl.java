package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberRepository는 인터페이스를 의존
    // 실제 할당하는 MemoryMemberRepository가 구현체를 의존
    // MemberServiceImpl는 추상화, 구체화 모두 의존 DIP 위반

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    // 다형성에의해 메모리리포지토리에서 구현한(오버라이딩) save 호출

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
