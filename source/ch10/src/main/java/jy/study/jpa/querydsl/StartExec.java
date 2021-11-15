package jy.study.jpa.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import jy.study.jpa.domain.Member;
import jy.study.jpa.domain.QMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StartExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        queryDSL(em);
    }

    private static void queryDSL(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember qMember = new QMember("m"); //생성되는 JPQL의 별칭이 m
        List<Member> members = query.from(qMember)
                .where(qMember.username.eq("유저1"))
                .orderBy(qMember.username.desc())
                .fetch();

        System.out.println(members);
    }
}
