package jy.study.jpa.manyToOne.twoWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Team2 {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member2> members = new ArrayList<>();

    public void addMember(Member2 member) {
        this.members.add(member);
        if (member.getTeam() != this)
            member.setTeam(this);
    }

}
