package jy.study.jpa.querydsl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategory {

    @Id
    private Long id;

    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
