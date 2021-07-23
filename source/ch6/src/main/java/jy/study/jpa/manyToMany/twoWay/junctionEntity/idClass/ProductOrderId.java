package jy.study.jpa.manyToMany.twoWay.junctionEntity.idClass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ProductOrderId implements Serializable {
    private String member;
    private String product;

}
