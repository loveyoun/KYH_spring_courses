package hello.core;

/**
 * Non-desirable Test
 * MemberService, MemberRepository 테스트
 */

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main() {

        /*
        1)
        MemberService memberService = new MemberServiceImpl();

        2)
        Appconfig appconfig = new Appconfig();
        MemberService memberService = appconfig.memberService();
         */

//        3)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);  // 회원가입

        Member fMember = memberService.findMember(1L);  // 내 정보 확인

        System.out.println("findMember = " + fMember.getName());
        System.out.println("new member = " + member.getName());
    }


}
