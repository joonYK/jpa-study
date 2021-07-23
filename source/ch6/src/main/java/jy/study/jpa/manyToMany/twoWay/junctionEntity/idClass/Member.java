package jy.study.jpa.manyToMany.twoWay.junctionEntity.idClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<ProductOrder> orders = new ArrayList<>();
}