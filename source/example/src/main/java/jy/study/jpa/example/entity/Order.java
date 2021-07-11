package jy.study.jpa.example.entity;

import jy.study.jpa.example.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    //주문 날짜
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    //주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
