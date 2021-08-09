package jy.study.jpa.identification.multi.embeddedId;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Child {
    @EmbeddedId
    private ChildId id;

    @MapsId("parentId") //ChildId.parentId 매핑
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    private String name;
}
