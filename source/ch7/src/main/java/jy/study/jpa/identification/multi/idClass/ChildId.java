package jy.study.jpa.identification.multi.idClass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ChildId implements Serializable {

    private String parent; //Child.parent 매핑
    private String childId; //Child.childId 매핑

}
