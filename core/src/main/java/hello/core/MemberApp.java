package hello.core;

/**
 * Non-desirable Test
 **/

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);

//        2)
//        Appconfig appconfig = new Appconfig();
//
//        MemberService memberService = appconfig.memberService();

//        1)
//        MemberService memberService = new MemberServiceImpl();


        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);   // 회원가입

        Member fMember = memberService.findMember(1L);   // 내 정보 확인

        System.out.println("findMember = " + fMember.getName());
        System.out.println("new member = " + member.getName());

//        System.out.println("웹 전용 스프링 컨테이너 확인 = " + ac);
    }

}
