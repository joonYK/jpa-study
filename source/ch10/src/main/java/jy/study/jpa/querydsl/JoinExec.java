package jy.study.jpa.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.domain.*;
import jy.study.jpa.domain.QMember;
import jy.study.jpa.domain.QOrder;
import jy.study.jpa.domain.QProduct;
import jy.study.jpa.domain.QTeam;

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

        System.out.println("페치 조인");
        fetchJoin(em, emf);
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

    private static void fetchJoin(EntityManager em, EntityManagerFactory emf) {
        QMember member = QMember.member;
        QTeam team = QTeam.team;

        Member findMember = new JPAQueryFactory(em)
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.id.eq("member1"))
                .fetchOne();

        System.out.println(findMember);

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        System.out.println(loaded);

    }
}
