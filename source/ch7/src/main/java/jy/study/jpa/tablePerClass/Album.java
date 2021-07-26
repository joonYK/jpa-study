package jy.study.jpa.tablePerClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Album extends Item {

    private String artist;
}
