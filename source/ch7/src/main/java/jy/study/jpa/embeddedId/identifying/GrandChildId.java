package jy.study.jpa.embeddedId.identifying;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class GrandChildId implements Serializable {
    private ChildId childId; //@MapsId("childId")로 매핑

    @Column(name = "GRANDCHILD_ID")
    private String id;
}
