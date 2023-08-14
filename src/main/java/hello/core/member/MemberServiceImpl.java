package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
