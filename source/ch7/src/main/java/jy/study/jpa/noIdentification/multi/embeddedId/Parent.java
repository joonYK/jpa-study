package jy.study.jpa.noIdentification.multi.embeddedId;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Parent {

    @EmbeddedId
    private ParentId id;

    private String name;
}
