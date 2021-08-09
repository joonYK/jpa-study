package jy.study.jpa.identification.multi.embeddedId;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ChildId implements Serializable {
    private String parentId; //@MapsId("parentId")로 매핑

    @Column(name = "CHILD_ID")
    private String id;
}
