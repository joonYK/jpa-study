package jy.study.jpa.tablePerClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}
