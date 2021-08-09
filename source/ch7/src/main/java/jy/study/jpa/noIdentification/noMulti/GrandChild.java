package jy.study.jpa.noIdentification.noMulti;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GrandChild {
    @Id
    @GeneratedValue
    @Column(name = "GRANDCHILD_ID")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Child child;
}
