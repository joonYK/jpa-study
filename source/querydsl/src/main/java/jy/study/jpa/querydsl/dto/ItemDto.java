package jy.study.jpa.querydsl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {

    private Long id;

    private String itemName;

    private List<ItemCategoryDto> cates;
}
