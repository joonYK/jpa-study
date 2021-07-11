package jy.study.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

    private String id;

    private String username;

    private Team team;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
