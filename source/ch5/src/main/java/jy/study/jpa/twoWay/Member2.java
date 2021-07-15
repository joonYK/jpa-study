package jy.study.jpa.twoWay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"team"})
@NoArgsConstructor
@Entity
@Table
public class Member2 {

    @Id
    @Column(name = "member_id")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team2 team;

    public Member2(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setTeam(Team2 team) {
        if(this.team != null)
            this.team.getMembers().remove(this);

        this.team = team;
        team.getMembers().add(this);
    }
}
