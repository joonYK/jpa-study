package jy.study.jpa.example.entity;

import jy.study.jpa.example.value.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member extends BaseEntity {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;

    @Embedded
    private Address address;

    public Member(String id, String name, String city, String street, String zipcode) {
        this.id = id;
        this.name = name;
        this.address = new Address(city, street, zipcode);
    }

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
