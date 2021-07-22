package jy.study.jpa.oneToOne.oneWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOKCER_ID")
    private Long id;

    private String name;
}
