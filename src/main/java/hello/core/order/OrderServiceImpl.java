package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // 생성자 주입

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 추상과 구체클래슼 모두 의존하고 있다 DIP위반! -> 추상에만 의존하도록 변경
    // fix에서 rate로 변경하는 순간 OrderServiceImpl 소스코드를 변경해야 하기 때문에 OCP도 위반!

    private final DiscountPolicy discountPolicy;
    // 인터페이스(추상)에만 의존하도록 변경 -> nullpointerException 발생
    // 구현 객체를 대신 생성하고 주입필요
    // 생성자 주입

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 조회
        Member member = memberRepository.findById(memberId);

        // 2. discountPolicy에 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 할인에 관련된 모든것은 discountPolicy 책임, 결과만 던져줌
        // 할인 수정이 필요한 경우 할인에서만 수정, 주문은 수정할 필요없음
        // 단일 책임의 원칙을 지킨 좋은 설계!

        // 3. 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
