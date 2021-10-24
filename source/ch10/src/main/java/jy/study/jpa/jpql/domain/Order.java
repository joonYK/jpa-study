package jy.study.jpa.jpql.domain;

import jy.study.jpa.jpql.domain.value.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "ORDERS")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderAmount;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Builder
    public Order(Integer orderAmount, Address address, Member member, Product product) {
        this.orderAmount = orderAmount;
        this.address = address;
        this.member = member;
        this.product = product;
    }
}