package jy.study.jpa.querydsl.dto;

import jy.study.jpa.querydsl.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCountDto {

    private Item.ItemType itemType;

    private Long count;
}
