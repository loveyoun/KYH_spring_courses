package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    /*
    //이렇게 하면 memberService랑 new MemoryMemberRepository 해서, 두 개 다른 MemoryMemberRepository 만들어짐.
    //지금은 static 써서 괜찮지만, static이 아니면 다른 db가 되겠지.
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    */

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){  //실행할 때마다 독립적으로 실행되어야 하니까,
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //GIVEN
        Member member = new Member();
        member.setName("spring");
        
        //WHEN
        Long saveId = memberService.join(member);
        
        //THEN. 검증.
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

        //lambda 로직 실행시, IllegalStateException 예외가 터져야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이.존.회");
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}