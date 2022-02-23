package jy.study.jpa.querydsl.repository;

import jy.study.jpa.querydsl.BaseQuerydslTest;
import jy.study.jpa.querydsl.dto.ItemDto;
import jy.study.jpa.querydsl.entity.Item;
import jy.study.jpa.querydsl.entity.ItemCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


class ItemRepositoryTest extends BaseQuerydslTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void test() {
        Item item1 = new Item("아이템1");
        Item item2 = new Item("아이템2");
        Item item3 = new Item("아이템3");

        ItemCategory itemCategory1 = new ItemCategory(1L, "카테고리1", item1);
        item1.getCategories().add(itemCategory1);
        ItemCategory itemCategory2 = new ItemCategory(2L, "카테고리2", item1);
        item1.getCategories().add(itemCategory2);
        ItemCategory itemCategory3 = new ItemCategory(3L, "카테고리3", item1);
        item1.getCategories().add(itemCategory3);
        ItemCategory itemCategory4 = new ItemCategory(4L, "카테고리4", item2);
        item2.getCategories().add(itemCategory4);
        ItemCategory itemCategory5 = new ItemCategory(5L, "카테고리5", item2);
        item2.getCategories().add(itemCategory5);
        ItemCategory itemCategory6 = new ItemCategory(6L, "카테고리6", null);

        em.persist(item1);
        em.persist(item2);
        em.persist(item3);

        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);

        List<ItemDto> itemDtoList = itemRepository.getItemDtoList(categoryIds);

        System.out.println(itemDtoList);
    }
}