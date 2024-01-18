package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
/*테스트 전에 transaction 걸고, 각 테스트 후에 자동으로 롤백(지워짐)이 된다. 사실 반영자체를 안 한다.
service 이런데 붙었을 떈, 정상 작동하고 테스트에 붙었을 때만 롤백한다.*/
class MemberServiceIntegrationTest {

    //테스트기 때문에 쉽게 하면 됨. 필드 기반 autowired.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /* 스프링 컨테이너한테 repository, service 다 내놔. 원래 생성자로 함.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        //같은 memberRepository가 사용된다.
        memberService = new MemberService(memberRepository);
    }
    AfterEach 대신에, Transactional로 함.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }*/


    @Test
    //@Commit //db에 저장이 된다.
    void 회원가입() {
        //GIVEN
        Member member = new Member();
        member.setName("spring");
        
        //WHEN
        Long saveId = memberService.join(member);
        
        //THEN
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    /*중복회원검증 예외*/
    @Test
    public void 중복_회원_예외(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        /*1
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail("예외가 발생해야 한다.");
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이.존.회");
        }
        */

        //2
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이.존.회");
    }

}