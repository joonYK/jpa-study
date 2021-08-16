package jy.study.jpa.example.entity;

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

    private String city;
    private String street;
    private String zipcode;

    public Member(String id, String name, String city, String street, String zipcode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
