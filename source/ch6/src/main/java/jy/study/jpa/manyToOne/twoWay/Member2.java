package jy.study.jpa.manyToOne.twoWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member2 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team2 team;

    public void setTeam(Team2 team) {
        this.team = team;

        if (!team.getMembers().contains(this))
            team.getMembers().add(this);
    }
}
