package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();
    //여러 컨트롤러가 갖다 사용할 수 있다.
    //컨테이너에 그냥 하나 등록해서 공용으로 사용하면 됨
    private final MemberService memberService;
    /*컨테이너가 이 객체 만들 때, 생성자 호출하는데, memberservice를 컨테이너에서 찾아서 연결해줌.
    1)필드주입. 별로임. 중간에 바꿀 수 없음.
    @Autowired private MemberService memberService;
    2)setter주입. 생성 되고, 나중에 setter 호출해서 주입.
    public으로 노출되어있는데, memberService는 중간에 바뀔 일이 없음.
    로딩 세팅(조립)때만 바꾸는 거임.
    3) 생성자 주입. 권장. */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //URL치는 것. HTTP get방식. 조회할 때.
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //data를 form에 넣어서 전달할 떄
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //key : "members"

        return "members/memberList";

    }

}


