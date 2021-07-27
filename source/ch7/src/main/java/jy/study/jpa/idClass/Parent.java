package jy.study.jpa.idClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Entity
@IdClass(ParentId.class)
public class Parent {

    @Id
    @Column(name = "PARENT_ID1")
    private String id1; //ParentId.id1과 매핑

    @Id
    @Column(name = "PARENT_ID2")
    private String id2; //ParentId.id2와 매핑

    private String name;
}
