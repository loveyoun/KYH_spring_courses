package hello.jdbc.domain;

import lombok.Data;

import java.util.Objects;

@Data  // ToString(), EqualsAndHashCode() 오버라이드
public class Member {

    private String memberId;
    private int money;

    public Member() {
    }

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }


}
