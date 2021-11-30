package jy.study.jpa.querydsl;

import com.querydsl.core.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static jy.study.jpa.querydsl.entity.QMember.member;

public class QuerydslIntermediateTest extends BaseQuerydslTest {

    @Test
    public void simpleProjection() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void tupleProjection() {
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }
}
