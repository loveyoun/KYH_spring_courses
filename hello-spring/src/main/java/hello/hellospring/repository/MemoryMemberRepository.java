package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    //실무에서, 공유되는 변수는 동시성 문제로 concurrentHashmap 쓰기.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //마찬가지로 atomicLong

    @Override
    public Member save(Member member) {
        //member를 다 세팅한 후,
        member.setId(++sequence);
        //저장소에 넣기
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return Optional.empty();
        return Optional.ofNullable(store.get(id));
        //이렇게 감싸서 나가면, null이어도 클라이언트가 뭘 할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()      //loop
                .filter(member -> member.getName().equals(name))    //java8의 lambda
                .findAny();
        //하나라도 찾고, optional로 반환이 된다.
    }

    @Override
    public List<Member> findAll() {
        //실무에서 루프돌리기 편해서 list 많이 사용함.
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear();
    }

}
