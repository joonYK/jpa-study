package jy.study.jpa.querydsl;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JoinExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("기본 조인");
        basicJoin(em);
        System.out.println();

        System.out.println("조인 on 사용");
        joinOn(em);
        System.out.println();
    }

    private static void basicJoin(EntityManager em) {
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QProduct product = QProduct.product;

        List<Order> list = new JPAQueryFactory(em)
                .select(order)
                .from(order)
                .join(order.member, member)
                .leftJoin(order.product, product)
                .fetch();

        for (Order o : list) {
            System.out.println("order = " + o);
        }
    }

    private static void joinOn(EntityManager em) {
        QMember member = QMember.member;
        QTeam team = QTeam.team;

        List<Tuple> result = new JPAQueryFactory(em)
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("team1"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }
}
