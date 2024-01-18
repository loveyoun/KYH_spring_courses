package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /*jpa는 entitymanager로 모든 게 동작된다.
    gradle.build에 data-gpa
    => spring boot가 현재 database랑 연결해서(properties 정보랑 dbconnection 정보랑 짬뽕해서) entitymanager 자동 생성해줌.
    entitymanager은 datasource 내부적으로 가지고 있어서, db 통신을 내부적으로 처리한다.
    */
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) { //만들어진 걸 injection 받으면 된다.
        this.em = em;
    }

    //저장, 조회, update는 jpql 필요 없다. 다 자동으로. 여러개 리스트로 가져올때나 pk기반 아니 것들은 필요.
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //jpqa라는 객체지향 쿼리를 써야 함.
        //테이블 대상이 아니라, 객체를 대상으로 쿼리를 날림
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // jpql이라는 객체지향 query를 써야한다. findById와는 다르게.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) //조회는 Member.class로.
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //jpql: 객체 대상으로 query 날림(원래는 table 대상으로 쿼리 날리는데) -> sql로 번역됨
        return em.createQuery("select m from Member m", Member.class) //객체 자체 select. Member (as) m
                .getResultList();
        //c + s + N : inline으로 만들기
        //List<Member> result = em.createQuery~
    }
}
