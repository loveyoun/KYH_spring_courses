package hello.core.member;

/**
 * 프론트와 직접 통신하는 서버
 * DB와 통신할 때는 클라이언트가 된다.
 **/

import org.springframework.stereotype.Component;


@Component
public class MemberServiceImpl implements MemberService {  // 서비스는 클라이언트.

    /*
    1) OCP, DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    2) Field 주입 : Not-recommended
    @Autowired   // ac.getBean(MemberRepository.class) 처럼 작동.
     */
    private final MemberRepository memberRepository;

    /*
    3) Setter : Not-recommended
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */

    /*
    4) 생성자 주입
    @Autowired  // 생성자 하나면 생략 가능
     */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // SRP : 프론트 요청 (DTO) -> 데이터 넘겨주기만 -> (처리는 다른 모듈) -> 최종 결과 데이터 리턴
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
