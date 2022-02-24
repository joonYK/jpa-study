package jy.study.jpa.querydsl.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.querydsl.dto.ItemCategoryDto;
import jy.study.jpa.querydsl.dto.ItemCountDto;
import jy.study.jpa.querydsl.dto.ItemDto;
import jy.study.jpa.querydsl.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static jy.study.jpa.querydsl.entity.QItem.item;
import static jy.study.jpa.querydsl.entity.QItemCategory.itemCategory;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final JPAQueryFactory queryFactory;

    public List<ItemDto> getItemDtoList(List<Long> categoryIds) {
        return queryFactory
                .from(item)
                .leftJoin(itemCategory).on(itemCategory.item.eq(item))
                .where(itemCategory.id.in(categoryIds))
                .transform(
                    groupBy(item.id).list(
                        Projections.fields(
                            ItemDto.class,
                            item.id,
                            item.itemName,
                            GroupBy.list(
                                Projections.fields(
                                    ItemCategoryDto.class,
                                    itemCategory.id
                                )
                            ).as("cates")
                        )
                    )
                );
    }

    public List<Tuple> groupByCount() {
        return queryFactory.select(
                new CaseBuilder()
                    .when(item.itemType.isNull())
                    .then("ITEM3")
                    .otherwise(item.itemType.stringValue()),
                new CaseBuilder()
                    .when(item.itemType.isNull())
                    .then("NULL")
                    .otherwise(item.itemType.stringValue())
                    .count()
                )
                .from(item)
                .groupBy(item.itemType)
                .fetch();
    }
}
