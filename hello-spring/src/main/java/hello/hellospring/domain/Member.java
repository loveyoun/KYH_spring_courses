package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //JPA가 관리하는 엔티티구나~
public class Member {
//annotation으로 db랑 mapping한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 매핑
    //DB가 자동으로 ID넣어주는 것 : IDENTITY. 오라클에서는 sequence
    private Long id; //데이터 구분위한 시스템 저장용
    //@Colum(name = "username"). DB column name == username으로 맵핑 된다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
