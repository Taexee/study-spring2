package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
