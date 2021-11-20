package jy.study.jpa.querydsl;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.domain.Member;
import jy.study.jpa.domain.QMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SubQueryExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        System.out.println("서브 쿼리 예제 - 한 건");
        subQuery(queryFactory);
        System.out.println();

        System.out.println("서브 쿼리 예제 - 여러 건");
        subQueryIn(queryFactory);
        System.out.println();
    }

    private static void subQuery(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;
        QMember memberSub = new QMember("memberSub");

        List<Member> members = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        System.out.println(members);
    }

    private static void subQueryIn(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;
        QMember memberSub = new QMember("memberSub");

        List<Member> members = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(15))
                ))
                .fetch();

        System.out.println(members);
    }

}
