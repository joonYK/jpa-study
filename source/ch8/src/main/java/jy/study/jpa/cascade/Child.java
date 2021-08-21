package jy.study.jpa.cascade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Child {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Parent parent;
}
