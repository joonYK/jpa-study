package jy.study.jpa.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.querydsl.entity.Member;
import jy.study.jpa.querydsl.entity.QMember;
import jy.study.jpa.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static jy.study.jpa.querydsl.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {
        String qlString = "select m from Member m where m.username = :username";

        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.username.eq("member1")
                        .and(QMember.member.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

    }

    @Test
    public void searchAndParam() {
        List<Member> members = queryFactory
                .selectFrom(QMember.member)
                .where(
                        QMember.member.username.startsWith("member"),
                        QMember.member.age.in(10, 20)
                )
                .fetch();

        for (Member findMember : members) {
            assertThat(findMember.getUsername()).isIn("member1", "member2");
            assertThat(findMember.getAge()).isIn(10, 20);
        }
    }

    @Test
    public void resultFetch() {
        System.out.println("fetch : 모든 회원 조회");
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        System.out.println("fetchOne : 단 건 조회");
        Member fetchOne = queryFactory
                .selectFrom(QMember.member)
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println("fetchFirst : limit(1).fetchOne()");
        Member fetchFirst = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();

        System.out.println("fetchResults : 페이징용 쿼리. total count 조회 쿼리 따로 호출.");
        QueryResults<Member> fetchResults = queryFactory
                .selectFrom(member)
                .fetchResults();

        long total = fetchResults.getTotal();
        List<Member> results = fetchResults.getResults();

        System.out.println("fetchCount : count 쿼리");
        long count = queryFactory
                .selectFrom(member)
                .fetchCount();
    }
}
