package jy.study.jpa.joinTable.oneToOne;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "child")
    private Parent parent;
}
