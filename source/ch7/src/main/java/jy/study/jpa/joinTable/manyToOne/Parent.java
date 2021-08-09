package jy.study.jpa.joinTable.manyToOne;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Parent {
    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> child = new ArrayList<>();

    public void addChild(Child child) {
        if (!this.child.contains(child)) {
            this.child.add(child);
            child.setParent(this);
        }
    }
}
