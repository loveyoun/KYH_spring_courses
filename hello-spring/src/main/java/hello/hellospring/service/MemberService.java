package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional//JPA쓰려면
//@Service
public class MemberService {

    /*private final MemberRepository memberRepository = new MemoryMemberRepository();
    회원서비스가 회원레포지토리 직접 생성*/
    private final MemberRepository memberRepository;
    //외부에서 memberRepository를 넣어주도록 = Dependency Injection
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    //@Transactional 여기에 해줘도 됨. jpa는 데이터 들어올 떄 모두 transactional 안에서 실행되어야 한다.
    public Long join(Member member){
        //로직 : 같은 이름이 있는 중복 회원은 없다.
        //중복회원검증
        /*1.
        Optional<Member> result = memberRepository.findByName(member.getName());
        값이 있으면 동작함. Optional안에 member 객체 있음. Optional 여러 메소드 있음.
        아니면 if(null)문으로 해야함.
        result.ifPresent(m -> {
            throw new IllegalStateException("이.존.회");
        });
        //result.get(); 그냥 꺼내는건 권장 안 함.
        //result.orElseGet(); 많이 씀
        */
        //2.
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이.존.회");
                });
    }

    /*
    전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
