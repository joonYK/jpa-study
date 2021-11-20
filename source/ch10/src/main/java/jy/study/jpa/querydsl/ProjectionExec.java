package jy.study.jpa.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.domain.QMember;
import jy.study.jpa.querydsl.dto.MemberDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProjectionExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        System.out.println("프로젝션 대상 하나");
        simpleProjection(queryFactory);
        System.out.println();

        System.out.println("튜플 사용");
        tupleProjection(queryFactory);
        System.out.println();

        System.out.println("dto 사용 - setter");
        dtoBySetter(queryFactory);
        System.out.println();

        System.out.println("dto 사용 - 필드 직접 접근");
        dtoByFields(queryFactory);
        System.out.println();

        System.out.println("dto 사용 - 생성자");
        dtoByConstructor(queryFactory);
        System.out.println();
    }

    private static void simpleProjection(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;

        List<String> list = queryFactory
                .select(member.username)
                .from(member)
                .fetch();

        System.out.println(list);
    }

    private static void tupleProjection(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;

        List<Tuple> tuples = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : tuples) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }

    private static void dtoBySetter(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;

        List<MemberDto> list = queryFactory
                .select(Projections.bean(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();

        System.out.println(list);
    }

    private static void dtoByFields(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;

        List<MemberDto> list = queryFactory
                .select(Projections.fields(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();

        System.out.println(list);
    }

    private static void dtoByConstructor(JPAQueryFactory queryFactory) {
        QMember member = QMember.member;

        List<MemberDto> list = queryFactory
                .select(Projections.constructor(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();

        System.out.println(list);
    }
}
