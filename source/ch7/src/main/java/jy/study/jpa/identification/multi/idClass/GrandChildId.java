package jy.study.jpa.identification.multi.idClass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class GrandChildId implements Serializable {

    private ChildId child; //GrandChild.child 매핑
    private String id; //GrandChild.id 매핑
}
