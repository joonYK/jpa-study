package jy.study.jpa.identification.multi.idClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Parent {

    @Id
    @Column(name = "PARENT_ID1")
    private String id1; //ParentId.id1과 매핑
    private String name;
}
