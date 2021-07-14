package jy.study.jpa.twoWay;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table
public class Team2 {

    @Id
    @Column(name = "team_id")
    private String id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member2> members = new ArrayList<>();

    public Team2(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
