package jy.study.jpa.manyToMany.twoWay.junctionEntity.idClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
