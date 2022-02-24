package jy.study.jpa.querydsl.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    public enum ItemType {
        ITEM1, ITEM2
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    public ItemType itemType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCategory> categories = new ArrayList<>();

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(String itemName, ItemType itemType) {
        this.itemName = itemName;
        this.itemType = itemType;
    }
}
