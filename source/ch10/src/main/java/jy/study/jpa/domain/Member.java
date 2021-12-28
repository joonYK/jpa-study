package jy.study.jpa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = "team")
@SqlResultSetMapping(name = "memberWithOrderCount",
    entities = {@EntityResult(entityClass = Member.class)},
    columns = {@ColumnResult(name = "ORDER_COUNT")}
)
@NamedNativeQuery(
        name = "Member.memberSQL",
        query = "SELECT ID, AGE, USERNAME, TEAM_ID " +
                "FROM MEMBER WHERE AGE > ?",
        resultClass = Member.class
)
@NamedNativeQuery(
        name = "Member.memberWithOrderCount",
        query = "SELECT M.ID, AGE, USERNAME, TEAM_ID, I.ORDER_COUNT " +
                "FROM MEMBER M " +
                "LEFT JOIN " +
                "   (SELECT IM.ID, COUNT(*) AS ORDER_COUNT " +
                "   FROM ORDERS O, MEMBER IM " +
                "   WHERE O.MEMBER_ID = IM.ID" +
                "   GROUP BY IM.ID) I " +
                "ON M.ID = I.ID",
        resultSetMapping = "memberWithOrderCount"
)
public class Member {

    @Id
    private String id;

    private String username;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String username, Integer age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
    }
}
