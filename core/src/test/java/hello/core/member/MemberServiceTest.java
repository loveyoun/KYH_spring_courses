package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;   //= new MemberServiceImpl();

    @BeforeEach
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();

        memberService = appconfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);

        Member fMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(fMember);
    }

}
