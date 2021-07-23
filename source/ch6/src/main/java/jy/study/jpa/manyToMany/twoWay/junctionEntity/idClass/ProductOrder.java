package jy.study.jpa.manyToMany.twoWay.junctionEntity.idClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(ProductOrderId.class)
public class ProductOrder {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Integer orderAmount;
}
