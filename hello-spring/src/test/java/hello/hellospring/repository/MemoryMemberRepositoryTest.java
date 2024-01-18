package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


//import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //각 메소드 끝날때마다 메모리 지워주는 콜백메세지
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        //여기서 setname -> memberrepository에서 setid
        member.setName("spring");

        repository.save(member);

        //optional.get(). 좋은 건 아니지만 test에서는 그래도 됨.
        Member result = repository.findById(member.getId()).get();

        /*검증*/
        //1.
        //System.out.println("result = " + (result == member));

        //2. junit.jupiter.api.Assertions
        //Assertions.assertEquals(member, result);

        //3. 최근에 많이 쓰는. org.assertj.core.api.Assertions
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Optional<Member> result = repository.findByName("spring");
        Member result = repository.findByName("spring1").get(); //optional 까서 꺼낼 수 있다.
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
