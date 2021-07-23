package jy.study.jpa.manyToMany.twoWay.junctionEntity.newKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
