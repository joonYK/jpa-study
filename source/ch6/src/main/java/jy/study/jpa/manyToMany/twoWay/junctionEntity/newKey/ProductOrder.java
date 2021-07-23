package jy.study.jpa.manyToMany.twoWay.junctionEntity.newKey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ProductOrder {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Integer orderAmount;
}
